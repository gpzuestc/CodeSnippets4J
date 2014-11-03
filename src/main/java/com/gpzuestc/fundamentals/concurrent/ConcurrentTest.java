package com.gpzuestc.fundamentals.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.openqa.selenium.support.ui.Sleeper;

/**
 * @author gpzuestc
 * @date: 2013-8-25
 * @description:  
 * 
 */
public class ConcurrentTest {
	
	private ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		
	}
	
	@Test
	public void testReentrantLock() throws Exception{
		
		
		/**
		 * java.util.concurrent.locks.ReentrantLock@16a4e743[Locked by thread main]
		 * java.util.concurrent.locks.ReentrantLock@244aeb52[Locked by thread main]
		 * java.util.concurrent.locks.ReentrantLock@329f671b[Locked by thread main]
		 */
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					reentrantLockR(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
		Thread.sleep(100);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
//					syncR(2);
					reentrantLockR(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
		reentrantLockR(2);
		syncR(2);
	}
	
	private void reentrantLockR(int i) throws Exception{
		if(i == 1){
			lock.lock();
			System.out.println(Thread.currentThread().getName() + ";" + lock.toString() + "; last");
			lock.unlock();
			return;
		}
		System.out.println(Thread.currentThread().getName() + ";" + lock.toString());
		lock.lock();
		reentrantLockR(i-1);
		Thread.sleep(1000);
		lock.unlock();
	}
	
	private void syncR(int i) throws InterruptedException{
		if(i == 1){
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() + ";" + "last sync");
			}
			return ;
		}
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + ";" + "in the sync");
			syncR(i - 1);
			Thread.sleep(100);
		}
	}
	

}
