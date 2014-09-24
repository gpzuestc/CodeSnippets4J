package com.gpzuestc;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Mar 23, 2012
 * 
 */
public class User implements Cloneable, Serializable, Comparable<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7829198924575723902L;
	private String name;
	private int age;
	private Long in;
	private Group group;
	private String extend;
	private String DNA;
	private Date birthDay;
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, int age) {
		this(name, age, null);
	}
	
	public User(String name, int age, Group group) {
		this.name = name;
		this.age = age;
		this.group = group;
	}
	
	public User(){
		try {
//			Thread.sleep(1000);
//			System.out.println("sleep");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void display_sync() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread()
				+ "display user info synchroized!");

	}

	public void display() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread() + "display user info!");

	}
	

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void changeName(){
		name = "chanedName";
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Long getIn() {
		return in;
	}

	public void setIn(Long in) {
		this.in = in;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	@JsonProperty("DNA2")
	public String getDNA() {
		return DNA;
	}

	public void setDNA(String dNA) {
		DNA = dNA;
	}

	@Override
	public int compareTo(User o) {
		return this.name.compareTo(o.getName());
	}
	
	public void testArgsName(String str1){
		
	}
	
	public void testArgsName(String str1, String str2){
		
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}
