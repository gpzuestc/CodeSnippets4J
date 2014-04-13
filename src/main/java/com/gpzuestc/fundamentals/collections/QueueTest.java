package com.gpzuestc.fundamentals.collections;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-5-24
 * @todo: 
 * 
 */
public class QueueTest {
	
	@Test
	public void testQueue(){
		Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(5);
		//不会抛异常
		System.out.println(queue.offer(100));
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		
		//会抛异常
		System.out.println(queue.add(111));
		System.out.println(queue.element());
		System.out.println(queue.remove());
	}
}
