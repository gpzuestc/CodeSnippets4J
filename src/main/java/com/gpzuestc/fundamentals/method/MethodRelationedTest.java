package com.gpzuestc.fundamentals.method;

import org.junit.Test;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Nov 17, 2012
 * 
 */
public class MethodRelationedTest {

	@Test
	public void testVarParams() {
		System.out.println(111);
		foo(1, "wet", 123);
		foo(2, null, "wet", 123);
		foo(3, "web", null);
		foo(4);
	}
	
	private void foo(int i, Object... objects){
		System.out.println(i);
		for(Object obj : objects){
			System.out.println(obj);
		}
	}
	
	@Test
	public void testObject(){
		Integer obj = null;
//		int a = obj;
//		System.out.print(a);
		foo(obj);
	}
	
	private void foo(int i){
		System.out.print(i);
	}

	@Test
	public void testMultiParam(){
		String[] strs = new String[]{"1", "2"};
		multiParamMethod(strs);
		multiParamMethod("3", "4");
	}
	
	public static void multiParamMethod(String... strs){
		for(String s : strs){
			System.out.println(s);
		}
	}
}
