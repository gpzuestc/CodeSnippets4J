package com.gpzuestc.framework.hibernate;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gpzuestc.framework.hibernate.dao.PlayerDAO;
import com.gpzuestc.framework.hibernate.entity.Player;
import com.gpzuestc.framework.hibernate.entity.Team;
import com.gpzuestc.util.ClassUtils;
import com.gpzuestc.util.JsonUtil;

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
//		context = new ClassPathXmlApplicationContext(new String[] { "/com/gpzuestc/framework/hibernate/applicationContext.xml" });
		context = new ClassPathXmlApplicationContext(ClassUtils.getPackagePathOfClass(HibernateDemo.class) + "/applicationContext.xml");
		playerDAO = (PlayerDAO)context.getBean("playerDAO");
	}
	
	@Test
	public void testGetEntity(){
		Player player = playerDAO.get(1L);
		System.out.println(player.getName());
		System.out.println(player.getTeam());
		System.out.println(player.getTeamId());
		player.setName("Pirlo1");
		System.out.println(player.getName());
		
		player = playerDAO.get(1L);
		System.out.println(player.getName());
	}
	
	@Test
	public void testLoadEntityList(){
		System.out.println(playerDAO.listByTeamId(1L));
	}
	
	@Test
	public void testGetBO(){
		System.out.println(playerDAO.getPlayerBO(1L).getTeamName());
	}
	
	@Test
	public void testGetBOBySqlQuery(){
		System.out.println(playerDAO.getPlayerBOBySqlQuery(1L).getTeamName());
	}
	
	@Test
	public void testLoadBOList(){
		System.out.println(playerDAO.listPlayerBOByTeamId(1L));
	}
	
	@Test
	public void testFindAllPlayers(){
		System.out.println(playerDAO.getPlayers());
	}
	
	
	@Test
	public void testLeftJoinFetch(){
		List<Player> list = playerDAO.getPlayersLefJoinFetch();
		System.out.println(list.size());
		System.out.println(list.get(0).getTeam().getName());
		System.out.println(list.get(0).getTeamId());
	}
	
	@Test
	public void testCreatePlayer(){
//		Player player = new Player();
//		player.setName("guopeng");
//		Team team = new Team();
//		team.setId(1L);
//		player.setTeam(team);
//		System.out.println(playerDAO.savePlayer(player));
		
		Player player = new Player();
		player.setName("guopeng");
		player.setTestBoolean(true);
		Team team = new Team();
		team.setId(2L);
		team.setName("teamaa");
		player.setTeam(team);
		System.out.println(playerDAO.savePlayer(player));
	}
	
	@Test
	public void testConcat(){
		Object obj = playerDAO.listContact();
		System.out.println(JsonUtil.toJSONString(obj));
	}
	
	@Test
	public void testTime(){
		System.out.println(JsonUtil.toJSONString(playerDAO.listTimeStamp()));
	}
}
