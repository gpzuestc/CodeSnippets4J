package com.gpzuestc.fundamentals.types;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-5
 * @todo: 
 * 
 */
public class IntegerTest {
	
	@Test
	public void test(){
		Double d = 2.34;
//		Integer a = (Integer)d;
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		long vid = 1000000100193L;
		long a = - vid % Integer.MAX_VALUE;
		System.out.println(a);
		
		long base = 1000000000000L;
		System.out.println((base / Integer.MAX_VALUE) * Integer.MAX_VALUE - a);
	}
	
	@Test
	public void testAbs(){
		System.out.println(Math.abs(Integer.MIN_VALUE));
	}
	
	@Test
	public void testInteger(){
		Integer a = 1;
		Integer b = 2;
		Integer c = ++a;
		
		System.out.println(c == b); //true
	}
	
}
