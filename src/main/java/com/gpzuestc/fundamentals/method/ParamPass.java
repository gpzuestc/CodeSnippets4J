package com.gpzuestc.fundamentals.method;

public class ParamPass {
	public int num = 0;
	
	public void change(int i){
		num = i;
	}
	
	public void change(ParamPass myClass){
		ParamPass mClass = new ParamPass();
		mClass.num = 10;
		myClass = mClass;
//		myClass.num = 10;
	}
	
	public void add(int i){
		i++;
	}
	
	public void add(ParamPass myClass){
		myClass.num++;
	}
	
	public static void main(String[] args) {
		ParamPass myClass = new ParamPass();
		myClass.change(1);
		System.out.println(myClass.num); //1
		myClass.change(myClass);
		System.out.println(myClass.num); //1
		myClass.add(myClass.num);
		System.out.println(myClass.num); //1
		myClass.add(myClass);
		System.out.println(myClass.num); //2
	}
}
