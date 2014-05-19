package com.gpzuestc.fundamentals.collections;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

/**
 * @author gpzuestc
 * @date: 2014-4-26
 * @description:  
 * 
 */
public class SetTest {
	
	@Test
	public void testEqual(){
		Set<String> strSet = new HashSet<String>();
		strSet.add(new String("abc"));
		strSet.add(new String("abc"));
		System.out.println(strSet.size());  //1 use equals compare
		
		strSet = Sets.newIdentityHashSet();
		strSet.add(new String("abc"));
		strSet.add(new String("abc"));
		System.out.println(strSet.size()); //2 use ref compare
	}
}
