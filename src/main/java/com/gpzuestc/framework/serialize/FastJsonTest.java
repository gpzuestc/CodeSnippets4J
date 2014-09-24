package com.gpzuestc.framework.serialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gpzuestc.User;

public class FastJsonTest {

	public static void main(String[] args) {
		
		User user = new User("guopeng", 23);
		user.setBirthDay(new Date());
//		JSON.parseObject("").
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		String jsonStr = JSON.toJSONString(user, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
		System.out.println(jsonStr);
		
		System.out.println(JSON.parse(jsonStr));
		System.out.println(JSON.parseObject(jsonStr));
		System.out.println(JSON.parseObject(jsonStr, User.class));
		
		List<User> list = new ArrayList<User>();
		Map<String, User> map = new HashMap<String, User>();
		list.add(user);
		map.put("1", user);
		user = new User("ga", 233);
		list.add(user);
		map.put("2", user);
		
		jsonStr = JSON.toJSONString(list);
		System.out.println(jsonStr);
		
		System.out.println(JSON.parseObject(jsonStr, new TypeReference<List<User>>(){}).get(0).getBirthDay());
		
		jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		
		System.out.println(JSON.parseObject(jsonStr, new TypeReference<Map<String, User>>(){}).get("1").getBirthDay());
		
		
	}
}
