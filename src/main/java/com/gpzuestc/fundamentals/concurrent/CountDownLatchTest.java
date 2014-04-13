package com.gpzuestc.fundamentals.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gpzuestc
 * @date: 2013-10-5
 * @description:  
 * 
 */
public class CountDownLatchTest {
	
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static Map<Integer, String> otherThings = new HashMap<Integer, String>();
	static{
		otherThings.put(0, "drink water");
		otherThings.put(1, "eat sth");
		otherThings.put(2, "washroom");
		otherThings.put(3, "walk");
		otherThings.put(4, "talk with others");
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int runnerCount = 4;
		CountDownLatch endCdl = new CountDownLatch(runnerCount);
		CountDownLatch beginCdl = new CountDownLatch(1);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i = 0; i < runnerCount; i++){
			executorService.execute(new Runner(beginCdl, endCdl, i));
		}
		Thread.sleep(100);
		beginCdl.countDown();
		System.out.println("ready go!");
		executorService.shutdown();
		endCdl.await();
		System.out.println("competition was end and begin to announce result");
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				return (o1.getValue() - o2.getValue());
			}
		});
		
		for(Map.Entry<Integer, Integer> entry : list){
			System.out.println("runner " + entry.getKey() + " : " + entry.getValue() + "ms");
		}
		
	}
	
	static class Runner implements Runnable{
		private CountDownLatch beginCdl;
		private CountDownLatch endCdl;
		private int id;
		
		public Runner(CountDownLatch beginCdl, CountDownLatch endCdl, int id){
			this.beginCdl = beginCdl;
			this.endCdl = endCdl;
			this.id = id;
		}
		
		@Override
		public void run() {
			System.out.println("runer " + id + " on the mark");
			try {
				beginCdl.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Random rand = new Random();
			int time = rand.nextInt(100);
			map.put(id, time);
			System.out.println("runner " + id + " reach finishing line");
			endCdl.countDown();
			System.out.println("runner " + id + " go to " + otherThings.get(time/20));
		}
		
	}
}


