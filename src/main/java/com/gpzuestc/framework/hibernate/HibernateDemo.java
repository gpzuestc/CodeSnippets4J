package com.gpzuestc.framework.hibernate;

import java.util.List;

import com.gpzuestc.framework.hibernate.service.PlayerService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gpzuestc.framework.hibernate.dao.PlayerDAO;
import com.gpzuestc.framework.hibernate.entity.Player;
import com.gpzuestc.framework.hibernate.entity.Team;
import com.gpzuestc.util.ClassUtils;
import com.gpzuestc.util.JsonUtil;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gpzuestc
 * @date: 2014-6-24
 * @description:  
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:**/hibernate/**/applicationContext.xml"})
public class HibernateDemo {
	public static ApplicationContext context = null;

//	private static PlayerDAO playerDAO = null;

	@Autowired
	private PlayerDAO playerDAO;

	@Autowired
	private PlayerService playerService;


	@BeforeClass
	public static void before(){
//		context = new ClassPathXmlApplicationContext(new String[] { "/com/gpzuestc/framework/hibernate/applicationContext.xml" });
		context = new ClassPathXmlApplicationContext(ClassUtils.getPackagePathOfClass(HibernateDemo.class) + "/applicationContext.xml");
//		playerDAO = (PlayerDAO)context.getBean("playerDAO");
//		playerse
	}
	
	@Test
	@Transactional
	public void testGetEntity(){
		Player player = playerDAO.get(10L);
		System.out.println(player.getName());
		System.out.println(player.getTeam());
		System.out.println(player.getTeamId());
		player.setName("Pirlo1");
		System.out.println(player.getName());
		
		player = playerDAO.get(10L);
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
	@Transactional
	public void testCreatePlayer(){
		
		Player player = new Player();
		player.setName("guopeng222");
		player.setTestBoolean(true);
		System.out.println(playerDAO.savePlayer(player));
		boolean exp = true;
		if (exp) {
			throw new RuntimeException();
		}
		System.out.println(playerDAO.savePlayer(player));

//		System.out.println(playerService.savePlayer(player));
	}

	@Test
	public void testConcat(){
		Object obj = playerDAO.listContact();
		System.out.println(JsonUtil.toJSONString(obj));
	}

	@Transactional
	@Test
	@Rollback(false)
	public void testCreate1() {
		System.out.println(playerDAO.count());

		Player player = new Player();
		player.setName("guopeng42");

//		playerDAO.savePlayer(player);

		player = new Player();
		player.setName("guopeng55");
		playerService.savePlayer(player);

		System.out.println(playerDAO.count());
	}

	@Test
	public void testTime(){
		System.out.println(JsonUtil.toJSONString(playerDAO.listTimeStamp()));
	}

	@Test
	@Transactional
	public void testGet() throws Exception{
		System.out.println(111);
		Player player = playerDAO.getPlayer(10L);

		Thread.sleep(1000L);
		System.out.println(player.getName());
		player = playerDAO.getPlayer(10L);
		System.out.println(player.getName());
	}


	@Transactional
	@Test
	@Rollback(false)
	public void testUpdate() {

		Player player = playerDAO.getPlayer(10L);
		System.out.println(player.getName());
//		player.setName("guopeng42");

//		playerDAO.savePlayer(player);

//		player = new Player();
//		player.setName("guopeng55");
		playerService.updatePlayer(player);

		player = playerDAO.getPlayer(10L);
		System.out.println(player.getName());
	}
}
