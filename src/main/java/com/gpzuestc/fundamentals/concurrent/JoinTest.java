package com.gpzuestc.fundamentals.concurrent;

/**
 * @author gpzuestc
 * @date: 2014-3-15
 * @description:  
 * 
 */
public class JoinTest {

	public static void main(String[] args) {
		System.out.println("get ready to launch a new thread");
		Thread t = new Thread(){
			public void run() {
				System.out.println("in new thread");
				try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("wake up");
			};
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("join end");
	}
}
