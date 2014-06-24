package com.gpzuestc.framework.hibernate;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gpzuestc.framework.hibernate.dao.PlayerDAO;

/**
 * @author gpzuestc
 * @date: 2014-6-24
 * @description:  
 * 
 */
public class HibernateDemo {
	public static ApplicationContext context = null;
	private static PlayerDAO playerDAO = null;
	
	@BeforeClass
	public static void before(){
		context = new ClassPathXmlApplicationContext(new String[] { "/com/gpzuestc/framework/hibernate/applicationContext.xml" });
		playerDAO = (PlayerDAO)context.getBean("playerDAO");
	}
	
	@Test
	public void testGetEntity(){
		System.out.println(playerDAO.get(1L).getName());
	}
	
	@Test
	public void testLoadEntityList(){
		System.out.println(playerDAO.listByTeamId(1L));
	}
	
	@Test
	public void testGetBO(){
		System.out.println(playerDAO.getPlayBO(1L).getTeamName());
	}
	
	@Test
	public void testLoadBOList(){
		System.out.println(playerDAO.listPlayerBOByTeamId(1L));
	}
}
