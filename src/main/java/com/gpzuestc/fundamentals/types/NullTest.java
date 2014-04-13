package com.gpzuestc.fundamentals.types;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-8-12
 * 
 */
public class NullTest {
	
	@Test
	public void testInstanceof(){
		String str = null;
		System.out.println(str instanceof String); //false
	}
	
	@Test
	public void testToString(){
		String str = null;
		System.out.println("abc" + str);
		System.out.println("abc" + null);
	}
	
	@Test
	public void testNullTrans(){
		Number n = null;
		Integer a = (Integer)n;
		System.out.println(a);
	}
}
