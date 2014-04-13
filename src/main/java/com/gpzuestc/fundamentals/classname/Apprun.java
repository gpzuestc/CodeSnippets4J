package com.gpzuestc.fundamentals.classname;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-8-11
 *
 */

public class Apprun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MicBlog m = new MicBlog();
		System.out.println(MicBlog.class);
		System.out.println(((Blog)m).getClass().equals(MicBlog.class)); //true
	}

}


