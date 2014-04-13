package com.gpzuestc.fundamentals.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-1-10
 * @todo: 
 * 
 */
public enum Stats {
	
	//work相关
	WORK_LIKE_COUNT("tipworkcount"),
	
	//slide相关
	SLIDE_REPLY_COUNT("replyslidecount"),
	SLIDE_LIKE_COUNT("tipslidecount");
	
	public static Stats fromId(String id){
//		List<Stats> list = Arrays.asList(Stats.values());
//		for(Stats s : list){
//			if(s.toString().equals(id)){
//				return s;
//			}
//		}
		Stats[] list = Stats.values();
		for(Stats s : list){
			if(s.toString().equals(id)){
				return s;
			}
		}
		return null;
	}
	private String id;
	
	private Stats(String id){
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.id;
	}
	
	public static void main(String[] args) {
		System.out.println(WORK_LIKE_COUNT);
		System.out.println(WORK_LIKE_COUNT.name());
		System.out.println(WORK_LIKE_COUNT.ordinal());
		
		System.out.println(Stats.valueOf("WORK_LIKE_COUNT"));
		List<Stats> list = Arrays.asList(Stats.values());
		for(Stats s : list){
			System.out.println(s.name());
		}
		
		System.out.println(list.contains(WORK_LIKE_COUNT));
		System.out.println(Stats.fromId("tipslidecount").name());
	}
	/*
		tipworkcount
		WORK_LIKE_COUNT
		0
		tipworkcount
		WORK_LIKE_COUNT
		SLIDE_REPLY_COUNT
		SLIDE_LIKE_COUNT
		true
		SLIDE_LIKE_COUNT
	 */
}
