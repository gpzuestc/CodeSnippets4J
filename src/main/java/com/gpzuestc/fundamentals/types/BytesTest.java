package com.gpzuestc.fundamentals.types;

import java.math.BigInteger;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Sep 10, 2012
 * 
 */
public class BytesTest {
	public static void main(String[] args) {
		System.out.println("get".getBytes().length);
		System.out.println("get".getBytes()[0]);
		System.out.println("0".getBytes()[0]);
	}
	
	@Test
	public void test(){
		byte[] bytes = "get".getBytes();
		String value = new String(bytes);
		Integer i = Integer.valueOf(value);
		System.out.println(i);
	}
	
	@Test
	public void testLong(){
		Long id = 1000L;
//		Long id = 127L;
		Integer num = 0;
		BigInteger bi = new BigInteger(id.toString());
		byte[] bytes = bi.toByteArray();
		for(int i = 0; i < bytes.length; i++){
			System.out.println(i + ": " + bytes[i]);
		}
		System.out.println(new BigInteger(bytes));
		
	}
}
