package com.gpzuestc.properties;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ʹ��java.util.Properties���load()����
 * @author asus
 *
 */
public class ReadFromProperties1 {
	private static Map<String, String> map = new HashMap<String, String>();
	public static void read(String fileName, String key){
		try {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(fileName));
			Properties properties = new Properties();
			properties.load(inputStream);
			String value = properties.get(key).toString();
			System.out.println("ʹ��java.util.Properties���load������ȡֵ��" + value + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void read2(String fileName, String key){
		try {
			InputStream s = ReadFromProperties1.class.getResourceAsStream("/" + fileName);
			InputStream d = ReadFromProperties1.class.getClassLoader().getResourceAsStream(fileName);
			InputStream inputStream = new BufferedInputStream(s);
			Properties properties = new Properties();
			properties.load(inputStream);
			String value = properties.get(key).toString();
			System.out.println("ʹ��java.util.Properties���load������ȡֵ��" + value + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readTest2(String fileName, String key){
		try {
			StringBuilder sb = new StringBuilder();
			String line;
			InputStream s = ReadFromProperties1.class.getResourceAsStream("/" + fileName);
//			InputStream d = ReadFromProperties1.class.getClassLoader().getResourceAsStream(fileName);
			InputStreamReader isr = new InputStreamReader(s);
//			BufferedReader br = new BufferedReader(isr);
//			while ((line = br.readLine()) != null) {
//				sb.append(line).append("\n");
//			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readTest3(String fileName, String key){
		try {
			if(map.size() == 0){
				map.put("text", "dddddddddd");
			}
			System.out.println(map.get("text"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
