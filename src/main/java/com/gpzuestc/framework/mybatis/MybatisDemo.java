package com.gpzuestc.framework.mybatis;

import com.gpzuestc.framework.mybatis.entity.User;
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
		System.out.println(ClassUtils.getPackagePathOfClass(MybatisDemo.class)); // com/gpzuestc/framework/mybatis
		context = new ClassPathXmlApplicationContext(
				new String[] {ClassUtils.getPackagePathOfClass(MybatisDemo.class) + "/spring-mybatis.xml" });
//				new String[] {"/spring-mybatis.xml"});
//				new String[] {"classpath:" + ClassUtils.getPackagePathOfClass(MybatisDemo.class) + "/spring-mybatis.xml" });

	}

	UserDAO userDAO = context.getBean(UserDAO.class);

	@Test
	public void testLoad(){
		System.out.println();
		System.out.println(userDAO.load(1L).getName());
	}
	
	@Test
	public void testFindByName(){
		System.out.println(userDAO.findByNameAndStartAndSize("sobject", 2, 3).get(1).getId());
	}

	@Test
	public void testSave(){
		User user = new User();
		user.setName("new");
		System.out.println(userDAO.save(user));
		System.out.println(user.getId());
	}

	@Test
	public void testUpdate(){
		User user = userDAO.load(3L);
		user.setName("ggg3");
		System.out.println(userDAO.update(user));

		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		user = new User();
		user.setId(4L);
		user.setName("ggg4");
		System.out.println(userDAO.update(user));
	}
}
