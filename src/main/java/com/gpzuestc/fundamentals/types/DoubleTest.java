package com.gpzuestc.fundamentals.types;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-22
 * @todo: 
 * 
 */
public class DoubleTest {
	
	@Test
	public void testNaN(){
		Double b = Double.NaN;
		System.out.println(b.isInfinite());
		System.out.println(b.isNaN());
		System.out.println(b > 1);
	}
	
	@Test
	public void testMod(){
		Double b = 123.6;
		System.out.println(Math.rint(b));
		System.out.println(Math.round(b));
		System.out.println(b/100);
		System.out.println(new Double(b%100).longValue());
	}
}
