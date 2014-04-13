package com.gpzuestc.fundamentals.object;

import org.junit.Test;

import com.gpzuestc.User;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-16
 * @todo: 
 * 
 */
public class ObjectTest {
	
	@Test
	public void testClone(){ //User类实现Clonable接口，并重写clone方法；
		User user = new User("guopengzhang", 20);
		User user2 = (User)user.clone();
		user.setName("weigo");
		System.out.println(user2.getName());
	}
	
	@Test
	public void testObject(){
		Object obj = new Object();
		System.out.println(obj.getClass());
	}
	
	@Test
	public void testSwap(){
		User user = new User("gpz", 20);
		
		for(int i = 0; i < 5; i++){
			User tmp = new User("gpz" + i, 22);
			user = tmp;
			System.out.println(user);
		}
		System.out.println();
		
	}
}
