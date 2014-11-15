package com.gpzuestc.fundamentals.extend;

public abstract class AbstractBaseClass {
	final 
	int a; //final filed not init here, must be in constructor
	String b;

	public AbstractBaseClass(int a) {
		super();
		this.a = a;
	}
	
	public AbstractBaseClass(int a, String b){
		this.a = a;
		this.b = b;
	}
	
}
