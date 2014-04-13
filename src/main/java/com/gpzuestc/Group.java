package com.gpzuestc;

import java.io.Serializable;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-1-4
 * @todo: 
 * 
 */
public class Group implements Serializable{
	private String name;
	private Long id;

	public Group(){}
	
	public Group(String name){
		this(null, name);
	}
	public Group(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
