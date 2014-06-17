package com.gpzuestc.framework.memcache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.Test;

/**
 * @author gpzuestc
 * @date: 2014-2-8
 * @description:  
 * 
 */
public class XmemcachedTest {
	
	
	@Test
	public void testCluterFailover() throws IOException, TimeoutException, InterruptedException, MemcachedException{
		int count = 20;
		String addrs = "10.10.77.156:11211 10.10.77.159:11212";
//		String addrs = "10.10.77.159:11211";
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(addrs);
		MemcachedClient c = builder.build();
		String key = "c";
		
		/**
		 * step 1: kill 10.10.77.159:11212 when i = 9
		 */
		for(int i = 0 ; i < count; i++){
			c.add(key + i, 60, "aaaa" + i);
			Thread.sleep(1000);
			System.out.println(i);
			System.out.println(getServerList(c));
		}
		
		
//		addrs = "10.10.77.156:11211";
//		builder = new XMemcachedClientBuilder(addrs);
//		c = builder.build();
		for(int i = 0; i< count; i++){
			System.out.println(key + i + ":" + c.get(key + i));
			System.out.println(getServerList(c));
		}
		
		Thread.sleep(20000);
		System.out.println();
		/**
		 * step 2: recover down server;
		 */
		System.out.println(getServerList(c));
		for(int i = 0; i< count; i++){
			System.out.println(key + i + ":" + c.get(key + i));
		}
		
	}
	
	/**
	 * 
0
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
1
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
2
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
3
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
4
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
5
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
6
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
7
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
8
;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
9
;10.10.77.156:11211(weight=1);
10
;10.10.77.156:11211(weight=1);
11
;10.10.77.156:11211(weight=1);
12
;10.10.77.156:11211(weight=1);
13
;10.10.77.156:11211(weight=1);
14
;10.10.77.156:11211(weight=1);
15
;10.10.77.156:11211(weight=1);
16
;10.10.77.156:11211(weight=1);
17
;10.10.77.156:11211(weight=1);
18
;10.10.77.156:11211(weight=1);
19
;10.10.77.156:11211(weight=1);


;10.10.77.156:11211(weight=1);
c0:null
c1:aaaa1
c2:null
c3:aaaa3
c4:null
c5:aaaa5
c6:null
c7:aaaa7
c8:null
c9:aaaa9
c10:aaaa10
c11:aaaa11
c12:aaaa12
c13:aaaa13
c14:aaaa14
c15:aaaa15
c16:aaaa16
c17:aaaa17
c18:aaaa18
c19:aaaa19


;10.10.77.159:11212(weight=1);10.10.77.156:11211(weight=1);
c0:null
c1:aaaa1
c2:null
c3:aaaa3
c4:null
c5:aaaa5
c6:null
c7:aaaa7
c8:null
c9:aaaa9
c10:aaaa10
c11:null
c12:aaaa12
c13:null
c14:aaaa14
c15:null
c16:aaaa16
c17:null
c18:aaaa18
c19:null
	 */

	private String getServerList(MemcachedClient c) {
		String serverList = ";";
		for(String server : c.getServersDescription()){
			serverList += server;
			serverList += ";";
		}
		return serverList;
	}
	
	@Test
	public void testOverSave() throws IOException, TimeoutException, InterruptedException, MemcachedException{
		String addr = "10.10.53.81:11211";
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(addr));
		MemcachedClient c = builder.build();
		String key = "abc";
//		c.set(key, 0, "woshishui");
		System.out.println(c.get(key));
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 2000; i++){
			sb.append("0123456789012345678abcdef" + i);
			sb.append("lknlipwefn.sndf;jpa" + i);
			sb.append("0123456789012345678abcdef" + i);
			sb.append("xeew;a;j;p32sl.,123rds23456789okjnb" + i);
			sb.append("0123456789012345678abcdef" + i);
			sb.append("ertyukmnbvcftyuik, cfgjbvc" + i);
		}
		String val = sb.toString();
		System.out.println(val.length());
		int count = 72;
		for(int i = 0; i < count; i++){
			c.set(key + i, 0, i + val);
		}
		for(int i = 0; i < count; i++){
			if(c.get(key + i) != null){
				System.out.println((key + i) + "=" + c.get(key + i).toString().substring(0, 30));
			}else{
				System.out.println(key + i + "=null");
			}
		}
	}
	
	
	@Test
	public void testSet() throws IOException, TimeoutException, InterruptedException, MemcachedException{
		String addr = "10.10.53.81:11211";
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(addr));
		MemcachedClient c = builder.build();
		String key = "abc";
		c.set(key, 60, "woshishui");
		System.out.println(c.get(key));
	}
	
	@Test
	public void testGet() throws IOException, TimeoutException, InterruptedException, MemcachedException{
		String addr = "10.10.77.159:11211";
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(addr));
		MemcachedClient c = builder.build();
		System.out.println(c.get("http://s.sohuno.com:8080/stream/stream/video?stream_id=3&max_id=20&min_id=1&pull_down=1&page_size=2"));;
//		System.out.println(c.get("http://re.sohuno.com:7777/recommend/recommend_simple?&vid=0&cid=1300&pageNum=1&pageSize=20"));;
	}
	
	
	@Test
	public void testDel() throws TimeoutException, InterruptedException, MemcachedException, IOException{
		
		List<String> hostList = new ArrayList<String>();
		hostList.add("10.10.52.189");
		hostList.add("10.10.53.173");
		hostList.add("10.16.15.130");
		hostList.add("10.10.53.181");
		hostList.add("10.16.15.144");
		
		for(String host : hostList){
			String addr = host + ":11211";
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(addr));
			MemcachedClient c = builder.build();
			String key = "getVideoObject--1631679-false-true-17";
			System.out.println(c.delete(key));
		}
	}
	
	@Test
	public void testDelV4() throws TimeoutException, InterruptedException, MemcachedException, IOException{
		
		List<String> hostList = new ArrayList<String>();
		hostList.add("10.10.76.125");
		hostList.add("10.10.76.149");
		hostList.add("10.16.12.90");
		hostList.add("192.168.99.159");
		
		for(String host : hostList){
			String addr = host + ":11211";
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(addr));
			MemcachedClient c = builder.build();
//			String key = String.format("video_by_vid_aid_site_plat_%s_%s_%s_%s", 1648437, 0, 1, 17);
			String key = String.format("video_by_vid_aid_site_plat_%s_%s_%s_%s", 1823474, null, 1, 17);
			
			System.out.println(key);
			System.out.println(c.delete(key));
		}
	}
}
