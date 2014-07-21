package com.gpzuestc.framework.jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 27, 2012
 * 
 */
public class BasicJedisDemo {
	
	
	private static final int PORT = 6388;
//	private static final String HOST = "10.1.36.194";
	private static final String HOST = "10.10.52.163";
//	private static final int PORT = 6389;
	
	private static JedisPool jp;
	private static Jedis j;
	
	private static int errorCount = 0;

	
	
	@Test
	public void testJedisCluster()throws Exception{
//		String host = HOST;
		String host = "10.10.82.80";
		JedisShardInfo shardinfo = new JedisShardInfo(host, 6388);
		JedisShardInfo shardinfo1 = new JedisShardInfo(host, 6380);
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(shardinfo);
		shards.add(shardinfo1);
		
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(8);
	    jpc.setMaxIdle(5);
	    jpc.setMaxWait(1000 * 10); //等待idle object的最大时间
//	    jpc.setTestOnBorrow(true); 一旦设置该值
		ShardedJedisPool sjp = new ShardedJedisPool(jpc, shards);
		int i = 0;
		ShardedJedis sj = null;
		while(true){
			try {
				sj = sjp.getResource();
				String key = "clusterTest" + i;
				System.out.println(key);
				System.out.println(sj.getShardInfo(key).getPort());
//				System.out.println("set:" + sj.set(key, "a" + i));
				System.out.println("get:" + sj.get(key));
//				sjp.returnResource(sj);
			} catch (Exception e) {
				e.printStackTrace();
				sjp.returnBrokenResource(sj);
			}finally{
				sjp.returnResource(sj);
			}
			i++;
			Thread.sleep(2000);
		}
	}
	
	
	@Test
	public void testConcurrentJedis() throws InterruptedException{
		long begin = System.currentTimeMillis();
		int count = 5;
		CountDownLatch latch = new CountDownLatch(count);
		JedisPool jp = new JedisPool(HOST, 6379);
		ConcurrentJedis cj = new ConcurrentJedis(jp, latch);
		for (int i = 0; i < count; i++) {
			new Thread(cj).start();
		}
		latch.await();
		System.out.println("execute time:" + (System.currentTimeMillis() - begin) + "ms");
	}
	
	
	@Test
	public void testMaxActiveConnect() throws Exception{

		//data 
		StringBuilder sb = new StringBuilder();
		int dataLen = 1024;
		for(int i = 0; i < dataLen; i++){
			sb.append("123456789a");
		}
		String data = sb.toString();
		
		int concurrentCount = 5;
		int queryCount = 50000;
		int activeCount = 256;
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(activeCount);
	    jpc.setMaxIdle(activeCount);
	    jpc.setMaxWait(1000);
		JedisPool jp = new JedisPool(jpc, HOST, PORT, 5000); //2000 may be too small;
		
		Thread.sleep(500);
		CountDownLatch cdl = new CountDownLatch(concurrentCount);

		long begin = System.currentTimeMillis();
		for(int i = 0; i < concurrentCount; i++){
			new Thread(new RunJedis(jp, queryCount, data.getBytes(), cdl)).start();
		}
		cdl.await();
		long useTime = System.currentTimeMillis() - begin;
		System.out.println("useTime=" + useTime + "ms");
		System.out.println("totalCount=" + concurrentCount * queryCount);
		System.out.println("timePerQuery=" + useTime * 1.0 / (concurrentCount * queryCount) + "ms");
		System.out.println("qps=" + concurrentCount * queryCount * 1000 / useTime);
		System.out.println("errorCount=" + errorCount);
	}
	
	class RunJedis implements Runnable{
		private JedisPool jp;
		private int queryCount;
		private byte[] data;
		private CountDownLatch cdl;
		public RunJedis(JedisPool jp, int queryCount, byte[] data, CountDownLatch cdl){
			this.jp = jp;
			this.queryCount = queryCount;
			this.data = data;
			this.cdl = cdl;
		}
		@Override
		public void run() {
//			System.out.println("threadId=" + Thread.currentThread().getId());
			Jedis j = null;
			for(int i = 0; i < queryCount; i++){
//				String key =  "testKey" + Thread.currentThread().getId() + i;
				String key = "testKey120";
				try {
					j = jp.getResource();
//					j.set(key.getBytes(), data);
//					j.get(key.getBytes());
					j.del(key.getBytes());
//					System.err.println(key);
					jp.returnResource(j);
				} catch (Exception e) {
//					System.err.println(key);
					e.printStackTrace();
					jp.returnBrokenResource(j);
					errorCount++;
				}
			}
			cdl.countDown();
		}
	
	}

	@Test
	public void testHA() throws Exception{
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(8);
		jpc.setMaxIdle(50);
		jpc.setMaxWait(5 * 1000);
		
		String host = "10.10.76.100";
		int port = 6379;
//		JedisPool jp = new JedisPool(host, port);
		
		JedisShardInfo jsi = new JedisShardInfo(host, port);
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
		list.add(jsi);
		ShardedJedisPool jp = new ShardedJedisPool(jpc, list);
		ShardedJedis j = null;
		
		for(int i = 0;;i++){
			try {
//				j = jp.getResource();
				j = jp.getResource();
				String key = "haha1";
				System.out.println("exists:" + j.exists(key));
				System.out.println(i + " get:" + j.set(key, i+""));
//				jp.returnResource(j);
				jp.returnResource(j);
			} catch (Exception e) {
				e.printStackTrace();
				jp.returnBrokenResource(j);
			}

			Thread.sleep(2 * 1000);
		}
	}

