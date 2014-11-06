package com.gpzuestc.framework.zk;

import java.util.Map;

import net.killa.kept.KeptMap;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-1-15
 * @todo: 
 * 
 */
public class KeptTest {
	private static ZooKeeper zk = null;
	@BeforeClass
	public static void setUp() throws Exception{
		zk = new ZooKeeper("179.36.12.21:2181,179.36.12.22:2181", 3000, null);
	}
	
	@Test
	public void testKeptmap() throws Exception{
//		zk.setData("/test1/aaa", "1234a5".getBytes(), -1);
//		System.out.println(new String(zk.getData("/test1/aaa", null, null)));
//		System.out.println(zk.getState().isAlive());
//		zk.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		Map<String, String> map = new KeptMap(zk, "/test1", Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(map.put("aaa", "11112a"));
//		System.out.println(map.size());
//		for(Map.Entry<String, String> entry : map.entrySet()){
//			System.out.println(entry.getKey());
//			System.out.println(entry.getValue());
//		}
		Thread.sleep(10);
		System.out.println("a:" + map.get("aaa"));
//		System.out.println(map.get("aaa"));
	}
	
	@Test
	public void testKeptMapSet() throws Exception{
		Map<String, String> map = new KeptMap(zk, "/test", Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		String path = "/test/abc/d";
		if(path.contains("/test/abc")){
			String sub = path.substring("test/abc".length() + 1);
			System.out.println(sub);
		}
	}
}
