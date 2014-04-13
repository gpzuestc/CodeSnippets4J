package com.gpzuestc.hash;

import java.math.BigInteger;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-2-18
 * @todo: 
 * 
 */
public class HashAlgTest {
	
	public static BigInteger time33BigInteger(String str){
		BigInteger hash = new BigInteger("0");
		char [] temp = str.toCharArray();
		for(int i = 0; i < str.length(); i++){
			hash = hash.multiply(new BigInteger("33")).add(new BigInteger(new Integer(temp[i]).toString()));
		}
		return hash;
	}
	
	public static Long time33Long(String str){
		long hash = 0;
		char [] temp = str.toCharArray();
		for(int i = 0; i < str.length(); i++){
			hash = ((hash << 5) + hash) + temp[i];
		}
		return Math.abs(hash);
	}
	
	@Test
	public void testTime33(){
		String str = "a/sd/asdfsdfgsdf/zx/sdfa.dddddddddddddddddddddddddddddddddddddddddddddddddddddddgbg124565gfdscvcxcvb/asdfssdffffff";
		System.out.println(Math.abs(str.hashCode()));
		System.out.println(Math.abs(str.hashCode()) % 4 + 1);
		BigInteger hash = time33BigInteger(str);
		System.out.println(hash);
		Long hashLong = time33Long(str);
		System.out.println(hashLong);
		System.out.println(Long.MAX_VALUE);
		System.out.println(hash.mod(new BigInteger("4")).add(new BigInteger("1")));
//		System.out.println(hash % 4 + 1);
	}
	
	
}
