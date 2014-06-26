package com.gpzuestc.util;

/**
 * @author gpzuestc
 * @date: 2014-6-26
 * @description:  
 * 
 */
public class ClassUtils {

	public static String getPackagePathOfClass(Class<?> clazz){
		return clazz.getPackage().getName().replaceAll("\\.", "/");
	}
}
