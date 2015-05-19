package com.gpzuestc.framework.hibernate.entity;

import java.math.BigInteger;

public class PlayerConcatBO {
	private Long teamId;
	private String teamName;
	private String players;
	
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(BigInteger teamId) {
		if(teamId != null){
			this.teamId = teamId.longValue();
		}
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getPlayers() {
		return players;
	}
	public void setPlayers(String players) {
		this.players = players;
	}
	
	
}
