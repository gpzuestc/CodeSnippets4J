package com.gpzuestc.framework.hibernate.dao;

import java.util.List;

import com.gpzuestc.framework.hibernate.entity.Player;
import com.gpzuestc.framework.hibernate.entity.PlayerBO;

/**
 * @author gpzuestc
 * @date: 2014-6-25
 * @description:  
 * 
 */
public interface PlayerDAO {
	Player get(Long id);
	
	PlayerBO getPlayerBO(Long id);
	
	PlayerBO getPlayerBOBySqlQuery(Long id);
	
	List<Player> listByTeamId(Long teamId);
	
	List<PlayerBO> listPlayerBOByTeamId(Long teamId);
	
	List<Player> getPlayers();
	
	List<Player> getPlayersLefJoinFetch();
}
