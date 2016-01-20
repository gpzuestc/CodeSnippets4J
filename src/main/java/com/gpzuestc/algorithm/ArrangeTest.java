package com.gpzuestc.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import com.gpzuestc.util.JsonUtil;

public class ArrangeTest {

	@Test
	public void test1(){
		List<String> wordList = new ArrayList<String>();
		wordList.add("a");
		wordList.add("b");
		wordList.add("c");
		wordList.add("d");
		wordList.add("e");
		
		int questionCount = 4;
//		List<String> arrangeList = new LinkedList<String>();
//		arrangeList.add(10, "mmm");
//		System.out.println(JsonUtil.toJSON(arrangeList));
		
		
		List<Long> randList = new LinkedList<Long>();
		for(int i = 0; i < wordList.size() * questionCount; i++){
			randList.add(Long.valueOf(i));
		}
		
		TreeMap<Long, String> resultMap = new TreeMap<Long, String>();
		
		for(int i = 0; i < wordList.size(); i++){
			List<Long> seqList = new LinkedList<Long>();
			while(seqList.size() < questionCount){
				int index = RandomUtils.nextInt(0, randList.size());
				seqList.add(randList.get(index));
				randList.remove(index);
			}
			Collections.sort(seqList);
			
			for(int k = 0; k < seqList.size(); k++){
				resultMap.put(seqList.get(k), wordList.get(i) + k);
			}
		}
		
		System.out.println(JsonUtil.toJSON(resultMap));
		System.out.println(JsonUtil.toJSON(resultMap.keySet()));
		System.out.println(JsonUtil.toJSON(resultMap.values()));
	}
	
	
	
	
	@Test
	public void test2(){
		List<String> wordList = new ArrayList<String>();
		wordList.add("a");
		wordList.add("b");
		wordList.add("c");
		wordList.add("d");
		wordList.add("e");
		
		int questionCount = 4;
		
		
		List<Long> randList = new LinkedList<Long>();
		for(int i = 4; i < wordList.size() * questionCount; i++){
			randList.add(Long.valueOf(i));
		}
		
		TreeMap<Long, String> resultMap = new TreeMap<Long, String>();
		
		for(int i = 0; i < wordList.size(); i++){
			List<Long> seqList = new LinkedList<Long>();
			
			if(i == 0){
				seqList.add(0L);
				seqList.add(2L);
			}else if(i == 1){
				seqList.add(1L);
				seqList.add(3L);
			}
			
			while(seqList.size() < questionCount){
				int index = RandomUtils.nextInt(0, randList.size());
				seqList.add(randList.get(index));
				randList.remove(index);
			}
			Collections.sort(seqList);
			
			for(int k = 0; k < seqList.size(); k++){
				resultMap.put(seqList.get(k), wordList.get(i) + k);
			}
		}
		
		System.out.println(JsonUtil.toJSON(resultMap));
		System.out.println(JsonUtil.toJSON(resultMap.keySet()));
		System.out.println(JsonUtil.toJSON(resultMap.values()));
	}
	
	
	@Test
	public void test3(){
		List<String> wordList = new ArrayList<String>();
		wordList.add("a");
		wordList.add("b");
		wordList.add("c");
		wordList.add("d");
		wordList.add("e");
//		wordList.add("f");
		
		int questionCount = 4;
		
		
		List<Long> randList = new LinkedList<Long>();
		for(int i = 4; i < wordList.size() * questionCount; i++){
			randList.add(Long.valueOf(i));
		}
		
		TreeMap<Long, List<Long>> seqMap = new TreeMap<Long, List<Long>>();
		
		for(int i = 0; i < wordList.size(); i++){
			List<Long> seqList = new LinkedList<Long>();
			
			if(i == 0){
				seqList.add(0L);
				seqList.add(2L);
			}else if(i == 1){
				seqList.add(1L);
				seqList.add(3L);
			}
			
//			Long seed = 12345789865432l;
			Long seed = System.currentTimeMillis();
			Random random = new Random(seed);
			
			while(seqList.size() < questionCount){
				int index = random.nextInt(randList.size());
				
//				int index = RandomUtils.nextInt(0, randList.size());
				System.out.println(index);
				seqList.add(randList.get(index));
				randList.remove(index);
			}
			Collections.sort(seqList);
			
			if(seqList != null && !seqList.isEmpty()){
				seqMap.put(seqList.get(0), seqList);
			}
			
		}
		
		TreeMap<Long, String> resultMap = new TreeMap<Long, String>();
		int i = 0;
		for(Map.Entry entry : seqMap.entrySet()){
			List<Long> list = (List<Long>)entry.getValue();
			for(int m = 0; m < list.size(); m++){
				resultMap.put(list.get(m), wordList.get(i) + m);
			}
			i++;
		}
		
//		for(int k = 0; k < seqList.size(); k++){
//			resultMap.put(seqList.get(k), wordList.get(i) + k);
//		}
		
		System.out.println(JsonUtil.toJSON(resultMap));
		System.out.println(JsonUtil.toJSON(resultMap.keySet()));
		System.out.println(JsonUtil.toJSON(resultMap.values()));
	}
}
