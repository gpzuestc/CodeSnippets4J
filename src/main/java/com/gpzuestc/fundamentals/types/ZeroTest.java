package com.gpzuestc.fundamentals.types;

import org.junit.Test;

/**
 * Description
 * 
 * @author guopengzhang@sohu-inc.com @2012-8-16
 * 
 */
public class ZeroTest {
	public static void main(String[] args) {
		double R_Zero = 0.0;
		double R_PosInf = 1.0 / R_Zero;
		double R_NegInf = -1.0 / R_Zero;
		double R_Nan = R_Zero / R_Zero;
		System.out.println(R_PosInf);
		System.out.println(R_NegInf);
		System.out.println(R_Nan);
	}
	
	@Test
	public void testLong(){
		Long a = null;
		long b = a;
		System.out.println(b);
	}
}
