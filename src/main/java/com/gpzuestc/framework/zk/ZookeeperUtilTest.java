package com.gpzuestc.framework.zk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import com.sohu.wireless.conf.ZookeeperClient;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-1-16
 * @todo: 
 * 
 */
public class ZookeeperUtilTest {
	private static CountDownLatch latch = null;
	private static int a;
	@Test
	public void testZookeeperClient() throws Exception{
		ZooKeeper zk = ZookeeperClient.getZookeeper();
		zk.setData("/test1/aaa", "mm".getBytes(), -1);
	}
	
	@Test
	public void testMultiZookeeperClient() throws Exception{
		latch = new CountDownLatch(5);
		for(int i = 0; i < 5; i++){
			Thread th = new Thread(new Runner());
//			Thread.sleep(1000);
			th.start();
		}
		latch.await(5, TimeUnit.SECONDS);
		System.out.println(a);
	}
	
	static class Runner implements Runnable{
		@Override
		public void run() {
			ZooKeeper zk = ZookeeperClient.getZookeeper();
			try {
				System.out.println(Thread.currentThread().getName());
				zk.setData("/test1/aaa", ("threadId=" + a + ":"+ Thread.currentThread().getName()).getBytes(), -1);
				a++;
				System.out.println(new String(zk.getData("/test1/aaa", null, null)));
				latch.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

