package com.gpzuestc.framework.hibernate.entity;


/**
 * @author gpzuestc
 * @date: 2014-6-25
 * @description:  
 * 
 */
public class PlayerBO {
	
	private String teamName;
	private Long teamId;
	private String players;
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getPlayers() {
		return players;
	}
	public void setPlayers(String players) {
		this.players = players;
	}
	
	
}
