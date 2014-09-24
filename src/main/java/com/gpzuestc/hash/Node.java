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
	private long flag;
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
	public long getFlag() {
		return flag;
	}
	public void setFlag(long flag) {
		this.flag = flag;
	}
	
	@Override
	public int hashCode() {
		return (int)(flag % Integer.MAX_VALUE);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.flag == ((Node)obj).getFlag();
	}
}
