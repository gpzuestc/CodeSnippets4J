package com.gpzuestc.fundamentals.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Test;

import com.gpzuestc.User;
import com.gpzuestc.util.JsonUtil;

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
		
		System.out.println("rem:" + list.remove(1));
		/**
		 * rem:6
		 * 100
		 * 1
		 */
		
//		System.out.println("rem:" + list.remove(100)); //exception
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

		//sublist添加元素也会影响原来的list
		subList.add("e");
//		list.add("f");  //list不能再添加元素？？？？？
		System.out.println(JSONArray.fromObject(subList).toString());
		System.out.println(JSONArray.fromObject(list).toString());
		
		list = list.subList(0, 3);
		System.out.println(JSONArray.fromObject(list).toString());
		
//		list.add("f");
//		System.out.println(JSONArray.fromObject(subList).toString());
//		System.out.println(JSONArray.fromObject(list).toString());
		
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
	
	@Test
	public void testArray2List(){
		Integer[] cArr = new Integer[]{2,3,4};
		List<Integer> cList = Arrays.asList(cArr);
		
		List<Integer> dList = new ArrayList<Integer>();
		dList.add(4);
		dList.add(5);
		System.out.println(dList.addAll(cList));
		System.out.println(dList);
		
		Integer[] aArr = new Integer[]{1,2,3,4,5,6,7};
		List<Integer> aList = Arrays.asList(aArr);
		System.out.println(aList.addAll(cList));   //报错：java.lang.UnsupportedOperationException
		
	}
	
	@Test
	public void testOperation(){
		Integer[] bArr = new Integer[]{1,2};
		List<Integer> bList = Arrays.asList(bArr);
		Integer[] cArr = new Integer[]{2,3,4};
		List<Integer> cList = Arrays.asList(cArr);
		
		System.out.println(union(bList, cList));
		System.out.println(intersection(bList, cList));
		System.out.println(diff(bList, cList));
		
	}
	
	// 并集
	public <T> List<T> union(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();
		list.addAll(list1);
		list.addAll(list2);
		HashSet<T> hs = new HashSet<T>(list);
		list.clear();
		list.addAll(hs);
		return list;
	}

	// 交集
	public <T> List<T> intersection(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();
		list.addAll(list1);
		list.retainAll(list2);
		return list;
	}

	// 差集
	public <T> List<T> diff(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();
		list.addAll(list1);
		list.removeAll(intersection(list1, list2));
		return list;
	}
	
	@Test
	public void testRemoveAll(){
		List<Integer> aList = new ArrayList<Integer>();
		aList.add(1);
		aList.add(2);
		aList.add(3);
		aList.add(4);
		aList.add(5);
		List<Integer> bList = new ArrayList<Integer>();
		bList.add(2);
		List<Integer> cList = new ArrayList<Integer>();
		cList.add(3);
		
		System.out.println(aList.removeAll(bList));
		System.out.println(aList.removeAll(cList));
		System.out.println(aList);
		System.out.println("hehe:" + String.valueOf(aList));
	}
	
	@Test
	public void testChangeAfterAdd(){
		List<User> list = new ArrayList<User>();
		User u = new User();
		u.setName("guopeng");
		list.add(u);
		u.setName("zhang");;
		System.out.println(list.get(0).getName());
		u = null;
		System.out.println(list.get(0));
	}
	
	@Test
	public void testInsert(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add(0, "bb");
		System.out.println(JsonUtil.toJSONString(list));
	}

	@Test
	public void testAddAllNull() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.addAll(null);  //报null异常
		System.out.println(Arrays.toString(list.toArray()));
	}

	@Test
	public void testAddAll() {
		List<String> list = new ArrayList<>();
		list.add("1");

		List<String> appList = new ArrayList<>();
		appList.add("2");
		list.addAll(appList);

		appList = new ArrayList<>();
		appList.add("3");
		list.addAll(appList);

		System.out.println(JsonUtil.toJSONString(list));
	}

	@Test
	public void testForNull() {
		List<String> list = null;
		for (String i : list) { // null exception
			System.out.println("hi");
		}
		System.out.println("end");
	}
}
