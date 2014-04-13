package com.gpzuestc.design_pattern.java_proxy;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-4-13
 * @todo: 
 * 
 */
public class Play implements DoSth {

	@Override
	public void apply() {
		System.out.println("i am play game,haha");
	}

	@Override
	public void apply1() {
		System.out.println("play apply1");
	}

}
