package com.gpzuestc.fundamentals.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author gpzuestc
 * @date: 2013-10-8
 * @description:  
 * 
 */
public class ExecutorServiceTest {
	
	@Test
	public void testFixedThreadPool() throws InterruptedException{
		int count = 2;
		ExecutorService pool = Executors.newFixedThreadPool(count);
//		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i = 0; i < 3; i++){
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println("sleep");
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	public static void main(String[] args) {
		int count = 2;
//		ExecutorService pool = Executors.newCachedThreadPool();
		ExecutorService pool = Executors.newFixedThreadPool(count);
		for(int i = 0; i < 3; i++){
			pool.submit(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println("sleep");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
}
