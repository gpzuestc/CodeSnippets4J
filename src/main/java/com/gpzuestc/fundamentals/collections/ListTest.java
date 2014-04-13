package com.gpzuestc.fundamentals.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Test;

import com.gpzuestc.User;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-4-3
 * @todo: 
 * 
 */
public class ListTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(2);
		list.add(3);
		list.add(4);
		for(int i : list.subList(0, list.size()-2)){
			System.out.println(i);
		}
		
		list = list.subList(0, 3);
		for(int i : list){
			System.out.println("sub:" + i);
		}
		
	}
	
	@Test
	public void testAsList(){
		Long[] l = {1L};
		List<Long> list = Arrays.asList();
		
		list = Arrays.asList(null);
	}
	
	@Test
	public void testRemoveLong(){
//		Long[] ls = {100L,6L,1L};
		String[] ls = {"a", "b", "c"};
//		List list = Arrays.asList(ls);
		List list = new ArrayList();
		list.add(100L);
		list.add(6L);
		list.add(1L);
		Long a = 1L;
//		System.out.println("rem:" + list.remove(1L)); 
		/**
		 * rem:true
		 * 100
		 * 6 
		 * */
		System.out.println("rem:" + list.remove(1)); 
		/**
		 * rem:6
		 * 100
		 * 1
		 */
		
		for(Object l : list){
			System.out.println(l);
		}
	}
	
	@Test
	public void testRemoveInt(){
		List list = new ArrayList();
		list.add(100);
		list.add(6);
		list.add(1);
		
//		System.out.println("rem:" + list.remove((Object)1));
		/**
		 * rem:true
		 * 100
		 * 6
		 */
		
//		System.out.println("rem:" + list.remove((Integer)1));
		/**
		 * rem:true
		 * 100
		 * 6
		 */
		
//		System.out.println("rem:" + list.remove(1));
		/**
		 * rem:6
		 * 100
		 * 1
		 */
		for(Object l : list){
			System.out.println(l);
		}
		
	}
	
	@Test
	public void testRemoveInteger(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(100);
		list.add(6);
		list.add(1);
		
		System.out.println("rem:" + list.remove((Object)1));
		/**
		 * rem:true
		 * 100
		 * 6
		 */
		
//		System.out.println("rem:" + list.remove((Integer)1));
		/**
		 * rem:true
		 * 100
		 * 6
		 */
		for(Object l : list){
			System.out.println(l);
		}
		
	}

	@Test
	public void testAddAtIndex(){
		Long[] array = new Long[10];
		List<Long> list = Arrays.asList(array);
		System.out.println(list.size());
		list.set(1, 100L);
		list.set(2, 500L);
		for(Long l : list){
			System.out.println(l);
		}
	}
	
	@Test
	public void testLinkList(){
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0, 1);
		list.add(0,2);
		list.add(list.size(), 5);
		System.out.println(list.size());
		for(Integer i : list){
			System.out.println(i);
		}
	}
	
	@Test
	public void testListCapacity(){
		List<Integer> list = new ArrayList<Integer>(2);
		list.add(1);
		list.add(2);
		list.add(3);
		list.remove(1);
		System.out.println(list.size());
	}
	
	@Test
	public void testRef(){
		User u = new User("sss", 19);
		List<User> list = new ArrayList<User>();
		list.add(u);
		list.get(0).setAge(200);
		System.out.println(u.getAge());
		u = new User("abc", 20);
		System.out.println(u.getAge());
	}
	
	@Test
	public void testRemove(){
		List<Long> list = new ArrayList<Long>();
		list.add(123L);
		list.add(456L);
		list.add(879L);
		list.add(8888L);
		for(int i = 0 ; i < list.size(); i++){
			if(list.get(i).equals(879L)){
				list.remove(i);
				i--;
			}else{
				System.out.println(list.get(i));
			}
		}
		
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testSubList(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		List<String> subList = list.subList(0, 2);
		System.out.println(JSONArray.fromObject(subList).toString());
		
//		subList = list.subList(0, 10); //报错
//		subList = list.subList(6, 8); //报错
//		subList = list.subList(3, 1); //报错
//		subList = list.subList(0, 0);
		subList = list.subList(3, 4);
		System.out.println(JSONArray.fromObject(subList).toString());
		
	}
	
	@Test
	public void testIndexOf(){
		List<Long> list = new ArrayList<Long>();
		list.add(10L);
		list.add(3L);
		list.add(200L);
		list.add(6L);
		
		Long a = 3L;
		System.out.println(list.indexOf(a));
		
		Long b = 1L;
		System.out.println(list.indexOf(b));
		
	}
	
	@Test
	public void testIterator(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		//会跑ConcurrentModificationException异常
		for(String str : list){
			list.remove(str);
		}
		
		//正确遍历移除方式
		Iterator<String> it = list.iterator();
		while(it.hasNext()){
			it.next();
			it.remove();
		}
		
		System.out.println(list.size());
	}
}
