package com.gpzuestc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


/**
 * Description:
 * 
 * @author: guopengzhang@sohu-inc.com
 * @date: Jun 24, 2012
 * 
 */

public class Foo{
	public static final List<User> list = Arrays.asList(new User[]{new User(), new User(), new User()});

	//test

	@Test
	public void test() {
		User user1 = new User();
		user1.setAge(10);
		user1.setGroup(new Group());
		System.out.println(user1.hashCode());

		user1.setAge(111);
		user1.setGroup(null);
		System.out.println(user1.hashCode());

		Integer a = null;
		System.out.println(1 == a);

	}
}