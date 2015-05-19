package com.gpzuestc.framework.hibernate.dao;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gpzuestc.framework.hibernate.entity.Player;
import com.gpzuestc.framework.hibernate.entity.PlayerBO;
import com.gpzuestc.framework.hibernate.entity.PlayerConcatBO;

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
	
	@Autowired
	private LocalSessionFactoryBean localSessionFactory;
	
	@Transactional  //用currentSession必须要用transaction的注解
	public Player get(Long id){
//		return localHibernateTemplate.get(Player.class, id);
		
		Session session = localSessionFactory.getObject().getCurrentSession();
		session.setFlushMode(FlushMode.AUTO);
//		session.setFlushMode(FlushMode.MANUAL); //manual 不会主动update
		Player p = (Player)session.get(Player.class, id);
		System.out.println("in db:" + p.getName());
		p.setName("Pirlo");
		return p;
//		
		
//		return (Player)localSessionFactory.getObject().getCurrentSession().get(Player.class, id);
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
	public PlayerBO getPlayerBO(final Long id) {
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
	public PlayerBO getPlayerBOBySqlQuery(final Long id) {
		PlayerBO player = (PlayerBO)localHibernateTemplate.execute(new HibernateCallback<PlayerBO>() {

			@Override
			public PlayerBO doInHibernate(Session session)
					throws HibernateException {
				String idName = "id";
				String sql = "select p.id as id, p.name as name, p.teamId as teamId, t.name as teamName from player p, team t where p.teamId=t.id and p.id=:" + idName;
				SQLQuery query = session.createSQLQuery(sql);
				//注意所有需要的属性都要添加scalar
				query.addScalar("id", LongType.INSTANCE).addScalar("name", StringType.INSTANCE).addScalar("teamId", LongType.INSTANCE).addScalar("teamName", StringType.INSTANCE);
				
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

			@SuppressWarnings("unchecked")
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

	@Override
	public List<Player> getPlayers() {
		return localHibernateTemplate.findByExample(new Player());
	}

	@Override
	public List<Player> getPlayersLefJoinFetch() {
		return localHibernateTemplate.execute(new HibernateCallback< List<Player>>() {

			@Override
			public  List<Player> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("from Player as p left join fetch p.team as team");
//				query.setResultTransformer(Transformers.aliasToBean(Player.class));
				return (List<Player>)query.list();
			}
			
		});
	}

	@Override
	public Long savePlayer(Player player) {
		return (Long)localHibernateTemplate.save(player);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object listContact() {
		return localHibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				String sql = "select t.id as teamId, t.name as teamName, GROUP_CONCAT(p.name) as players from team t left join player p on p.teamId=t.id group by t.id";
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(Transformers.aliasToBean(PlayerConcatBO.class));
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object listTimeStamp() {
		return localHibernateTemplate.execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql = "select p.timestamp, unix_timestamp() from Player p ";
				Query query = session.createQuery(hql);
//				query.setResultTransformer(Transformers.aliasToBean(PlayerConcatBO.class));
				return query.list();
			}
		});
	}
}
