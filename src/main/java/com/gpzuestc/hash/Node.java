package com.gpzuestc.hash;

import com.gpzuestc.hash.consistent.jedis.JedisShardInfo;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 26, 2012
 * 
 */
public class Node {
	private String name;
	public Node(String name) {
		this.name = name;
	}
	public Node(){
		
	}
	public Node(JedisShardInfo jedisShardInfo) {
		this.name = jedisShardInfo.getName();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
