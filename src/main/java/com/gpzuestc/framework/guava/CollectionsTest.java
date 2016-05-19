package com.gpzuestc.framework.guava;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.gpzuestc.util.JsonUtil;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;

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

	@Test
	public void testImmutableMap() {
//		ImmutableMap<String, String> immutableMap =
		ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
		builder.put("1", "1");
		builder.put("2", "3");
		ImmutableMap<String, String> immutableMap = builder.build();
		System.out.println(JsonUtil.toJSONString(immutableMap));

	}
}
