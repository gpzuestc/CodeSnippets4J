package com.gpzuestc.fundamentals.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

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
	
//	public static void main(String[] args) {
//		int count = 2;
////		ExecutorService pool = Executors.newCachedThreadPool();
//		ExecutorService pool = Executors.newFixedThreadPool(count);
//		for(int i = 0; i < 3; i++){
//			pool.submit(new Runnable() {
//				
//				@Override
//				public void run() {
//					try {
//						System.out.println("sleep");
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			});
//		}
//	}
	
	
//	public static void main(String[] args) throws IOException, InterruptedException {  
//	}  
	
	@Test
	public void testFixed(){
		ExecutorService service = Executors.newFixedThreadPool(2);  
		for (int i = 0; i < 6; i++) {  
			final int index = i;  
			System.out.println("task: " + (i+1));  
			Runnable run = new Runnable() {  
				@Override  
				public void run() {  
                    System.out.println("thread start" + (index+1) + " " + Thread.currentThread().getName());  
					try {  
						Thread.sleep(Long.MAX_VALUE);  
					} catch (InterruptedException e) {  
						e.printStackTrace();  
					}  
					System.out.println("thread end" + (index + 1) + " " + Thread.currentThread().getName());  
				}  
			};  
			service.execute(run);  
		}  
	}
	
	/*
	task: 1
	task: 2
	task: 3
	thread start1 pool-1-thread-1
	thread start2 pool-1-thread-2
	task: 4
	task: 5
	task: 6
	*/
	
//	@Test
//	public void testCustomFixed(){
	public static void main(String[] args){
	
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(6);  
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());  
          
        for (int i = 0; i < 20; i++) {  
            final int index = i;  
            System.out.println("task: " + (index+1));  
            Runnable run = new Runnable() {  
                @Override  
                public void run() {  
                    System.out.println("thread start" + (index+1) + " " + Thread.currentThread().getName()); 
                    try {  
//                    	if("main".equals(Thread.currentThread().getName())){
//                    		throw new RuntimeException();
//                    	}
//                        Thread.sleep(Long.MAX_VALUE);
                    	Thread.sleep(5*1000);
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                    System.out.println("thread end" + (index+1) + " " + Thread.currentThread().getName());  
                }  
            };  
            executor.execute(run);  
        }  
    }  
	/*
	task: 1
	task: 2
	task: 3
	thread start1 pool-1-thread-1
	thread start2 pool-1-thread-2
	task: 4
	task: 5
	thread start3 pool-1-thread-3
	task: 6
	task: 7
	task: 8
	task: 9
	task: 10
	thread start10 main
	*/
}
