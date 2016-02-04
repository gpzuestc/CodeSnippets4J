package com.gpzuestc.framework.zk.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import com.gpzuestc.util.JsonUtil;

public class CuratorTest {

	@Test
	public void testInit() {
		CuratorFramework client = CuratorFrameworkFactory
				.builder()
				.connectString("localhost:2181,localhost:2182")
				.sessionTimeoutMs(30000)
				.connectionTimeoutMs(30000)
				.canBeReadOnly(false)
				.retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
				.namespace("test_namespace").defaultData(null).build();
		client.start();
		try {
			client.create().creatingParentsIfNeeded().forPath("/gpzrr1/kk");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCrud() {
		String connectionString = "localhost:2181,localhost:2182";
		String PATH = "/testzk1/crud";
		CuratorFramework client = CuratorFrameworkFactory.newClient(
				connectionString, 3000, 3000, new ExponentialBackoffRetry(
						1000, Integer.MAX_VALUE));

		try {
			System.out.println("start..");
			client.start();

			client.create().creatingParentsIfNeeded().forPath(PATH, "I love messi".getBytes());

			byte[] bs = client.getData().forPath(PATH);
			System.out.println("新建的节点，data为:" + new String(bs));

			client.setData().forPath(PATH, "I love football o".getBytes());
			
			bs = client.getData().forPath(PATH);
			System.out.println("修改后的节点data为：" + new String(bs));

			// 由于是在background模式下获取的data，此时的bs可能为null
			byte[] bs2 = client.getData().watched().inBackground()
					.forPath(PATH);
			System.out.println("background模式下获取修改后的data为"
					+ new String(bs2 != null ? bs2 : new byte[0]));

			Stat stat = client.checkExists().forPath(PATH);

			// Stat就是对zonde所有属性的一个映射， stat=null表示节点不存在！
			System.out.println(JsonUtil.toJSONString(stat));
			client.delete().forPath(PATH);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseableUtils.closeQuietly(client);
		}
	}
}
