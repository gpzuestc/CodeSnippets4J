package com.gpzuestc.fundamentals.initial;

public class Person {
	private Var var1 = new Var("父类私有变量，位置1");
	public Var var2 = new Var("父类公有变量，位置2");
	public static Var var3 = new Var("父类公有静态变量，位置3");
	private static Var var4 = new Var("父类私有静态变量，位置4");
	
	public Person(){
		Var var5 = new Var("父类构造方法");
	}
}
