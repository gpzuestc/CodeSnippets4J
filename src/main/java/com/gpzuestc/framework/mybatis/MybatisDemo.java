package com.gpzuestc.framework.mybatis;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gpzuestc.framework.mybatis.dao.UserDAO;
import com.gpzuestc.util.ClassUtils;

public class MybatisDemo {
	
	protected static ApplicationContext context = null;
	protected static String SUCCESS = "SUCCESS";
	protected static long id = 1 ;
	@BeforeClass
	public static void before(){
		context = new ClassPathXmlApplicationContext(
				new String[] { ClassUtils.getPackagePathOfClass(MybatisDemo.class) + "/spring-mybatis.xml" });

	}

	UserDAO userDAO = context.getBean(UserDAO.class);

	@Test
	public void testLoad(){
		System.out.println(userDAO.load(1L).getName());
	}
	
	@Test
	public void testFindByName(){
		System.out.println(userDAO.findByNameAndStartAndSize("sobject", 2, 3).get(1).getId());
	}
}
