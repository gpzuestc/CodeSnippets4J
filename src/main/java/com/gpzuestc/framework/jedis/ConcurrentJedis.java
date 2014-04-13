package com.gpzuestc.framework.jedis;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 27, 2012
 * 
 */
public class ConcurrentJedis implements Runnable{
	private JedisPool jp;
	private CountDownLatch latch;
	public ConcurrentJedis(JedisPool jp, CountDownLatch latch){
		this.jp = jp;
		this.latch = latch;
	}
	public void run() {
		Jedis j = null;
		try {
			j = jp.getResource(); 
			Integer i = new Random().nextInt(10);
			String key = "conc" + i;
			j.set(key, "v_" + String.valueOf(new Random().nextInt(10)));
			j.set(key, "m_" + String.valueOf(new Random().nextInt(10)));
			j.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			jp.returnBrokenResource(j);
		}finally{
			jp.returnResource(j);
			latch.countDown();
		}
	}

}
