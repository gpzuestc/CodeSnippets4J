package com.gpzuestc.fundamentals.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.gpzuestc.User;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-5-24
 * @todo: 
 * 
 */
public class CollectionsTest {
	
	@Test
	public void testReadOnly(){
		  Set<String> set = new HashSet<String>( );
	        set.add("Java");
	        set.add("JEE");
	        set.add("Spring");
	        set.add("Hibernate");
	        set = Collections.unmodifiableSet(set);
	        set.add("Ajax"); //会报错
//	        SortedSet<Integer> ss = 
	        TreeSet<Integer> ts = new TreeSet<Integer>();
	}
	
	@Test
	public void testContains(){
		Set<String> set = new HashSet<String>();
		set.add("100");
		set.add("200");
		
		List<Long> list = new ArrayList<Long>();
		list.add(100L);
		list.add(300L);
		
		for(Long i : list){
			if(set.contains(String.valueOf(i))){
				System.out.println("contains " + i);
			}
		}
	}
	
	@Test
	public void testConcurrentHashMap(){
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(32, 0.7f);
	}
	
	@Test
	public void testTreeMapSort(){
		TreeMap<String, Integer> map = new TreeMap<String, Integer>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
			
		});
	}
	@Test
	public void testMapOrder(){
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
//		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("1", 1);
		map.put("23", 23);
		map.put("12", 12);
		map.put("a", 25);
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey());
		}
	}
	
	@Test
	public void testObjRef(){
		Map<Integer, User> map = new HashMap<Integer, User>();
		User user = new User("gpz", 1);
		map.put(1, user);
		user = new User("gpz", 2);
		
		System.out.println(map.get(1).getAge());
	}
	
	@Test
	public void testHashMapInConcurrent(){
		final Map<String, String> map = new HashMap<String, String>();
		for(int i = 0; i < 1000; i++){
			new Thread(new Runnable() {
				int countPerThread = 3000;
				@Override
				public void run() {
					for(int i = 0; i < countPerThread; i ++){
						map.put(Thread.currentThread().getId() + ":" + i, "a");
						map.get(Thread.currentThread().getId() + ":" + (i-2));
						map.remove(Thread.currentThread().getId() + ":" + (i-1));
					}
				}
			}).start();
		}
		System.out.println(map.size());
	}
	
	
//	@Test
//	public void testConcurrentHashMapIterator(){
	public static void main(String[] args) {
		final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		final int keyCount = 100;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < keyCount; i++){
					String key = "key" + i;
					map.put(key, "abc");
					System.out.println("add " + key);
//					try {
//						Thread.sleep(200);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		}).start();
						
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0 ; i < 3; i++){
					Set<Map.Entry<String, String>> set = map.entrySet();
					int size = set.size();
					System.out.println("map size " + size);
					for(Map.Entry<String, String> entry : set){
						System.out.println( size + " : " + entry.getKey());
					}
				}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < keyCount; i++){
					String key = "key" + new Random().nextInt(keyCount);
					map.remove(key);
					System.out.println("remove " + key);
				}
			}
		}).start();
	}
	
	
	@Test
	public void testSetAndListTrans(){
		List<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("abc");
		Set<String> set = new HashSet<String>(list);
		System.out.println(set.size());
		list = new ArrayList<String>(set);
		System.out.println(list.size());
	}
}
