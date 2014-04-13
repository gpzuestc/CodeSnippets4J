package com.gpzuestc.fundamentals.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author gpzuestc
 * @date: 2014-3-17
 * @description:  
 * 
 */
public class ScheduledExecutorTest {

	public static void main(String[] args) {
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		for(int i = 0; i < 10; i++){
//			executorService.schedule(new Runnable() {
			executorService.schedule(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getId());
				}
			}, 5, TimeUnit.SECONDS);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
