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
		System.out.println((24 * 3600 * 1000 * 100)); //8640000000 but 50065408
		testLongArg(24 * 3600 * 1000 * 100* 100L); //5006540800
		testLongArg(24L * 3600 * 1000 * 100* 100); //864000000000 
	}
	
	private void testLongArg(Long arg){
		System.out.println(arg);
	}
	
	@Test
	public void testScience(){
		Double a = 1e7;
		System.out.println("double:" + a.doubleValue());
		System.out.println("float:" + a.floatValue());
		System.out.println("long:" + a.longValue());
		System.out.println("int:" + a.intValue());
	}
	
	@Test
	public void testTrans() {
		long abc = 0xffffffffL;
		System.out.println(abc);
		System.out.println(Long.parseLong("ffffffff", 16));
		System.out.println(Integer.parseInt("ff", 16));

		Integer a = 127;
		Integer b = 127;
		Integer c = new Integer(127);
		Integer d = new Integer(127);
		System.out.println(a == b);
		System.out.println(b == c);
		System.out.println(c == d);
	}
	
	
	@Test
	public void testFloat(){
		System.out.println(2.00 - 1.10); 
		System.out.println(String.format("%.2f", 2.00 - 1.10)); 
	}
}
