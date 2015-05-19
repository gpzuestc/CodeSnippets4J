package com.gpzuestc.framework.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author gpzuestc
 * @date: 2014-6-25
 * @description:  
 * 
 */
@Entity
@Table(name="player")
public class Player {
	
	
	private Long id;
	
	private String name;
	
//	@Column(name = "teamId")
	private Long teamId;
	
	private Team team;
	
	private Boolean testBoolean;
	
	private String timestamp;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Transient
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	
	@ManyToOne
	@JoinColumn(name="teamId")
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
		this.teamId = team.getId();
	}
	
	@Column(name = "test_boolean")
	public Boolean getTestBoolean() {
		return testBoolean;
	}
	public void setTestBoolean(Boolean testBoolean) {
		this.testBoolean = testBoolean;
	}
	
	@Column(name = "timestamp")
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
