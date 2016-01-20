package com.gpzuestc.util;

import java.util.UUID;

import com.gpzuestc.encrypt.Base64;
import com.sohu.wireless.util.EncryptUtil;

public class ShortUUIDGen {

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
	  
	    public static String hexTo64(String hex) {  
	        StringBuffer r = new StringBuffer();  
	        int index = 0;  
	        int[] buff = new int[3];  
	        int l = hex.length();  
	        for (int i = 0; i < l; i++) {  
	            index = i % 3;  
	            buff[index] = Integer.parseInt(""+hex.charAt(i),16);  
	            if (index == 2) {  
	                r.append(charMap[buff[0] << 2 | buff[1] >>> 2]);  
	                r.append(charMap[(buff[1] &3) << 4 | buff[2]]);  
	            }  
	        }  
	        return r.toString();  
	    }  
	  
	    public static void process() {  
	        String uuid = "1838efee-893d-3778-b480-43d6457767ea";  
	        uuid = uuid.replaceAll("-", "").toUpperCase();  
	        uuid = "0" + uuid;  
	        uuid = hexTo64(uuid);  
	        System.out.println(uuid);
	    }  
	      
	    public static void test() {  
//	        for (int i = 0; i < 100000; i++) {  
//	            process();  
//	        }  
//	        long t1 = System.nanoTime();  
//	        for (int i = 0; i < 100000; i++) {  
//	            process();  
//	        }  
//	        long t2 = System.nanoTime();  
//	        System.out.println(t2 - t1);  
	    }  
	  
	    public static void main(String[] args) {  
//	        test(); 
	    	String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	    	System.out.println(uuid.hashCode());
	    	System.out.println(Base64.encode(uuid.getBytes()));
	        System.out.println(hexTo64(uuid));
	    } 
}
