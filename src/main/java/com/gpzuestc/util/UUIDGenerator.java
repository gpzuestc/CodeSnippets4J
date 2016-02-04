package com.gpzuestc.util;

import java.util.UUID;

public class UUIDGenerator {
	
	 public static final char[] charMap;  
	    static {  
	        charMap = new char[64];  
	        for (int i = 0; i < 10; i++) {  
	            charMap[i] = (char) ('0' + i);  
	        }  
	        for (int i = 10; i < 36; i++) {  
	            charMap[i] = (char) ('a' + i - 10);  
	        }  
	        for (int i = 36; i < 62; i++) {  
	            charMap[i] = (char) ('A' + i - 36);  
	        }  
	        charMap[62] = '_';  
	        charMap[63] = '-';  
	    }  
	    
	    /*
	     * 1、uuid 去掉-之后是32位16进制字符串，每个16进制字符转换为2进制是由4位组成，所以共4*32=128位。
	     * 2、64进制字符转换为2进制是由6位组成，为了保证uuid的2进制总位数能被4和6整除，所以选择最近的132，需要在生成的uuid上加一个16进制字符(33*4=132=6*22)
	     * 3、最小每3个16进制字符(3*4=12)才能转换为两个64进制字符(2*6=12),所以算法中每三个16进制字符转换为两个64进制字符
	     *    xxxx xxyy yyyy -> xxxxxx yyyyyy
	     */
	  
	    public static String generateShort() {  
	    	String hex = generate() + "0";
	        StringBuffer r = new StringBuffer();  
	        int index = 0;  
	        int[] buff = new int[3];  
	        int l = hex.length();  
	        for (int i = 0; i < l; i++) {  
	            index = i % 3;  
	            buff[index] = Integer.parseInt("" + hex.charAt(i), 16);  
	            if (index == 2) {  
	                r.append(charMap[buff[0] << 2 | buff[1] >>> 2]);  
	                r.append(charMap[(buff[1] &3) << 4 | buff[2]]);  
	            }  
	        }  
	        return r.toString();  
	    }  
	
	public static String generate(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(generate());
		String sh = generateShort();
		System.out.println(sh);
		System.out.println(sh.length());
	}
}
