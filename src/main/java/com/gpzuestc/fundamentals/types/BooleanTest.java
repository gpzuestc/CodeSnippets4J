package com.gpzuestc.fundamentals.types;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-4
 * @todo: 
 * 
 */
public class BooleanTest {
	@Test
	public void test(){
		Boolean b = null;
		if(b){  //NullPointerException
			System.out.println("1");
		}else{
			System.out.println("2");
		}
	}
}
