package com.gpzuestc.tools;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.gpzuestc.encrypt.Base64;

public class PasswordGenTool {
	
	private static Random random = new Random();
	
	public static void main(String[] args) throws Exception {

		List<String> lines = FileUtils.readLines(new File("/Users/gpzuestc/快盘/Work/xianlaohu/数据/brand_py.txt"));
		
		String gen = genPassword();
		System.out.println(gen);
		String en = encode(gen);
		System.out.println(en);
		System.out.println(decode(en));
	}
	
	public static String genPassword(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 10; i++){
			sb.append(getRandomChar());
		}
		return sb.toString();
	}
	
	
	public static String encode(String str) {
		String ensStr = Base64.encode(str.getBytes());
//		System.out.println(ensStr);
//		System.out.println(new String(Base64.decode(ensStr)));
		StringBuilder sb = new StringBuilder(ensStr);
		char[] insChars = new char[4];
		insChars[0] = getRandomChar();
		insChars[1] = getRandomChar();
		insChars[2] = getRandomChar();
		insChars[3] = getRandomChar();
		sb.insert(2, insChars);
		System.out.println(sb.toString());
		return "100" + sb.toString();
	}
	
	public static String decode(String str){
		StringBuilder sb = new StringBuilder(str);
//		System.out.println(new String(Base64.decode(sb.toString())));
		sb.replace(0, 3, "");
		String remStr = sb.replace(2, 6, "").toString();
//		System.out.println(remStr);
		String deStr = new String(Base64.decode(remStr));
		return deStr;
	}
	
	private static char getRandomChar(){
		Random random = new Random();
		char c = (char)(48 + random.nextInt(47)); //0-^
//		System.out.println(c);
		return c;
	}
}