	@Test
	public void testPipline(){
		String host = "127.0.0.1";
		Integer port = 6379;
		JedisPool jp = new JedisPool(host, port);
		
		Jedis j = jp.getResource();
		try {
			Pipeline pip = j.pipelined();
			pip.setex("aa", 60, "aa1");
			pip.setex("bb", 60, "bb2");
//			pip.sync();
			List<Object> result = pip.syncAndReturnAll();
			for(Object obj : result){
				System.out.println(obj);
			}
			jp.returnResource(j);
		} catch (Exception e) {
			e.printStackTrace();
			jp.returnBrokenResource(j);
		}
		
	}
	
	/*
	127.0.0.1:6379> PUBLISH hi "can you see me"
	
	onSubscribe
	onMessage
	channel:hi
	message:can you see me
	*/
	@Test
	public void testPubSub(){
		String host = "127.0.0.1";
		Integer port = 6379;
		JedisPool jp = new JedisPool(host, port);
		
		Jedis j = jp.getResource();
		try {
			j.subscribe(new JedisPubSub() {
				
				@Override
				public void onUnsubscribe(String channel, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onUnsub");
				}
				
				@Override
				public void onSubscribe(String channel, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onSubscribe");
				}
				
				@Override
				public void onPUnsubscribe(String pattern, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onPUnsubscribe");
				}
				
				@Override
				public void onPSubscribe(String pattern, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onPSubscribe");
				}
				
				@Override
				public void onPMessage(String pattern, String channel, String message) {
					// TODO Auto-generated method stub
					System.out.println("onPMessage");
					System.out.println("channel:" + channel);
					System.out.println("message:" + message);
				}
				
				@Override
				public void onMessage(String channel, String message) {
					System.out.println("onMessage");
					System.out.println("channel:" + channel);
					System.out.println("message:" + message);
					
				}
			}, new String[]{"hi"});
			
			jp.returnResource(j);
		} catch (Exception e) {
			e.printStackTrace();
			jp.returnBrokenResource(j);
		}
		
	}
	/*
	127.0.0.1:6379> PUBLISH hi_2 "can you see me"
	127.0.0.1:6379> PUBLISH hi_ "can you see me"
	
	onPSubscribe
	onPMessage
	channel:hi_2
	message:can you see me
	onPMessage
	channel:hi_
	message:can you see me
	*/
	
	@Test
	public void testPatternPubSub(){
		String host = "127.0.0.1";
		Integer port = 6379;
		JedisPool jp = new JedisPool(host, port);
		
		Jedis j = jp.getResource();
		try {
			j.psubscribe(new JedisPubSub() {
				
				@Override
				public void onUnsubscribe(String channel, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onUnsub");
				}
				
				@Override
				public void onSubscribe(String channel, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onSubscribe");
				}
				
				@Override
				public void onPUnsubscribe(String pattern, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onPUnsubscribe");
				}
				
				@Override
				public void onPSubscribe(String pattern, int subscribedChannels) {
					// TODO Auto-generated method stub
					System.out.println("onPSubscribe");
				}
				
				@Override
				public void onPMessage(String pattern, String channel, String message) {
					// TODO Auto-generated method stub
					System.out.println("onPMessage");
					System.out.println("channel:" + channel);
					System.out.println("message:" + message);
				}
				
				@Override
				public void onMessage(String channel, String message) {
					System.out.println("onMessage");
					System.out.println("channel:" + channel);
					System.out.println("message:" + message);
					
				}
			}, new String[]{"hi_*"});
			
			jp.returnResource(j);
		} catch (Exception e) {
			e.printStackTrace();
			jp.returnBrokenResource(j);
		}
		
	}
	
	public static void main(String[] args) {
//	@Test
//	public void test() throws Exception{
		
		String host = "10.10.82.80";
		int port = 6300;
		
		//data 
		StringBuilder sb = new StringBuilder();
		int dataLen = 500;
		for(int i = 0; i < dataLen; i++){
			sb.append("123456789a");
		}
		final String data = sb.toString();
		
//		int concurrentCount = 5;
//		int queryCount = 50000;
		int activeCount = 256;
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(activeCount);
	    jpc.setMaxIdle(activeCount);
	    jpc.setMaxWait(1000);
		final JedisPool jp = new JedisPool(jpc, host, port, 5000); //2000 may be too small;
		final SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		for(int k = 0; k < 3; k++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					for(long i = 0;;i++){
						String key = Thread.currentThread().getName() + "a" + i;
						try {
							System.out.println("[" + sdf.format(new Date(System.currentTimeMillis())) + "] " + key);
							Jedis j = jp.getResource();
							j.set(key, data);
//							j.get(key);
							jp.returnResource(j);
						} catch (Exception e) {
//				System.err.println(key);
							e.printStackTrace();
							jp.returnBrokenResource(j);
						}
					}
						
				}
			}).start();;
		}
		
	}
}
