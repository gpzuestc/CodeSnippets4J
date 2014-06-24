package com.gpzuestc.framework.hibernate.entity;


/**
 * @author gpzuestc
 * @date: 2014-6-25
 * @description:  
 * 
 */
public class PlayerBO {
	
	private Long id;
	private String name;
	private Long teamId;
	private String teamName;
	
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
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
