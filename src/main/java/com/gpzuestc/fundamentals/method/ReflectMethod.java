package com.gpzuestc.fundamentals.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-8-7
 *
 */

public class ReflectMethod {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException { 
        Foo foo = new Foo("foo"); 
        Class clazz = foo.getClass(); 
        Method m2 = clazz.getDeclaredMethod("setMsg", String.class); 
        Method m3 = clazz.getDeclaredMethod("getMsg"); 
        Method m4 = clazz.getDeclaredMethod("setPara",String.class,String.class);
        Method m5 = clazz.getDeclaredMethod("getText");
        
        String msg = (String)m3.invoke(foo);
        System.out.println(msg);
        
        m2.invoke(foo, "msg2"); 
        msg = (String) m3.invoke(foo); 
        System.out.println(msg); 
        
        m4.invoke(foo, "msg4", "text");
        msg = (String) m3.invoke(foo);
        System.out.println(msg); 
        String text = (String) m5.invoke(foo);
        System.out.println(text); 
    } 
}


