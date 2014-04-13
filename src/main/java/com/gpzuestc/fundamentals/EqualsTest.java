package com.gpzuestc.fundamentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Apr 1, 2012
 * 
 */
public class EqualsTest {
	public static void main(String[] args) {
		String a = new String("a");
		String aa = a;
		System.out.println(a == aa);
		
		String b = "b";
		String bb = "b";
		
		System.out.println(b == bb);
		
	}
	
	@Test
	public void testBytes(){
		String a = "123abc";
		String b = "123abc";
		System.out.println(new String(a.getBytes()));
		System.out.println(new String(b.getBytes()));
		System.out.println(Arrays.equals(a.getBytes(), b.getBytes()));
	}
	
	@Test
	public void testList(){
		List<Long> list1 = new ArrayList<Long>();
		List<Long> list2 = new ArrayList<Long>();
		for(Long i = 0L; i < 5; i++){
			list1.add(i);
			list2.add(i);
		}
		System.out.println(list1.equals(list2));
	}
	
	@Test
	public void testNull(){
		Integer a = 1;
		System.out.println(a.equals(null));
		
		int b = 1;
		Integer n = null;
		System.out.println(b == n); //error
	}
}
