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
			String key = String.format("video_by_vid_aid_site_plat_%s_%s_%s_%s", 1648437, 0, 1, 17);
			System.out.println(key);
			System.out.println(c.delete(key));
		}
	}
}
