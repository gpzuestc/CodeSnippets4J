package com.gpzuestc.fundamentals.object;

import java.util.ArrayList;

import org.junit.Test;

import com.gpzuestc.User;

/**
 * Description: 改变对象内对象的引用，不会影响； 如果改变内容，就会影响；
 * @author: guopengzhang@sohu-inc.com 
 * @date: Mar 23, 2012
 * 
 */

public class ObjectReference {
	private String[] states = new String[]{"a", "b", "c"};
	private Integer[] integers = new Integer[]{1, 2, 3};
	private int[] ints = new int[]{1, 2, 3};
	private Integer i = 1;
	private int ii = 1;
	private ArrayList<String> as = new ArrayList<String>();
	private User u = new User("zhang", 20);
	
	
	public ObjectReference() {
		as.add("a");
		as.add("b");
		as.add("c");
	}

	
	
	public static void main(String[] args) {
		ObjectReference sa = new ObjectReference();
		
		//修改String[]
		String[] s = sa.getStates();
		System.out.println("修改前String[]:" + s[0]);
		s[0] = "A";
		System.out.println("修改后String[]:" + sa.getStates()[0]);
		String[] s2 = new String[]{"x", "y", "z"};
		s = s2;
		System.out.println("重赋值后String[]:" + sa.getStates()[0]);
		
		//修改Integer[]
		Integer[] integers = sa.getIntegers();
		System.out.println("修改前Integer[]:" + integers[0]);
		integers[0] = 11;
		System.out.println("修改后Integer[]:" + sa.getIntegers()[0]);
		
		
		//修改int[]
		int[] ints = sa.getInts();
		System.out.println("修改前int[]:" + ints[0]);
		ints[0] = 11;
		System.out.println("修改后int[]:" + sa.getInts()[0]);
		
		//修改ArrayList<String>
		ArrayList<String> as = sa.getAs();
		System.out.println("修改前ArrayList<String>:" + as.get(0));
		as.set(0, "A");
		System.out.println("修改后ArrayList<String>:" + sa.getAs().get(0));
		
		//修改Integer
		Integer i = sa.getI();
		System.out.println("修改前Integer:" + i);
		i = 2;
		System.out.println("修改后Integer:" + sa.getI());
		
		//修改int
		int ii = sa.getIi();
		System.out.println("修改前int:" + ii);
		i = 2;
		System.out.println("修改后int:" + sa.getIi());
		
		User u = sa.getU();
		System.out.println("修改前User:" + u.getName());
		u.setName("guopeng"); //会改变sa对象中user对象的name值
		System.out.println("修改后User:" + sa.getU().getName());
		User uu = new User("guopengzhang", 20);
		u = uu;   //不会改变sa中user对象
		System.out.println("重赋值后User:" + sa.getU().getName());
		
		u=null;
		System.out.println(u.toString());
		
	}
	
	@Test
	public void testReference(){
		StringBuilder sb = new StringBuilder();
		sb.append("1");
		append(sb);
		System.out.println(sb.toString());
		appendChange(sb);
		System.out.println(sb.toString());
		
	}

	private void append(StringBuilder sb){
		sb.append("app");
	}
	
	private void appendChange(StringBuilder sb){
		sb = new StringBuilder("mmm");
	}
	

	public String[] getStates() {
		return states;
	}
	
	public Integer getI() {
		return i;
	}



	public Integer[] getIntegers() {
		return integers;
	}



	public void setIntegers(Integer[] integers) {
		this.integers = integers;
	}



	public int[] getInts() {
		return ints;
	}



	public void setInts(int[] ints) {
		this.ints = ints;
	}



	public int getIi() {
		return ii;
	}



	public void setIi(int ii) {
		this.ii = ii;
	}



	public ArrayList<String> getAs() {
		return as;
	}



	public void setAs(ArrayList<String> as) {
		this.as = as;
	}



	public User getU() {
		return u;
	}



	public void setU(User u) {
		this.u = u;
	}

	
	
	@Test
	public void test(){
		int[] arr = {1, 2};
		changeArray(arr);
		System.out.println(arr[0]);
		changeReference(arr);
		System.out.println(arr[0]);
	} //结论：能改变对象的值，但不能改变引用
	
	private void changeArray(int[] arr){
		arr[0] = 100;
	}
	
	private void changeReference(int[] arr){
		arr = getArray();
		
	}
	private int[] getArray(){
		int[] arr = {300, 200};
		return arr;
	}
}
