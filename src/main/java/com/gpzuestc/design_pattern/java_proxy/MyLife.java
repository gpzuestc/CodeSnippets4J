package com.gpzuestc.design_pattern.java_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-4-13
 * @todo: 
 * 
 */
public class MyLife implements InvocationHandler{
	private Object target;
	 /** 
     * 绑定委托对象并返回一个代理类 
     * @param target 
     * @return 
     */  
    public Object bind(Object target) {  
        this.target = target;  
        //取得代理对象  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);   //需要绑定接口(这是一个缺陷，如果没有实现接口呢？cglib可以弥补这一缺陷)  
    }  
  
    @Override  
    /** 
     * 调用方法 
     */  
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
        Object result=null;  
        System.out.println("起床");  
        //执行方法  
        result=method.invoke(target, args);  
        System.out.println("睡觉");  
        return result;  
    }  
    
    public void invoke(DoSth doSth){
    	System.out.println("起床invoke");  
    	doSth.apply();
    	System.out.println("睡觉invoke");
    }
	
    public static void main(String[] args) {
    	MyLife myLife = new MyLife();
    	DoSth doSth = (DoSth)myLife.bind(new Work());
    	doSth.apply();	
    	doSth.apply1();
    	
    	doSth = (DoSth)myLife.bind(new Play());
    	doSth.apply();
    	doSth.apply1();
    	
    	myLife.invoke(new DoSth() {
			
			@Override
			public void apply1() {
			}
			
			@Override
			public void apply() {
				System.out.println("1234567");
			}
		});
	}
}
