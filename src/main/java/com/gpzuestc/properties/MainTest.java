package com.gpzuestc.properties;

import org.junit.Test;



public class MainTest {
	
	private static final String BASE_NAME = "config";
	private static final String REL_FILENAME = "src/test/java/config/config.properties";
	private static final String ABS_FILENAME = "F:/Dev/JavaWorkspace/TestApp/src/test/java/config/config.properties";
	private static final String KEY_EN = "name";
	private static final String KEY_ZH = "sex";  //��������
	@Test
	public void testReadFromProperties1(){
		System.out.println("�û���ǰĿ¼��" + System.getProperty("user.dir"));
		ReadFromProperties1.read(REL_FILENAME, KEY_EN);
		ReadFromProperties1.read(REL_FILENAME, KEY_ZH);
		ReadFromProperties1.read(ABS_FILENAME, KEY_EN);
		ReadFromProperties1.read(ABS_FILENAME, KEY_ZH);
	}
	
	@Test
	public void testReadFromProperties1_2(){
//		System.out.println("�û���ǰĿ¼��" + System.getProperty("user.dir"));
		ReadFromProperties1.read2("config.properties", KEY_EN);
		long b = System.currentTimeMillis();
		for(int i = 0; i < 100 ; i++){
			ReadFromProperties1.read2("config.properties", KEY_EN);
		}
		long e = System.currentTimeMillis();
		System.out.println(e - b);
	}
	
	@Test
	public void testReadFromProperties1_3(){
		int TIME = 10000;
//		System.out.println("�û���ǰĿ¼��" + System.getProperty("user.dir"));
		ReadFromProperties1.read2("config.properties", KEY_EN);
		long b = System.currentTimeMillis();
		for(int i = 0; i < TIME ; i++){
			ReadFromProperties1.readTest2("config.properties", KEY_EN);
		}
		long e = System.currentTimeMillis();
		long t1 = e - b;
		
		b = System.currentTimeMillis();
		for(int i = 0; i < TIME ; i++){
			ReadFromProperties1.readTest3("config.properties", KEY_EN);
		}
		e = System.currentTimeMillis();
		long t2 = e - b;
		
		System.out.println(t1);
		System.out.println(t2);
	}
	
	@Test
	public void testReadFromProperties2(){
		System.out.println("�û���ǰĿ¼��" + System.getProperty("user.dir"));
		ReadFromProperties2 readFromProperties2 = new ReadFromProperties2();
		readFromProperties2.read(BASE_NAME, KEY_EN);
		readFromProperties2.read(BASE_NAME, KEY_ZH);
		readFromProperties2.read(ABS_FILENAME, KEY_EN);
		readFromProperties2.read(ABS_FILENAME, KEY_ZH);
	}
	
	
}
