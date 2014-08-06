package com.gpzuestc.fundamentals.reflect;

import java.lang.reflect.Field;

import org.junit.Test;

import com.gpzuestc.User;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-11-18
 * @todo: 
 * 
 */
public class ReflectionTest {
	@Test
	public void testGetName() throws Exception{
//		User user = new User("guopeng", 11);
//		Class<?> clazz = user.getClass();
//		System.out.println(clazz.getCanonicalName());
//		Field[] fields = clazz.getDeclaredFields();
//		System.out.println("fieldNmae:" + fields[0].getName());
//		System.out.println("fieldNmae:" + fields[1].getName());
//		System.out.println("fieldNmae:" + fields[2].getName());
//		Field f = user.getClass().getDeclaredField("name");
//		f.setAccessible(true);
//		System.out.println(f.get(user));
//		System.err.println("hi:" + (null instanceof User));
		
		
		Field[] fields = User.class.getDeclaredFields();
		for(Field f : fields){
			System.out.println("fieldNmae:" + f.getName());
		}
	}
	
	@Test
	public void testGetArgName(){
		String[] args = ReflectionUtils.getMethodVariableNames("com.gpzuestc.User", "testArgsName");
		System.out.println(args);
		for(String arg : args){
			System.out.println(arg);
		}
	}
}
