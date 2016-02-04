package com.gpzuestc.framework.zk.curator;

import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorRecipesTest {

	private CuratorFramework client1 = CuratorFrameworkFactory.newClient("localhost:2181,localhost:2182", new ExponentialBackoffRetry(1000, Integer.MAX_VALUE));
	
//	@Test
//	public static void main(String[] args) {
	public void testLeaderLatch(){
		final String latchPath = "/curator_recipes/leaderLatch";
		System.out.println("start");
		for(int i = 0; i < 5; i++){
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181,localhost:2182", new ExponentialBackoffRetry(1000, Integer.MAX_VALUE));
					LeaderLatch leaderLatch = new LeaderLatch(client, latchPath);
					try {
						client.start();
						leaderLatch.start();
						leaderLatch.await(100, TimeUnit.SECONDS); //阻塞等待获得leaderShip
//						System.out.println(leaderLatch.getLeader());
						System.out.println(Thread.currentThread().getName() + ":" + leaderLatch.hasLeadership() + ":" + leaderLatch.getState());
						Thread.sleep(5 * 1000L);
						
						leaderLatch.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						client.close();
					}
				}
			}).start();
		}
		System.out.println("end");
		
	}
	
	public static void main(String[] args) {
//		public void testLeaderSelector(){
			final String latchPath = "/curator_recipes/leaderSelector";
			System.out.println("start");
			for(int i = 0; i < 5; i++){
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181,localhost:2182", new ExponentialBackoffRetry(1000, Integer.MAX_VALUE));
						
						
						 final LeaderSelector leaderSelector = new LeaderSelector(client, "/led", new LeaderSelectorListenerAdapter(){  
							  
					            @Override  
					            public void takeLeadership(CuratorFramework client) throws Exception {  
					                System.out.println(Thread.currentThread().getName() + ":work ing...");  
					                Thread.currentThread().sleep(3000);  
					                System.out.println(Thread.currentThread().getName() + ":end..");  
					            }  
					              
					      	});  
						
						try {
							client.start();
							leaderSelector.autoRequeue();  
							leaderSelector.start();  
//							System.out.println(leaderLatch.getLeader());
							Thread.sleep(5000L);
							System.out.println(Thread.currentThread().getName() + ":" + leaderSelector.hasLeadership() );
							Thread.sleep(10 * 1000L);
							leaderSelector.close();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							client.close();
						}
					}
				}).start();
			}
			System.out.println("end");
			
		}
}
