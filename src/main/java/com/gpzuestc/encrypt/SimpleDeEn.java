package com.gpzuestc.encrypt;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;


public class SimpleDeEn {
	public static void main(String[] args) {
		
//		String str = "z125902-11whi_12";
//		String res = encode(encode(str));
//		System.out.println("en result=" + res);
//		String deRes = decode(decode(res));
//		System.out.println("de result=" + deRes);
		
		
		
		String str = "z125902-11whi_12";
		String res = encode2(str);
		System.out.println("en result=" + res);
		String deRes = decode2(res);
		System.out.println("de result=" + deRes);
		
		
	}

	public static String encode(String str) {
		String ensStr = Base64.encode(str.getBytes());
		System.out.println(ensStr);
		System.out.println(new String(Base64.decode(ensStr)));
		StringBuilder sb = new StringBuilder(ensStr);
		char[] insChars = new char[4];
		insChars[0] = 'a';
		insChars[1] = 'b';
		insChars[2] = 'c';
		insChars[3] = 'd';
		sb.insert(2, insChars);
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static String decode(String str){
		StringBuilder sb = new StringBuilder(str);
		System.out.println(new String(Base64.decode(sb.toString())));
		String remStr = sb.replace(2, 6, "").toString();
		System.out.println(remStr);
		String deStr = new String(Base64.decode(remStr));
		return deStr;
	}
	
	
	private static String encode2(String str) {
		String ensStr = Base64.encode(str.getBytes());
		System.out.println(ensStr);
		System.out.println(new String(Base64.decode(ensStr)));
		StringBuilder sb = new StringBuilder(ensStr);
		char[] insChars = new char[4];
		insChars[0] = 'a';
		insChars[1] = 'b';
		insChars[2] = 'c';
		insChars[3] = 'd';
		sb.insert(1, insChars[0]);
		sb.insert(3, insChars[1]);
		sb.insert(5, insChars[2]);
		sb.insert(7, insChars[3]);
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private static String decode2(String str){
		StringBuilder sb = new StringBuilder(str);
		System.out.println(new String(Base64.decode(sb.toString())));
		sb.replace(1, 2, "");
		sb.replace(2, 3, "");
		sb.replace(3, 4, "");
		sb.replace(4, 5, "");
		String remStr = sb.toString();
		System.out.println(remStr);
		String deStr = new String(Base64.decode(remStr));
		return deStr;
	}
	
	@Test
	public void test(){
//		System.out.println('a' - '0');
		String a = "a~@#$%^&*P0abc_1232523aadkfas;safsdfq~123xmz.nB";
		char[] cs = a.toCharArray();
		for(char c : cs){
			String i = Integer.toHexString(Integer.valueOf(c));
//			Integer i = Integer.valueOf(c - '0');
			System.out.println(StringUtils.leftPad(i, 2, 'x'));
//			System.out.println((char)('0' + i));
		}
//		System.out.println((char)(49 + '0'));
		
		
//		String xx = (encode1(a));
//		System.out.println(xx);
//		System.out.println(xx.length());
//		System.out.println((decode1(xx)));
//		System.out.println(a.length());
		
		String xx = encode1(encode1(a));
		System.out.println(xx);
		System.out.println(xx.length());
		System.out.println(decode1(decode1(xx)));
		System.out.println(a.length());
	}
	
	
	private char[] salts = new char[]{'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','|','{','}','~'};
	
	private String encode1(String str){
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		char[] cs = str.toCharArray();
		for(char c : cs){
			String hexStr = Integer.toHexString(Integer.valueOf(c - ' '));
			sb.append(StringUtils.leftPad(hexStr, 2, salts[random.nextInt(salts.length - 1)]));
		}
		return sb.toString();
	}
	
	
	private String decode1(String str){
		StringBuilder res = new StringBuilder();
		char[] chars = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length(); i++){
			if(i % 2 == 0){
				if(chars[i] - 'g' < 0){
					sb.append(chars[i]);
				}
			}else{
				sb.append(chars[i]);
//				System.out.println("        :" + sb.toString());
//				System.out.println("        ?" + Integer.parseInt(sb.toString(), 16));
//				System.out.println();
				res.append((char)(' ' + Integer.parseInt(sb.toString(), 16)));
				sb = new StringBuilder();
			}
		}
		return res.toString();
	}
}
