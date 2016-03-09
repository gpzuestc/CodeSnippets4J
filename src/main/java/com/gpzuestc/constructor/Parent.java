package com.gpzuestc.constructor;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-9-25
 *
 */

public abstract class Parent {
	
	public static Integer a = 1;
	public static Integer b = 2;
	static {
		b = 2;
	}
	public Parent(){
		System.out.println("static var;a=" + a);
		System.out.println("static var;b=" + b);
		System.out.println("parent c()");
	}
	public Parent(int b){
		System.out.println("parent c(int)");
	}
}


