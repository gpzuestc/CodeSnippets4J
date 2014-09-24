package com.gpzuestc.hash.consistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.gpzuestc.hash.Node;
import com.gpzuestc.hash.consistent.jedis.Hashing;
import com.gpzuestc.hash.consistent.jedis.JedisShardInfo;
import com.gpzuestc.hash.consistent.jedis.ShardInfo;
import com.gpzuestc.hash.consistent.jedis.Sharded;
/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 26, 2012
 * 
 */
public class HashTest {
	static Random ran = new Random();
	
	/** key's count */
	private static final Integer EXE_TIMES = 100000;
	
	private static final Integer NODE_COUNT = 10;
	
	private static final Integer VIRTUAL_NODE_COUNT = 160;
	
	public static void main(String[] args) {
		testHash(HashAlgorithm.KETAMA_HASH);
		testHash(HashAlgorithm.MURMUR_HASH);
		testHash(HashAlgorithm.MD5_HASH);
		testHash(HashAlgorithm.DJB_HASH);
		
		
//		testJedisShard(Hashing.MURMUR_HASH);
//		testJedisShard(Hashing.MD5);
//		testJedisShard(Hashing.KETAMA_HASH);
	}


	private static void testHash(HashAlgorithm alg) {
		System.out.println("Algorithm: " + alg);
		long begin = System.currentTimeMillis();
		HashTest test = new HashTest();
		
		/** Records the times of locating node*/
		
		List<Node> allNodes = test.getNodes(NODE_COUNT);
		NodeLocator locator = new NodeLocator(allNodes, alg, VIRTUAL_NODE_COUNT);
		List<String> allKeys = test.getAllStrings();
		Map<String, Node> keyNodeMap = new HashMap<String, Node>();
//		for(int i = 0; i < 3; i++){
//			if(i == 1){
//				List<Node> subNodes = new ArrayList<Node>(allNodes);
//				subNodes.remove(0);
//				locator = new NodeLocator(subNodes, alg, VIRTUAL_NODE_COUNT);
//			}else if(i == 2){
//				List<Node> addNodes = new ArrayList<Node>(allNodes);
//				addNodes.add(new Node("node" + (NODE_COUNT + 1)));
//				locator = new NodeLocator(addNodes, alg, VIRTUAL_NODE_COUNT);
//			}
			for (String key : allKeys) {
				Node node = locator.getPrimary(key);
				keyNodeMap.put(key, node);
			}
			
//			System.out.println("Execute time: " + (System.currentTimeMillis() - begin) + "ms");
//			float expect = (float) 100 / locator.getNodes().size() ;
//			System.out.println("\nNodes count : " + locator.getNodes().size() + ", Keys count : " + EXE_TIMES + ", Expect percent : " + expect + "%");
//			System.out.println("-------------------- -------  ----------------------");
//			System.out.println("NodeName\tHitCount\tPercent");
//			float sum = 0;
//			float percent = 0;
//			for (Map.Entry<Node, Integer> entry : nodeRecord.entrySet()) {
//				percent = (float)entry.getValue() / EXE_TIMES * 100;
//				System.out.println(entry.getKey() + "\t\t" + entry.getValue() + "\t\t" + percent + "%");
//				sum += Math.pow(percent - expect, 2);
//			}
//			System.out.println("Variance:" + Math.sqrt(sum / locator.getNodes().size()));
			
			Map<Node, Integer> baseNodeMap = changeNode(alg, test, allKeys, keyNodeMap, null, NODE_COUNT);
			changeNode(alg, test, allKeys, keyNodeMap, baseNodeMap, NODE_COUNT + 1);
			changeNode(alg, test, allKeys, keyNodeMap, baseNodeMap, NODE_COUNT - 1);
			System.out.println("-------------------- -------  ----------------------");
			System.out.println();
//		}
	}


	private static Map<Node, Integer> changeNode(HashAlgorithm alg, HashTest test,
			List<String> allKeys, Map<String, Node> keyNodeMap, Map<Node, Integer> compareNodeMap, int nodeCount) {
		List<Node> allNodes;
		NodeLocator locator;
		long count = 0;
		allNodes = test.getNodes(nodeCount);
		locator = new NodeLocator(allNodes, alg, VIRTUAL_NODE_COUNT);
		Map<Node, Integer> nodeRecord = new HashMap<Node, Integer>();
		Map<Node, Integer> hitRecord = new HashMap<Node, Integer>();
		for (String key : allKeys) {
			Node node = locator.getPrimary(key);
			if(node.getFlag() == keyNodeMap.get(key).getFlag()){
				count++;
				Integer hit = hitRecord.get(node);
				if (hit == null) {
					hitRecord.put(node, 1);
				} else {
					hitRecord.put(node, hit + 1);
				}
			}
			Integer times = nodeRecord.get(node);
			if (times == null) {
				nodeRecord.put(node, 1);
			} else {
				nodeRecord.put(node, times + 1);
			}
			
		}
		float expect = (float) 100 / locator.getNodes().size() ;
		System.out.println("\nNodes count : " + locator.getNodes().size() + ", Keys count : " + EXE_TIMES + ", Expect percent : " + expect + "%");
		System.out.println("-------------------- -------  ----------------------");
		System.out.println("NodeName\tHitCount\tPercent");
		float sum = 0;
		float percent = 0;
		for (Map.Entry<Node, Integer> entry : nodeRecord.entrySet()) {
			percent = (float)entry.getValue() / EXE_TIMES * 100;
			System.out.println(entry.getKey() + "\t\t" + entry.getValue() + "\t\t" + percent + "%");
			sum += Math.pow(percent - expect, 2);
		}
		System.out.println("Variance:" + Math.sqrt(sum / locator.getNodes().size()));
		
		if(compareNodeMap != null){
			for (Map.Entry<Node, Integer> entry : hitRecord.entrySet()) {
				if (compareNodeMap.get(entry.getKey()) != null){
					percent = (float)entry.getValue() / compareNodeMap.get(entry.getKey()) * 100;
					System.out.println(entry.getKey() + "\t\t" + entry.getValue() + "\t\t" + percent + "%");
				}
			}
			System.out.println(count * 1.0 / allKeys.size());
		}
		return nodeRecord;
	}
	
