package com.gpzuestc.fundamentals.concurrent.test;

import java.util.concurrent.TimeUnit;

public class IntegerLockDemo {

	public static void main(String[] args) {
		IntegerLockDemo t = new IntegerLockDemo();
		t.test();
	}
	
	private Integer a = new Integer(0);
	
//	private Wraper lockObj = new Wraper();
//	
//	private class Wraper {
//		private Integer b = 0;
//		
//		
//		public Integer getB() {
//			return b;
//		}
//
//
//		public void setB(Integer b) {
//			this.b = b;
//		}
//		
//	}
	
	public void test(){
		
		System.out.println(this);
		System.out.println(this.getClass());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < 5; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						
//						synchronized (a) {
						System.out.println(this);
						System.out.println(this.getClass());
						synchronized (this.getClass()) {
							a++;
							System.out.println(a);
							if(a.equals(3)){
								a = 0;
							}
						}
						
//						synchronized (lockObj) {
//							lockObj.setB(lockObj.getB() + 1);;
//							System.out.println(lockObj.getB());
//							if(lockObj.getB().equals(3)){
//								lockObj.setB(0);;
//							}
//						}
					}
				}
			}).start();
		}
	}
}
