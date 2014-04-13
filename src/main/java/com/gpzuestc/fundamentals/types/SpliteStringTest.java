package com.gpzuestc.fundamentals.types;

import org.junit.Test;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-10-12
 *
 */

public class SpliteStringTest {
	@Test
	public void testshuxian(){
		String str = "1|2|3";
		String[] ss = str.split("\\|"); //很重要
		for(String s : ss){
			System.out.println(s + "    ;");
		}
	}
	
	public static void main(String[] args) {
		String guestStr = "[213, 234]";
		guestStr = guestStr.replace("[", "").replace("]", "");
		String[] guestIds = guestStr.split(",");
		for(String str : guestIds){
			System.out.println(str);
		}
		float a = 3/2;
		System.out.println(a);
	}
	@Test
	public void testLast(){
		String str = "E:\\IBM_Intern\\FileAutoGenModTool\\文档及运行结果\\abdsd.jsp";
//		String str = "E:_IBM_Intern_FileAutoGenModTool_文档及运行结果_abdsd.jsp";
		System.out.println(str);
		str = str.replace("\\", "_");
		System.out.println(str);
		String[] strs = str.split("_");
		for(String s : strs){
			System.out.println(s);
		}
	}
	
	@Test
	public void testSeparator(){
		String path = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		System.out.println(path);
		System.out.println(separator);
		String[] strs = path.split("\\\\");
		for(String s : strs){
			System.out.println(s);
		}
	}
	
	private String reverse(String str){
		if(str.length() == 0){
			return "";
		}
		return str.substring(str.length() - 1, str.length()) + reverse(str.substring(0, str.length() - 1));
	}
	
	@Test
	public void testReverse(){
		String abc = "abcde";
		System.out.println(reverse(abc));
	}
	
	@Test
	public void testSplit(){
		String str = "";
		String[] arr = str.split(",");
		System.out.println(arr.length);
		for(String a : arr){
			System.out.println("r:" + a); 
		}
	}
	
	@Test
	public void testSplitDot(){
		String str = "a.b.c";
		String[] arr = str.split("\\.");
		System.out.println(arr.length);
		for(String a : arr){
			System.out.println("r:" + a); 
		}
		
		int index = str.lastIndexOf(".");
		System.out.println(index);
		System.out.println(str.substring(index + 1));
	}
	
	@Test
	public void testSplite(){
		String str = "1,2,3,";
		String str2 = "1,2,3";
		String[] strarray = str.split(",");
		System.out.println(strarray.length);
		for(String s : strarray){
			System.out.println("str: " + s);
		}
		strarray = str2.split(",");
		System.out.println(strarray.length);
		for(String s : strarray){
			System.out.println("str2: " + s);
		}
	}
}


