package com.gpzuestc.framework.serialize;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gpzuestc.User;
import com.gpzuestc.util.JsonUtil;

public class FastJsonTest {

	public static void main(String[] args) {
		
		User user = new User("guo-peng", 23);
		user.setBirthDay(new Date());
//		JSON.parseObject("").
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
//		String jsonStr = JSON.toJSONString(user, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
		String jsonStr = JSON.toJSONString(user, SerializerFeature.WriteDateUseDateFormat);
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
		
		list.add(user);
//		jsonStr = JSON.toJSONString(list);
		jsonStr = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		System.out.println(jsonStr);
		
		System.out.println(JSON.parseObject(jsonStr, new TypeReference<List<User>>(){}).get(0).getBirthDay());
		
		jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		
		System.out.println(JSON.parseObject(jsonStr, new TypeReference<Map<String, User>>(){}).get("1").getBirthDay());
		
		
	}
	
	
	@Test
	public void test() throws Exception{
		String jsonStr = FileUtils.readFileToString(new File("/Users/gpzuestc/Desktop/hi"));
		CardVO card = JSON.parseObject(jsonStr, CardVO.class);
		System.out.println(card);
	}
	
	@Test
	public void test1(){
		User user = new User("ggg", 20);
		user.setDNA("dddd");
		JSONObject jsonObjMap = (JSONObject)JsonUtil.toJSON(user);
		SortedMap<String, Object> treeMap = new TreeMap<String, Object>();
		
		for(String key : jsonObjMap.keySet()){
			treeMap.put(key, jsonObjMap.get(key));
		}
		
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Object> entry : treeMap.entrySet()){
			if(entry.getValue() != null){
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append("&");
			}
		}
		System.out.println(sb.toString());
		
		System.out.println(JsonUtil.toJSON(treeMap));
	}
}
