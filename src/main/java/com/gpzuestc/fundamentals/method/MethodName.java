package com.gpzuestc.fundamentals.method;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-8-7
 *
 */

public class MethodName {
	public static void main(String[] args) {
		MethodName m = new MethodName();
		m.name();
	}
	private void name(){
		foo(print());
	}
	private int print(){
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		for(int i = 0; i < st.length; i++){
			System.out.println(i + ":" + st[i].getMethodName());
		}
		return 0;
	}
	private void foo(int a){
		
	}
}

/**
0:getStackTrace
1:print
2:name
3:main
 */


