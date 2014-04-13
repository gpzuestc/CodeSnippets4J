package com.gpzuestc.fundamentals.concurrent;

import org.apache.commons.lang.StringUtils;

import com.gpzuestc.User;
import com.thoughtworks.xstream.XStream;

/**
 * @author gpzuestc
 * @date: 2013-10-7
 * @description:  
 * 
 */
public class ThreadLocalTest {
	
	public static void main(String[] args) {
		User user = new User("guopeng", 12);
		System.out.println(Obejct2Xml.write("test", user));
		System.out.println(Obejct2Xml.write("test", user));
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				User user = new User("guopeng", 12);
				System.out.println(Obejct2Xml.write("hello", user));
				System.out.println(Obejct2Xml.write("hello1", user));
			}
		}).start();
	}
	
}

class Obejct2Xml{
	private static final ThreadLocal<XStream> xstreamLocal = new ThreadLocal<XStream>(){
		@Override
		protected XStream initialValue() {
			return new XStream();
		}
	};
	static String write(String rootName, Object object){
		if(object != null){
			XStream xStream = xstreamLocal.get();
			System.out.println(Thread.currentThread().getId() + " : " + xStream.hashCode());
			if(StringUtils.isNotBlank(rootName)){
				xStream.alias(rootName, object.getClass());
			}
			return xStream.toXML(object);
		}
		return "";
	}
}
