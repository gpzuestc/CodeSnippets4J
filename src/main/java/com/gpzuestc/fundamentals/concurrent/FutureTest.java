package com.gpzuestc.fundamentals.concurrent;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

import com.gpzuestc.User;

/**
 * @author gpzuestc
 * @date: 2014-3-17
 * @description:  
 * 
 */
public class FutureTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		Future<String> future = executor.submit(new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				System.out.println("in call 1");
				return "abc";
			}
			
		});
		
		Future<String> future1 = executor.submit(new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				System.out.println("in call 2");
				return "123";
			}
			
		});
		System.out.println(future.get());
		System.out.println(future1.get());
		
		
		
		
		FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000L);
				return "future task test";
			}
			
		});
		
		new Thread(ft).start();
		
		System.out.println(ft.get());
	}
	
	
	@Test
	public void testFuture(){
		ExecutorService es = Executors.newFixedThreadPool(20);
		final ArrayList<User> list = new ArrayList<User>();
		Future<User> future = null;
		for(int i = 0 ; i < 1000; i++){
			future = es.submit(new Callable<User>() {

				@Override
				public User call() throws Exception {
					list.add(new User());
					return null;
				}
			});
		}
		
		try {
			Thread.sleep(1000*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
