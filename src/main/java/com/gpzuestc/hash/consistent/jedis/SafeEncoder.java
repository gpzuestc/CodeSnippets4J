package com.gpzuestc.hash.consistent.jedis;



/**
 * The only reason to have this is to be able to compatible with java 1.5 :(
 * 
 */
public class SafeEncoder {
	public static byte[][] encodeMany(final String... strs){
		byte[][] many = new byte[strs.length][];
		for(int i=0;i<strs.length;i++){
			many[i] = encode(strs[i]);
		}
		return many;
	}
	
    public static byte[] encode(final String str) {
            if (str == null) {
                throw new RuntimeException(
                        "value sent to redis cannot be null");
            }
            return str.getBytes();
    }

    public static String encode(final byte[] data) {
            return new String(data);
    }
}
