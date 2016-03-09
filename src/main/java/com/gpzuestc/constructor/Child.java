package com.gpzuestc.constructor;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-9-25
 *
 */

public class Child extends Parent{
	
	public static Integer m = 8;
	
	public Child(){
		System.out.println("chid static var;m=" + m);
		System.out.println("child c()");
	}
	
	public  Child(int b){
		super(b); //若注释该行则调用parent的默认构造方法
		System.out.println("child c(int)");
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Child c = new Child();
		c = new Child(1);
	}
}

/**
 * 
parent c()
child c()
parent c(int)
child c(int)

 */

