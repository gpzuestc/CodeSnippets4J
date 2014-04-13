package com.gpzuestc.fundamentals.types;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-11
 * @todo: 
 * 
 */
public class NumberTest {
	@Test
	public void testMax(){
		System.out.println(Long.MAX_VALUE);  //   2^(8*8-1) = 9223372036854775807
		System.out.println(Integer.MAX_VALUE); // 2^(4*8-1) = 2147483647
	}
	
	@Test
	public void testScience(){
		Double a = 1e7;
		System.out.println("double:" + a.doubleValue());
		System.out.println("float:" + a.floatValue());
		System.out.println("long:" + a.longValue());
		System.out.println("int:" + a.intValue());
	}
}
