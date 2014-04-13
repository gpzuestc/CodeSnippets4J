package com.gpzuestc.framework.jedis;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import redis.clients.jedis.Jedis;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 27, 2012
 * 
 */
public class WrongConcurrentJedis implements Runnable{
	private Jedis j;
	private CountDownLatch latch;
	public WrongConcurrentJedis(Jedis j, CountDownLatch latch){
		this.j = j;
		this.latch = latch;
	}
	public void run() {
		try{
			Integer i = new Random().nextInt(10);
			String key = "conc" + i;
			j.set(key, "v_" + String.valueOf(new Random().nextInt(10)));
			j.set(key, "m_" + String.valueOf(new Random().nextInt(10)));
			j.get(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			latch.countDown();
		}
	}

}
