package com.gpzuestc.fundamentals.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-8-7
 *
 */

public class MapTest {
	@Test
	public void map(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "guopeng");
		map.put("age","23");
		map.put("name", "zhang");
		
		System.out.println(map.keySet().contains("name"));
		System.out.println(map.get("name"));
	}
	
	@Test
	public void sortByKey(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("2", 20);
		map.put("4", 10);
		map.put("1", 30);
		map.put("3", 20);
		
		List<Map.Entry<String, Integer>> values = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		// 排序前
		System.out.println("befor:");
		for (int i = 0; i < values.size(); i++) {
			String id = values.get(i).toString();
			System.out.println(id);
		}
		
		
		Map<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.putAll(map);
		values = new ArrayList<Map.Entry<String, Integer>>(treeMap.entrySet());
		System.out.println("after:");
		for (int i = 0; i < values.size(); i++) {
			String id = values.get(i).toString();
			System.out.println(id);
		}
		
	}
	
	@Test
	public void sortByValue(){
		Map<String, Integer> map = new TreeMap<String, Integer>();

		map.put("2", 20);
		map.put("4", 10);
		map.put("1", 30);
		map.put("3", 20);
		map.put("3", map.get("3") + 1);
		map.remove("4");
		List<Map.Entry<String, Integer>> values = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		// 排序前
		System.out.println("befor:");
		for (int i = 0; i < values.size(); i++) {
			String id = values.get(i).toString();
			System.out.println(id);
		}
		// 排序
		Collections.sort(values, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue() - o2.getValue());
			}
		});
		// 排序后
		System.out.println("after:");
		for (int i = 0; i < values.size(); i++) {
			String id = values.get(i).toString();
			System.out.println(id);
		}
	}
}


