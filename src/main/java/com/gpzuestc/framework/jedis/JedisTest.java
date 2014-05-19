package com.gpzuestc.framework.jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.BeforeClass;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 27, 2012
 * 
 */
public class JedisTest {
	
	
	private static final int PORT = 6388;
//	private static final String HOST = "10.1.36.194";
	private static final String HOST = "10.10.52.163";
//	private static final int PORT = 6389;
	
	private static JedisPool jp;
	private static Jedis j;
	
	private static int errorCount = 0;
	@BeforeClass
	public static void beforeClass(){
		jp = new JedisPool(HOST, PORT);
		j = jp.getResource();
	}
	@Test
	public void test(){
		String str = j.get("video.tvapp.findAllByNId_387662052");
		System.out.println(str.length());
	}
	@Test
	public void testJedis(){
		String [] strs = new String[10];
		String key = "jdemo1";
		if(j.exists(key)){
			j.del(key);
		}
		System.out.println("exists:" + j.exists(key));
		System.out.println("set:" + j.set(key, "12"));
		System.out.println("get:" + j.get(key));
		
		List<String> list = j.mget("jdemo", "xsdfasfd", "jdemo1");
		System.out.println(list.size());
		for (String str : list){
			System.out.println("res=" + str);
		}
	}
	
	@Test
	public void testJedisException() throws InterruptedException{
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(1);
		jpc.setMaxIdle(50);
		jpc.setMaxWait(5 * 1000);
		
		JedisPool jp = new JedisPool("10.10.82.80", 6380);
		int count = 200;
		for(int i = 0; i < count;i++){
			try {
				j = jp.getResource();
				String key = "clusterTest" + i;
//				System.out.println(j.hashCode());
//				System.out.println(j.setex(key, 60, "sdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"));
				System.out.println(j.set(key, "sdeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"));
				jp.returnResource(j);
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println(e.getMessage());
				jp.returnBrokenResource(j);
//				jp.returnResource(j);
			}
			
//			Thread.sleep(2 * 1000);
		}
		for(int i = 0; i < count;i++){
			try {
				j = jp.getResource();
				String key = "clusterTest" + i;
//				System.out.println(j.hashCode());
				System.out.println(i + " get:" + j.get(key));
				jp.returnResource(j);
			} catch (Exception e) {
				e.printStackTrace();
				jp.returnBrokenResource(j);
//				jp.returnResource(j);
			}

//			Thread.sleep(2 * 1000);
		}
	}
	
	
	@Test
	public void testJedisCluster()throws Exception{
		String host = HOST;
		JedisShardInfo shardinfo = new JedisShardInfo(host, 6380);
		JedisShardInfo shardinfo1 = new JedisShardInfo(host, 6381);
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(shardinfo);
		shards.add(shardinfo1);
		
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(8);
	    jpc.setMaxIdle(5);
	    jpc.setMaxWait(1000 * 10); //等待idle object的最大时间
		ShardedJedisPool sjp = new ShardedJedisPool(jpc, shards);
		int i = 0;
		ShardedJedis sj = null;
		while(true){
			try {
				sj = sjp.getResource();
				String key = "clusterTest";
//			if(sj.exists(key)){
//				sj.del(key);
//			}
				System.out.println("exists:" + sj.exists(key));
				System.out.println("set:" + sj.set(key, "a" + i));
				System.out.println("get:" + sj.get(key));
//				sjp.returnResource(sj);
			} catch (Exception e) {
				e.printStackTrace();
//				sjp.returnBrokenResource(sj);
			}
			i++;
			Thread.sleep(1000);
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
	public void testWrongConcurrentJedis() throws InterruptedException{
		long begin = System.currentTimeMillis();	
		int count = 5;
		JedisPool jp = new JedisPool(HOST, 6379);
		Jedis j = jp.getResource();
		CountDownLatch latch = new CountDownLatch(count);
		WrongConcurrentJedis wcj = new WrongConcurrentJedis(j, latch);
		try {
			for(int i = 0; i < 5; i++){
				new Thread(wcj).start();
			}
			latch.await();
		} catch (Exception e) {
			e.printStackTrace();
			jp.returnBrokenResource(j);
		}finally{
			jp.returnResource(j);
		}
		
		System.out.println("execute time:" + (System.currentTimeMillis() - begin) + "ms");

	}
	
	@Test
	public void testExeTime(){
		System.out.println("5000 hset entries:");
		findSuitableHmaxLen(512);
		findSuitableHmaxLen(1000);
		findSuitableHmaxLen(2000);
		findSuitableHmaxLen(3000);
		findSuitableHmaxLen(4000);
		findSuitableHmaxLen(5000);

	}


	private void findSuitableHmaxLen(Integer len) {
		flushAndWait();
		String pattern = "hash-max-zipmap-entries";
		j.configSet(pattern, len.toString());
		String key = "talk_desc";
		int begin = 1000;
		int end = 6000;
		long ts = System.currentTimeMillis();
//		for(int i = begin; i < end; i++){
//			j.set(key + i, "a12345" + i);
//		}
//		System.out.println("set string exe time:" + (System.currentTimeMillis() - ts));
		
//		ts = System.currentTimeMillis();
		for (int i = begin; i < end; i++) {
			j.hset(key, String.valueOf(i), "a12345" + i);
		}
		long total =System.currentTimeMillis() - ts;
		System.out.println(j.configGet(pattern) + "\ttime:" + total);
	}
	
	@Test
	public void testSaveObject() throws Exception{
		int count = 5500;
		
		System.out.println("1、set  msg_id  serialized_Bytes");
		flushAndWait();
		long before = getRedisUsedMem();
		System.out.println("before_mem:" + before);
		long st = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			j.set(("msg_" + i).getBytes(), HessianSerializeUtil.encode(new Msg(i)));
		}
		long et = System.currentTimeMillis();
		long after = getRedisUsedMem();
		System.out.println("after_mem: " + after);
		System.out.println("used_mem:  " + (after - before));
		System.out.println("used_time: " + (et - st) + "\n");
		
		System.out.println("2、set  msg_id_field  value");
		flushAndWait();
		before = getRedisUsedMem();
		System.out.println("before_mem:" + before);
		st = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			Msg msg = new Msg(i);
			j.set("msg_" + i + "_suid", String.valueOf(msg.getSuid()));
			j.set("msg_" + i + "_ruid", String.valueOf(msg.getRuid()));
			j.set("msg_" + i + "_content", msg.getContent());
			j.set("msg_" + i + "_title", msg.getTitle());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			j.set("msg_" + i + "_date",	sdf.format(msg.getDate()));
		}
		et = System.currentTimeMillis();
		after = getRedisUsedMem();
		System.out.println("after_mem: " + after);
		System.out.println("used_mem:  " + (after - before));
		System.out.println("used_time: " + (et - st) + "\n");
		
		System.out.println("3、hset  msg_id  field  value");
		flushAndWait();
		before = getRedisUsedMem();
		System.out.println("before_mem:" + before);
		st = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			Msg msg = new Msg(i);
			j.hset("msg_" + i , "suid", String.valueOf(msg.getSuid()));
			j.hset("msg_" + i , "ruid", String.valueOf(msg.getRuid()));
			j.hset("msg_" + i , "content", msg.getContent());
			j.hset("msg_" + i , "title", msg.getTitle());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			j.hset("msg_" + i , "date",	sdf.format(msg.getDate()));
		}
		et = System.currentTimeMillis();
		after = getRedisUsedMem();
		System.out.println("after_mem: " + after);
		System.out.println("used_mem:  " + (after - before));
//		System.out.println(j.hgetAll("msg_1"));
		System.out.println("used_time: " + (et - st) + "\n");
		
		
		System.out.println("4、hset  msg_filed_pre  mod  value");
		flushAndWait();
		before = getRedisUsedMem();
		System.out.println("before_mem:" + before);
		st = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			Msg msg = new Msg(i);
			int d = i / 1000;
			int m = i % 1000;
			j.hset("msg_suid_" + d, String.valueOf(m), String.valueOf(msg.getSuid()));
			j.hset("msg_ruid_" + d, String.valueOf(m), String.valueOf(msg.getRuid()));
			j.hset("msg_content_" + d, String.valueOf(m), msg.getContent());
			j.hset("msg_title_" + d, String.valueOf(m), msg.getTitle());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			j.hset("msg_date_" + d, String.valueOf(m), sdf.format(msg.getDate()));
		}
		et = System.currentTimeMillis();
		after = getRedisUsedMem();
		System.out.println("after_mem: " + after);
		System.out.println("used_mem:  " + (after - before));
		System.out.println("used_time: " + (et - st) + "\n");
	}

	private void flushAndWait(){
		j.flushAll();
//		Thread.sleep(20*1000);
	}
	
	private long getRedisUsedMem(){
		return Long.valueOf(j.info().split("\r\n")[19].split(":")[1]);
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
		
		String host = "10.10.82.80";
//		String host = "10.10.52.181";
//		String host = "10.10.52.200";
		int port = 6388;
		JedisPool jp = new JedisPool(host, port);
		for(int i = 0;;i++){
			try {
				j = jp.getResource();
				String key = "haha1";
				System.out.println("exists:" + j.exists(key));
//				System.out.println(i + " get:" + j.set(key, i+""));
				jp.returnResource(j);
				System.out.println(j);
			} catch (Exception e) {
				e.printStackTrace();
				jp.returnBrokenResource(j);
			}

			Thread.sleep(5 * 1000);
		}
	}
	
	
	@Test
	public void video4ServerDel() throws Exception{
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(8);
		jpc.setMaxIdle(50);
		jpc.setMaxWait(5 * 1000);
		
		List<String> hostList = new ArrayList<String>();
		hostList.add("10.10.77.156");
		hostList.add("10.10.76.126");
		hostList.add("10.16.12.94");
		hostList.add("10.10.76.154");
		hostList.add("192.168.99.164");
		
		int port = 6388;
		for(String host : hostList){
			JedisPool jp = new JedisPool(host, port);
			
			j = jp.getResource();
//			j.del("find_album_playcount_by_playlist_id_6494271");
			for(int i = 1; i <= 399; i++){
				System.out.println(j.del("video4.albumEntity_" + i));
			}
			System.out.println(j.del("video4.tvVerRelation.findAllByTvIdAndVersionId_1206424_1"));
//			System.out.println(HessianSerializeUtil.decode(bArr)));
			jp.returnResource(j);
//			for(Long id : tvIds){
//				try {
//					j = jp.getResource();
////					String key2 = "video.vrsTvSetExtend_" + id;
//					String key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 2;
//					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
//					
//					key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 1;
//					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
//					
//					key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 21;
//					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
//					
//					key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 31;
//					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
//					jp.returnResource(j);
//				} catch (Exception e) {
//					e.printStackTrace();
//					jp.returnBrokenResource(j);
//				}
//				
//			}
		}
	}
	@Test
	public void videoServerDel() throws Exception{
		JedisPoolConfig jpc = new JedisPoolConfig();
		jpc.setMaxActive(8);
		jpc.setMaxIdle(50);
		jpc.setMaxWait(5 * 1000);
		
		List<String> hostList = new ArrayList<String>();
//		hostList.add("10.10.52.184");
//		hostList.add("10.10.53.56");
//		hostList.add("10.16.15.136");
//		hostList.add("10.10.76.122");
//		hostList.add("10.16.15.129");
//		hostList.add("10.11.52.194");
		hostList.add("10.10.52.163");
		
		List<Long> vids = new ArrayList<Long>();
//		vids.add(1629779L);
//		vids.add(1629663L);
		vids.add(8374L);
		
		List<Long> tvIds = new ArrayList<Long>();
//		tvIds.add(1166855L);
//		tvIds.add(1166808L);
		tvIds.add(4176L);
		
		int port = 6388;
		for(String host : hostList){
			JedisPool jp = new JedisPool(host, port);
			
			for(Long id : tvIds){
				try {
					j = jp.getResource();
//					String key2 = "video.vrsTvSetExtend_" + id;
					String key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 2;
					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
					
					key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 1;
					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
					
					key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 21;
					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
					
					key2 = "video.tvVerRelation.findAllByTvIdAndVersionId_" + id + "_" + 31;
					System.out.println(host + ":" + key2 +" res:" + j.del(key2));
					jp.returnResource(j);
				} catch (Exception e) {
					e.printStackTrace();
					jp.returnBrokenResource(j);
				}
				
			}
			
			for(Long id : vids){
				try {
					j = jp.getResource();
					String key1 = "video_object_" + id;
					System.out.println(host + ":" + key1 +" res:" + j.del(key1));
					jp.returnResource(j);
				} catch (Exception e) {
					e.printStackTrace();
					jp.returnBrokenResource(j);
				}
				
			}
		}
	}
	
}
