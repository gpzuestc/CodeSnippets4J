package com.gpzuestc.fundamentals.concurrent.test;

public class IntegerLockDemo1 implements Runnable{
	private static Integer a = 0;

	@Override
	public void run() {
		for(;;){
//			synchronized (a) {
			synchronized (IntegerLockDemo1.class) {
				if(++a == 100){
					a = 0;
				}
				System.out.println(Thread.currentThread().getName() + ":" + a);
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++){
			new Thread(new IntegerLockDemo1()).start();
		}
	}
}
