package com.gpzuestc.hash.consistent.jedis;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sharded<R, S extends ShardInfo<R>> {

    public static final int DEFAULT_WEIGHT = 1;
    private TreeMap<Long, S> nodes;
    private final Hashing algo;
    private List<S> shards;
    /**
     * The default pattern used for extracting a key tag. The pattern must have
     * a group (between parenthesis), which delimits the tag to be hashed. A
     * null pattern avoids applying the regular expression for each lookup,
     * improving performance a little bit is key tags aren't being used.
     */
    private Pattern tagPattern = null;
    // the tag is anything between {}
    public static final Pattern DEFAULT_KEY_TAG_PATTERN = Pattern
            .compile("\\{(.+?)\\}");

    public Sharded(List<S> shards) {
        this(shards, Hashing.MURMUR_HASH); // MD5 is really not good as we works
        // with 64-bits not 128
    }

    public Sharded(List<S> shards, Hashing algo) {
        this.algo = algo;
        initialize(shards);
    }

    public Sharded(List<S> shards, Pattern tagPattern) {
        this(shards, Hashing.MURMUR_HASH, tagPattern); // MD5 is really not good
        // as we works with
        // 64-bits not 128
    }

    public Sharded(List<S> shards, Hashing algo, Pattern tagPattern) {
        this.algo = algo;
        this.tagPattern = tagPattern;
        initialize(shards);
    }

    private void initialize(List<S> shards) {
    	this.shards = shards;
        nodes = new TreeMap<Long, S>();
        /***
         * guopeng add
         */
        if(algo instanceof KetamaHash){
        	for (S node : shards) {
    			for (int i = 0; i < 160 / 4; i++) {
    				byte[] digest = algo.computeMd5(node.getName() + i);
    				for(int h = 0; h < 4; h++) {
    					long m = algo.hash(digest, h);
    					
    					nodes.put(m, node);
    				}
    			}
    		}
        }else{
        	for (int i = 0; i != shards.size(); ++i) {
        		final S shardInfo = shards.get(i);
        		if (shardInfo.getName() == null)
        			for (int n = 0; n < 160 * shardInfo.getWeight(); n++) {
        				nodes.put(this.algo.hash("SHARD-" + i + "-NODE-" + n), shardInfo);
        			}
        		else
        			for (int n = 0; n < 160 * shardInfo.getWeight(); n++) {
        				nodes.put(this.algo.hash(shardInfo.getName() + "*" + shardInfo.getWeight() + n), shardInfo);
        			}
        	}
        }
    }

    public S getShardInfo(byte[] key) {
        SortedMap<Long, S> tail = nodes.tailMap(algo.hash(key));
        if (tail.size() == 0) {
            return nodes.get(nodes.firstKey());
        }
        return tail.get(tail.firstKey());
    }

    public S getShardInfo(String key) {
        return getShardInfo(SafeEncoder.encode(getKeyTag(key)));
    }

    /**
     * A key tag is a special pattern inside a key that, if preset, is the only
     * part of the key hashed in order to select the server for this key.
     *
     * @see http://code.google.com/p/redis/wiki/FAQ#I
     *      'm_using_some_form_of_key_hashing_for_partitioning,_but_wh
     * @param key
     * @return The tag if it exists, or the original key
     */
    public String getKeyTag(String key) {
        if (tagPattern != null) {
            Matcher m = tagPattern.matcher(key);
            if (m.find())
                return m.group(1);
        }
        return key;
    }

    public List<S> getNodes(){
    	return shards;
    }

}

