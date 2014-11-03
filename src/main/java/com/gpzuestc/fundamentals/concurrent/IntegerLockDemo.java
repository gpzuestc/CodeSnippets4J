package com.gpzuestc.fundamentals.concurrent;

public class IntegerLockDemo {

	public static void main(String[] args) {
		IntegerLockDemo t = new IntegerLockDemo();
		t.test();
	}
	
	private Integer a = new Integer(0);
	
	private Wraper lockObj = new Wraper();
	
	private class Wraper {
		private Integer b = 0;
		
		
		public Integer getB() {
			return b;
		}


		public void setB(Integer b) {
			this.b = b;
		}
		
	}
	
	public void test(){
		
		for(int i = 0; i < 2; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						
//						synchronized (a) {
//							a++;
//							System.out.println(a);
//							if(a.equals(3)){
//								a = 0;
//							}
//						}
						
						synchronized (lockObj) {
							lockObj.setB(lockObj.getB() + 1);;
							System.out.println(lockObj.getB());
							if(lockObj.getB().equals(3)){
								lockObj.setB(0);;
							}
						}
					}
				}
			}).start();
		}
	}
}
