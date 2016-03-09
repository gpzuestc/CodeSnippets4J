package com.gpzuestc.framework.guava;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;

public class CollectionsTest {
	
	@Test
	public void testMultiMap(){
		 HashMultimap<Integer, Integer> map = HashMultimap.create(); 
		 map.put(20, 1);
		 map.put(20, 2);
		 map.put(21, 3);
		 
		 System.out.println(map.get(20));
		 System.out.println("map_size:" + map.size());
		 
		 for(Map.Entry<Integer, Integer> entry : map.entries()){
			 System.out.println(entry.getKey() + ":" + entry.getValue());
		 }
		 
		 System.out.println();
		 map.clear();
		 
		 for(int i = 0; i < 10; i++){ 
		    map.put(new Random().nextInt(5), i); 
		 }
		 for(Map.Entry<Integer, Integer> entry : map.entries()){
			 System.out.println(entry.getKey() + ":" + entry.getValue());
		 }
	}
	
	@Test
	public void test(){
		Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
	}
}
