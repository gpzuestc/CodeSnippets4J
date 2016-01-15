package com.gpzuestc.fundamentals.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections.map.ListOrderedMap;
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
		List<Map.Entry<String, Integer>> entry = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		// 排序前
		System.out.println("befor:");
		for (int i = 0; i < entry.size(); i++) {
			String id = entry.get(i).toString();
			System.out.println(id);
		}
		// 排序
		Collections.sort(entry, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue() - o2.getValue());
			}
		});
		// 排序后
		System.out.println("after:");
		for (int i = 0; i < entry.size(); i++) {
			String id = entry.get(i).toString();
			System.out.println(id);
		}
	}
	
	@Test
	public void testLinkedHashMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		String str  = "abcdefg";
		str.indexOf("e");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testListOrderedMap(){
		Map<String, String> map = new HashMap<String, String>();
        map.put("1", "Test1");
        map.put("2", "Test2");
        map.put("3", "Test3");
        map.put("4", "Test4");

        Set<Entry<String, String>> entrySet1 = map.entrySet();
        for (Entry<String, String> entry : entrySet1) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        
        System.out.println("------------");
        
        Map<String, String> listOrderedMap = ListOrderedMap.decorate(new HashMap());
        listOrderedMap.put("1", "Test1");
        listOrderedMap.put("2", "Test2");
        listOrderedMap.put("3", "Test3");
        listOrderedMap.put("4", "Test4");

        Set<Entry<String, String>> entrySet2 = listOrderedMap.entrySet();
        for (Entry<String, String> entry : entrySet2) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
	}
	
	@Test
	public void testTreeMap(){
		TreeMap<String, Integer> map = new TreeMap<String, Integer>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
//				return o1.compareTo(o2);
				return o2.compareTo(o1); //key 倒序
			}
		});
		
		map.put("10", 2);
		map.put("08", 23);
		map.put("11", 234);
		
		for(Entry<String, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey() + ":" + entry.getValue()); 
		}
	}
	
	@Test
	public void testTreeMapRange(){
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		map.put(800, 0);
		map.put(1000, 2);
		map.put(1100, 4);
		System.out.println(map.get(map.tailMap(900).firstKey()));
		System.out.println(map.get(map.tailMap(600).firstKey()));
		System.out.println(map.get(map.tailMap(800, false).firstKey()));
		System.out.println(map.get(map.tailMap(1050).firstKey()));
		System.out.println(map.get(map.tailMap(1000).firstKey()));
		System.out.println(map.get(map.tailMap(1100).firstKey()));
//		System.out.println(map.get(map.tailMap(1105).firstKey()));
		
	}
	
	@Test
	public void testMapNull(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("000", "aaa");
		map.put("111", null);
		map.put(null, "222");
		
		for(Map.Entry<String, String> entry : map.entrySet()){
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}
}


