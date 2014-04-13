package com.gpzuestc.properties;

import java.util.ResourceBundle;

/**
 * ʹ��java.util.ResourceBundle���getBundle()����
 * @author asus
 *
 */
public class ReadFromProperties2 {
	public static void read(String fileName, String key){
		try {
//			Locale locale = Locale.getDefault();
			ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
			String value = resourceBundle.getString(key);
			System.out.println("ʹjava.util.ResourceBundle.getBundle()" + value + "\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
