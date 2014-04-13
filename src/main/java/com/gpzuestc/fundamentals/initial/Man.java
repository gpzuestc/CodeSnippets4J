package com.gpzuestc.fundamentals.initial;

public class Man extends Person{
	private Var var1 = new Var("子类私有变量，位置1");
	public Var var2 = new Var("子类公有变量，位置2");
	
	private static Var var4 = new Var("子类私有静态变量，位置3");
	public static Var var3 = new Var("子类公有静态变量，位置4");
	
	public Man(){
		Var var5 = new Var("子类构造方法");
	}
	
	
	public static void main(String[] str){
		Man m = new Man();
	}
	
	/*
	 * static(父类，子类)，
	 * 变量（父类，子类）
	 * 构造函数（父类、子类）
	 * */
}
