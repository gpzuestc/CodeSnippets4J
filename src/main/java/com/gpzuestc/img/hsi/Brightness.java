package com.gpzuestc.img.hsi;

import java.io.Serializable;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-25
 * @todo: 
 * 
 */
public class Brightness implements Serializable, Comparable<Brightness>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 916631940155974594L;
	
	public static final Brightness LIGHT = new Brightness("light");
	public static final Brightness DIM = new Brightness("dim");
	public static final Brightness DARK = new Brightness("dark");
	
	private String name;
	
	public Brightness(){};
	
	public Brightness(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int compareTo(Brightness o) {
		return name.compareTo(o.getName());
	}
	
}
