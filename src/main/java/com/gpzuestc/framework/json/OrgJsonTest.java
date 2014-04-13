package com.gpzuestc.framework.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.gpzuestc.User;

/**
 * @author gpzuestc
 * @date: 2013-8-20
 * @description:  
 * 
 */
public class OrgJsonTest {
	
	@Test
	public void testList() throws Exception{
		List<String> list = new ArrayList<String>();
		list.add("b");
		list.add("c");
		
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		System.out.println(obj.toString());
		
		List<User> objList = new ArrayList<User>();
		objList.add(new User("gpz", 20));
		objList.add(new User("zc", 20));
		
		obj = new JSONObject();
		obj.put("objList", objList);
		System.out.println(obj.toString());
	}
}
