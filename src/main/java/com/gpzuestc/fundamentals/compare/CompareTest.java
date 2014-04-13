package com.gpzuestc.fundamentals.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @Nov 24, 2011
 *
 */

public class CompareTest {
	public static void main(String[] args) {
		User u1 = new User(2,"u1");
		User u2 = new User(1,"u2");
		User u3 = new User(6,"u3");
		List<User> list = new ArrayList<User>();
		list.add(u1);
		list.add(u2);
		list.add(u3);
//		list.add(null);
		Collections.sort(list);
		for(User user : list){
			System.out.println(user.getName());
		}
	}
}


 class User implements Comparable<User>{
	private Integer age;
	private String sAge;
	private String name;
	User(Integer age, String name){
		this.age = age;
		this.sAge = age.toString();
		this.name = name;
	}
	public int compareTo(User otherUser) {
		return age.compareTo(otherUser.getAge());
//		return sAge.compareTo(otherUser.getsAge());
//		return otherUser.getAge().compareTo(age);
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getsAge() {
		return sAge;
	}
	public void setsAge(String sAge) {
		this.sAge = sAge;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}


