package com.gpzuestc.fundamentals.collections;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

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
		Queue<Long> queue = new ConcurrentLinkedQueue<Long>();
		
//		queue = new ArrayBlockingQueue<Integer>(10);
		queue.add(1L);
		queue.add(2L);
		queue.add(5L);
		
		String a = "a";
		System.out.println(a.hashCode());
		
		//不会抛异常
		System.out.println(queue.offer(100L));
		System.out.println(queue.peek()); //返回不移除
		System.out.println(queue.poll()); //回移返除
		
		//会抛异常
		System.out.println(queue.add(111L));
		System.out.println(queue.element()); //返回不移除
		System.out.println(queue.remove()); //返回移除
		
	}
	
	@Test
	public void testDelayQueue() throws InterruptedException{
		DelayQueue<DelayedObj> delayQueue = new DelayQueue<DelayedObj>();
		delayQueue.add(new DelayedObj(1 * 1000 * 1000l));
		delayQueue.add(new DelayedObj(2* 1000 * 1000l));
		System.out.println(delayQueue.poll());
		
		Thread.sleep(1100l);
		
		System.out.println(delayQueue.poll());
		
		Thread.sleep(2200l);
		
		System.out.println(delayQueue.poll());
	}
	
	public class DelayedObj implements Delayed{
		
		private final long NANO_ORIGIN = System.nanoTime();
		private long time;
		
		public DelayedObj(long time){
			this.time = time;
		}
		

		@Override
		public int compareTo(Delayed o) {
			if (o == this) // compare zero ONLY if same object
				return 0;
			long d = (getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
			return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(time - (System.nanoTime() - NANO_ORIGIN), TimeUnit.NANOSECONDS);
		}

		@Override
		public String toString() {
			return String.valueOf(this.time);
		}
	}
	
}
