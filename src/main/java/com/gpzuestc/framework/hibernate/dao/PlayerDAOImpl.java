package com.gpzuestc.framework.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.gpzuestc.framework.hibernate.entity.Player;
import com.gpzuestc.framework.hibernate.entity.PlayerBO;

/**
 * @author gpzuestc
 * @date: 2014-6-25
 * @description:  
 * 
 */
@Component("playerDAO")
public class PlayerDAOImpl implements PlayerDAO{
	
	@Autowired
	private HibernateTemplate localHibernateTemplate;
	
	public Player get(Long id){
		return localHibernateTemplate.get(Player.class, id);
	}

	@Override
	public List<Player> listByTeamId(final Long teamId) {
		List<Player> players = (List<Player>)localHibernateTemplate.execute(new HibernateCallback<List<Player>>() {

			@SuppressWarnings("unchecked")
			@Override
			public List<Player> doInHibernate(Session session)
					throws HibernateException {
				String teamIdName = "teamId";
				String hql = "from Player where teamId=:" + teamIdName;
				Query query = session.createQuery(hql);
				query.setLong(teamIdName, teamId);
				return query.list();
			}
		});
		return players;
	}

	@Override
	public PlayerBO getPlayBO(final Long id) {
		PlayerBO player = (PlayerBO)localHibernateTemplate.execute(new HibernateCallback<PlayerBO>() {

			@Override
			public PlayerBO doInHibernate(Session session)
					throws HibernateException {
				String idName = "id";
//				String hql = "select p.id, p.name, p.teamId, t.name as teamName from Player p, Team t where p.teamId=t.id and p.id=:" + idName;
				//as语句是必须的
				String hql = "select p.id as id, p.name as name, p.teamId as teamId, t.name as teamName from Player p, Team t where p.teamId=t.id and p.id=:" + idName;
				Query query = session.createQuery(hql);
				query.setLong(idName, id);
				query.setResultTransformer(Transformers.aliasToBean(PlayerBO.class));
				return (PlayerBO)query.uniqueResult();
			}
		});
		return player;
	}

	@Override
	public List<PlayerBO> listPlayerBOByTeamId(final Long teamId) {
		List<PlayerBO> players = (List<PlayerBO>)localHibernateTemplate.execute(new HibernateCallback<List<PlayerBO>>() {

			@Override
			public List<PlayerBO> doInHibernate(Session session)
					throws HibernateException {
				String teamIdName = "teamId";
//				String hql = "select p.id, p.name, p.teamId, t.name as teamName from Player p, Team t where p.teamId=t.id and p.id=:" + idName;
				//as语句是必须的
				String hql = "select p.id as id, p.name as name, p.teamId as teamId, t.name as teamName from Player p, Team t where p.teamId=t.id and t.id=:" + teamIdName;
				Query query = session.createQuery(hql);
				query.setLong(teamIdName, teamId);
				query.setResultTransformer(Transformers.aliasToBean(PlayerBO.class));
				return (List<PlayerBO>)query.list();
			}
		});
		return players;
	}
}
