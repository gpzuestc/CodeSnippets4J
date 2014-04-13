package com.gpzuestc.fundamentals.types;

import java.math.BigInteger;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-11
 * @todo: 
 * 
 */
public class LongTest {
	
	@Test
	public void testUnsignedLong(){
		Long a = 1L;
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MAX_VALUE + 1);
		System.out.println(new BigInteger(Long.toHexString(Long.MAX_VALUE * 2), 16));
		System.out.println(new BigInteger(Long.toOctalString(Long.MAX_VALUE * 2), 8));
		
	}
	
	@Test
	public void testLongParse(){
		Object a = "1";
		Long abc = Long.parseLong(a.toString());
		System.out.println(abc);
	}
	
	@Test
	public void testEqualsInt(){
		Long a = 7L;
		System.out.println(a.equals(7)); //false
		System.out.println(a == 7); // true
		
		long b = 7;
		System.out.println(a.equals(b)); //true;
		System.out.println(a == b);  //true;
	}
}
