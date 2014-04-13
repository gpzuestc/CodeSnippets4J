package com.gpzuestc.fundamentals.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-2-18
 * @todo: 
 * 
 */
public enum Type {
	zk,
	local;
	
	public static Type fromOrdinal(int ordinal){
		Type type = null;
		List<Type> list = Arrays.asList(Type.values());
		for(Type t : list){
			if(t.ordinal() == ordinal){
				type = t;
				break;
			}
		}
		return type;
	}
	
	public static void main(String[] arg){
		System.out.println(Type.zk);
		System.out.println(Type.zk.name());
		System.out.println(Type.zk.ordinal());
		System.out.println(Type.valueOf("zk").toString());
		System.out.println(Type.fromOrdinal(1));
//		System.out.println(Type.valueOf("zk1")); // exception
	}
}
