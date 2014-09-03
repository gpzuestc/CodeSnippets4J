package com.gpzuestc.fundamentals.concurrent;


/**
 * @author gpzuestc
 * @date: 2014-3-15
 * @description:  
 * 
 */
public class WaitTest {
	private static final int CAPICITY = 10;
	private int index = 0;
	private String[] stack = new String[CAPICITY];
	
	public synchronized void push(String str){
		while(index == CAPICITY){
			try {
				System.out.println("wait to push");
				wait();
				System.out.println("after wait in push");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll(); //位置只要再次之下就ok
		System.out.println("111");
		try {
			System.out.println("222");
			Thread.sleep(1500L);
			System.out.println("333");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stack[index++] = str;
		System.out.println("push index[" + (index - 1) + "] " + str);
	}
	
	public synchronized String pop(){
		while(index == 0){
			try {
				System.out.println("wait to pop");
				wait();
				System.out.println("after wait in pop");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
		String ret = stack[--index];
		System.out.println("pop index[" + index + "] " + ret);
		return ret;
	}
	
	public static void main(String[] args) {
		final int count = 30;
		final WaitTest wt = new WaitTest();
		Thread producer = new Thread(){
			public void run() {
				for(int i = 0; i < count; i++){
//					System.out.println("push:" + i);
					System.out.println("push " + currentThread().getName());
					wt.push(i + "");
				}
			};
		};
		producer.start();
		
		Thread consumer = new Thread(){
			@Override
			public void run() {
				for(int i = 0; i < count; i++){
					System.out.println("pop " + currentThread().getName());
					wt.pop();
				}
			}
		};
		consumer.start();
		
		Thread consumer2 = new Thread(){
			@Override
			public void run() {
				for(int i = 0; i < count; i++){
					System.out.println("pop2 " + currentThread().getName());
					wt.pop();
				}
			}
		};
		consumer2.start();
		
	}
}
