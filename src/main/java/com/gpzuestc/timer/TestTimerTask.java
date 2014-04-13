package com.gpzuestc.timer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author gpzuestc
 * @date: 2013-11-8
 * @description:  
 * 
 */
@Component("testTimerTask")
public class TestTimerTask {
	
	public void black(){
		System.out.println("black");
	}
	
	public void fetch(){
		System.out.println("fetch");
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("123"+ ctx.getBean("testTimerTask"));
	}
}
