package com.gpzuestc.fundamentals.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-1-6
 * @todo: 
 * 
 */
public class StringTest {
	
	@Test
	public void testStartWith(){  //不支持正则表达式
		String str = "http://r10.mp.itc.cn/a/d";
		String strd = "http://dev.r10.mp.itc.cn/a/d";
		String str2d = "http://mp.chinaren.com/a/d";
		String str2 = "http://dev.mp.chinaren.com/a/d";
		String str3 = "http://m.mp.chinaren.com/a/d";
		String str3d = "http://dev.m.mp.chinaren.com/a/d";
//		String pattern = "http://(dev\\.|)(r[\\d]{1,2}\\.|m\\.|)mp\\.chinaren\\.com.*";
		String pattern = "http://(((dev\\.|)(r[\\d]{1,2}\\.|)mp\\.itc\\.cn)|((dev\\.|)(m\\.|)mp\\.chinaren\\.com)).*";
		System.out.println(str.matches(pattern));
		System.out.println(strd.matches(pattern));
		System.out.println(str2.matches(pattern));
		System.out.println(str2d.matches(pattern));
		System.out.println(str3.matches(pattern));
		System.out.println(str3d.matches(pattern));
	}
	
	@Test
	public void testStringPara(){
		String str = "aaa";
		setValue(str);
		System.out.println(str);
		
		StringWrap.ver = "bbb";
		setSWValue();
		System.out.println(StringWrap.ver);
	}
	
	private void setValue(String str){
		str = "another value";
	}
	
	private void setSWValue(){
		StringWrap.ver = "another sw value";
	}
	
	
	private static class StringWrap {
		public static String ver;
	}
	
	@Test
	public void testNewString(){
		byte[] b = null;
		String a = new String(b);
		System.out.println(a);
	}
	
	@Test
	public void testSubString(){
		String path = "/a/bb/1/23/45";
		System.out.println(path.substring(0, path.lastIndexOf("/")));
		System.out.println(path.substring(path.lastIndexOf("/") + 1, path.length()));
		
		String a = "abcdefghijk";
		int pos = a.indexOf("b");
		System.out.println(pos);
		System.out.println(a.substring(0, pos));
		
		String str = "abcdef";
		System.out.println(str.substring(0, 10));
	}
	
	@Test
	public void testCount(){
		String str = "76b1d76c-f07b-4aa6-b910-57775a2f9b62-2.wav";
		System.out.println(str.split("-").length);
		Pattern p = Pattern.compile("([\\d]+)\\.");
		Matcher m = p.matcher(str);
		if(m.find()){
			System.out.println("a:" + m.group(1));
		}
	}
	
	@Test
	public void testArrayAppend(){
		int[] arr = null;
		int[] arr2 = {1,3,5};
		for(int i = 0; i < 2; i++){
			arr = ArrayUtils.addAll(arr, arr2);
		}
		System.out.println(arr.length);
	}
	
	@Test
	public void testReplace(){
		String str = "abc${  dgc}d";
		String result = str.replaceAll("\\$\\{\\s*dgc\\s*\\}", "123");
		System.out.println(result);
		
		str = "a.plbca.pl";
		result= str.replace(".pl", ".dd");
		System.out.println(result);
	}
	
	@Test
	public void testTrim(){
		String s = " Hello ";
		s += " World ";
		s.trim( ); //s = s.trim();
		System.out.println(s);
	}
	
	@Test
	public void testNull(){
		System.out.println(null + "123");  //null123
		System.out.println(String.valueOf(null)); //NullPointerException
	}
	
	@Test
	public void testFormat(){
		String[] list = new String[2]; //不能用List
		System.out.println(list.length);
		list[0] = "a";
		list[1] = "bbb";
		System.out.println(String.format("2%s111%s6", list));
	}
	
	
	@Test
	public void testChineseLength(){
		String str = "大尼玛and伟比";
		System.out.println(str.length()); //8
		System.out.println(str.substring(0, 4)); //大尼玛a
	}
	
	@Test
	public void testUnicodeLength(){
//		String str = "%u0073";
		String str = "searchVideoByKeyword-http://search.ns.hd.sohuno.com/jsvm?wd=&p=1&s=50&t=1&plat=6";
		System.out.println(str.length());
	}
	
	
	@Test
	public void testSubstring(){
		String str = "abcde";
		System.out.println(str.substring(5, 5).length());
		
//		String tableName = "vrs.abc";
		String tableName = "abcd";
		int dotIndex = tableName.indexOf(".");
		tableName = tableName.substring(dotIndex + 1);
		System.out.println(tableName);
	}
	
	
	public static boolean validateStrByLength(String strParameter, int limitLength) {
		if(strParameter==null || strParameter.length()==0)
			return true;
		
		int temp_int = 0;
		byte[] b = strParameter.getBytes();

		for (int i = 0; i < b.length; i++) {
			if (b[i] >= 0) {
				temp_int = temp_int + 1;
			} else {
				temp_int = temp_int + 2;
				i++;
			}
		}

		if (temp_int > limitLength) {
			return false;
		} else {
			return true;
		}
	}
	
	public static String subStringWithMore(String str,
			int subBytes) {	

		int bytes = 0; // 用来存储字符串的总字节数
		for (int i = 0; i < str.length(); i++) {
			if (bytes == subBytes) {
				if (i < str.length() - 1)
					return str.substring(0, i) + "...";
				else
					return str.substring(0, i)+ "...";
			}
			char c = str.charAt(i);
			if (c < 256) {
				bytes += 1; // 英文字符的字节数看作1
			} else {
				bytes += 2; // 中文字符的字节数看作2
				if (bytes - subBytes == 1) {
					if (i < str.length() - 1)
						return str.substring(0, i) + "...";
					else
						return str.substring(0, i)+ "...";
				}
			}
		}
		return str;
	}
	
	@Test
	public void test1(){
		final String pig = "length: 10";
        final String dog = "length: " + pig.length();

        System.out.println(pig == dog);
        System.out.println("Animals are equal: " + pig == dog + ";" + pig.equals(dog));
        System.out.println("Animals are equal: " + (pig == dog) + ";" + pig.equals(dog));
        
        System.out.println();
        String a = "ab3";
        String b = "ab" + a.length();
        int num = 3;
        String c = "ab" + num;
        String d = "ab" + 3;
        Integer num1 = 3;
        String e = "ab" + num1;
        Long num2 = 3L;
        String f = "ab" + num2;
        String g = "ab" + 3L;
        long num3 = 3;
        String h = "ab" + num3;
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d); //true
        System.out.println(a == e);
        System.out.println(a == f);
        System.out.println(a == g); //true
        System.out.println(a == h);
        
        System.out.println();
        a = "ab: 4";
        b = "ab: " + "4";
        System.out.println(a == b);
	}
	
	public static void main(String[] args) {
		String str = "搜狐微博活跃者，誓要汇集天下娱乐之精华，让你在这里流连忘返，如痴如醉。\n搜狐微博交流群:19078484,";
		byte[] bs = str.getBytes();
		System.out.println(str.length());
		System.out.println(bs.length);
		System.out.println(validateStrByLength(str, 58));
		
		System.out.println(subStringWithMore(str, 58));
		
	}
}
