package com.gpzuestc.framework.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author gpzuestc
 * @date: 2014-6-25
 * @description:  
 * 
 */
@Entity
@Table(name="player")
public class Player {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
//	@Column(name = "teamId")
//	private Long teamId;
	
	@ManyToOne
	@JoinColumn(name="teamId")
	private Team team;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Long getTeamId() {
//		return teamId;
//	}
//	public void setTeamId(Long teamId) {
//		this.teamId = teamId;
//	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	
	
}