	private static void testJedisShard(Hashing alg){
		System.out.println("Jedis shard test: " + alg.getName());
		long begin = System.currentTimeMillis();
		HashTest test = new HashTest();
		
		/** Records the times of locating node*/
		List<JedisShardInfo> shardInfos = new ArrayList<JedisShardInfo>();
		List<Node> allNodes = test.getNodes(NODE_COUNT);
		for(Node n : allNodes){
			shardInfos.add(new JedisShardInfo("host", 0, n.getName()));
		}
		
		Sharded<Node, JedisShardInfo> shards = new Sharded<Node, JedisShardInfo>(shardInfos, alg);
		List<String> allKeys = test.getAllStrings();
		
		
		Map<ShardInfo<Node>, Integer> nodeRecord = null;
//		for(int i = 0; i < 3; i++){
//			if(i == 1){
//				List<Node> subNodes = new ArrayList<Node>(allNodes);
//				subNodes.remove(0);
//				locator = new NodeLocator(subNodes, alg, VIRTUAL_NODE_COUNT);
//			}else if(i == 2){
//				List<Node> addNodes = new ArrayList<Node>(allNodes);
//				addNodes.add(new Node("node" + (NODE_COUNT + 1)));
//				locator = new NodeLocator(addNodes, alg, VIRTUAL_NODE_COUNT);
//			}
			nodeRecord = new HashMap<ShardInfo<Node>, Integer>();
			for (String key : allKeys) {
				ShardInfo<Node> node = shards.getShardInfo(key);
				Integer times = nodeRecord.get(node);
				if (times == null) {
					nodeRecord.put(node, 1);
				} else {
					nodeRecord.put(node, times + 1);
				}
			}
			
			System.out.println("Execute time: " + (System.currentTimeMillis() - begin) + "ms");
			float expect = (float) 100 / shards.getNodes().size() ;
			System.out.println("Nodes count : " + shards.getNodes().size() + ", Keys count : " + EXE_TIMES + ", Expect percent : " + expect + "%");
			System.out.println("-------------------- -------  ----------------------");
			System.out.println("NodeName\tHitCount\tPercent");
			float sum = 0;
			float percent = 0;
			for (Map.Entry<ShardInfo<Node>, Integer> entry : nodeRecord.entrySet()) {
				percent = (float)entry.getValue() / EXE_TIMES * 100;
				System.out.println(entry.getKey().getName() + "\t\t" + entry.getValue() + "\t\t" + percent + "%");
				sum += Math.pow(percent - expect, 2);
			}
			System.out.println("Variance:" + Math.sqrt(sum / shards.getNodes().size()));
			System.out.println("-------------------- -------  ----------------------");
			System.out.println();
//		}
	}
	
	
	/**
	 * Gets the mock node by the material parameter
	 * 
	 * @param nodeCount 
	 * 		the count of node wanted
	 * @return
	 * 		the node list
	 */
	private List<Node> getNodes(int nodeCount) {
		List<Node> nodes = new ArrayList<Node>();
		
		for (int k = 1; k <= nodeCount; k++) {
			Node node = new Node("node" + k);
			nodes.add(node);
		}
		
		return nodes;
	}
	
	/**
	 *	All the keys	
	 */
	private List<String> getAllStrings() {
		List<String> allStrings = new ArrayList<String>(EXE_TIMES);
		
		for (int i = 0; i < EXE_TIMES; i++) {
			allStrings.add(generateRandomString(ran.nextInt(50)));
		}
		
		return allStrings;
	}
	
	/**
	 * To generate the random string by the random algorithm
	 * <br>
	 * The char between 32 and 127 is normal char
	 * 
	 * @param length
	 * @return
	 */
	private String generateRandomString(int length) {
		StringBuffer sb = new StringBuffer(length);
		
		for (int i = 0; i < length; i++) {
			sb.append((char) (ran.nextInt(95) + 32));
		}
		
		return sb.toString();
	}
}
