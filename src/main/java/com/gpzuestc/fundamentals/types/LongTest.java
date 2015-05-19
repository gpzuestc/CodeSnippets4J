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
	
	@Test
	public void test_long(){
		long number1 = 1403750900 * 1000;  //溢出啦！
		System.out.println(number1); 
		
		long number2 = 1403750900 * 1000L; // 想要的结果，但L会被遗漏
		System.out.println(number2);
		
		Long number3 = 1403750900 * 1000L;  //如果不明确指定L,会提示错误
		System.out.println(number3);
		
		/**
		-703405792
		1403750900000
		1403750900000
		
		如果定义参数long的方法，则很容易误传这样的值！！
		**/
	}
	
	@Test
	public void testEquals(){
		Long a = 127L;
		Long b = 127L;
		Long c = 128L;
		Long d = 128L;
		System.out.println(a == b);
		System.out.println(c == d);
	}
}
