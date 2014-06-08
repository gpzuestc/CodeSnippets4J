package com.gpzuestc.fundamentals.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.google.common.collect.Sets;
import com.gpzuestc.User;

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
	
	@Test
	public void testTreeSet(){
		TreeSet<User> treeSet = new TreeSet<User>();
		treeSet.add(new User("xyz", 1));
		treeSet.add(new User("abc", 2));
		treeSet.add(new User("abc", 3));
		
		System.out.println(treeSet.size()); // 2 !! the User class has implemented the comparable interface and 
		                                    // the value be compared is the "name" property; 
		treeSet.clear();
		User a = new User("a");
		User c = new User("c");
		User e = new User("e");
		User f = new User("f");
		
		User x = new User("x");
		User z = new User("z");
		
		User g = new User("g");
		
		
		treeSet.add(a);
		treeSet.add(f);
		treeSet.add(x);
		treeSet.add(z);
		treeSet.add(c);
		treeSet.add(e);
		
		User ceiling = treeSet.ceiling(g);
		System.out.println(ceiling.getName()); //x
		
		User floor = treeSet.floor(g);
		System.out.println(floor.getName()); //f
		
		System.out.println(treeSet.tailSet(g).size()); //2
		System.out.println(treeSet.headSet(c).size()); //1
		System.out.println(treeSet.headSet(c, true).size()); //2
	}
}
