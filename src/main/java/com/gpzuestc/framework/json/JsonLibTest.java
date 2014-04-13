package com.gpzuestc.framework.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.gpzuestc.Group;
import com.gpzuestc.User;
import com.gpzuestc.fundamentals.enums.Stats;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: May 7, 2012
 * 
 */
public class JsonLibTest {
	public static void main(String[] args) {
		User user = new User();
		user.setName("guopeng");
		user.setIn(null);
//		Scope map = new Scope();
		Map map = new HashMap();
		map.put("user", user);
		JsonConfig jc = new JsonConfig();
		jc.registerDefaultValueProcessor(Long.class, new NullValueProcessor());
		JSONObject obj = JSONObject.fromObject(map, jc);
		System.out.println(obj);
		
		obj = JSONObject.fromObject(map);
		System.out.println(obj);
		JSONObject jobj = new JSONObject();
		jobj.put("data", obj);
		System.out.println(jobj);
		///////////
		
		obj = JSONObject.fromObject("{}");
		System.out.println(obj.isNullObject());
		System.out.println(obj.size());
		
	}
	
	@Test
	public void testString(){
		String str = "123";
		JSONObject obj = new JSONObject();
		obj.put("name", "guopeng");
		obj.put("data", str);
		System.out.println(obj);
		
		Object ob = str;
		obj.put("ob", ob);
		System.out.println(obj);
		
		User user = new User("zhangguopeng", 23);
		JSONObject jobj = JSONObject.fromObject(user);
		obj.put("user", user);
		System.out.println(obj);
		
		if(ob instanceof String){
			obj.put("strObj", ob);
		}else{
			JSONObject strObj = JSONObject.fromObject(ob);
			obj.put("strObj", strObj);
		}
		System.out.println(obj);
	}
	
	@Test
	public void testNewObject(){
		String str = "{'k1':'a','k2':'b'}";
		JSONObject obj = JSONObject.fromObject(str);
		System.err.println(obj.toString());
		System.err.println(obj.get("k1"));
	}
	
	@Test
	public void testRemove(){
		int i = 411111111;
		long l = 4111111112222222222L;
		
		String str = "[1,3,6,4111111112222222222," + (Long.valueOf(Integer.MAX_VALUE) + 1) + "]";
		JsonConfig config = new JsonConfig();
		
		JSONArray array = JSONArray.fromObject(str);
		System.out.println(array.get(3));
		System.out.println(array.remove(4111111112222222222L));
		System.out.println(array.remove((Long)3L));
		System.out.println(array.remove((Integer)6));
		System.out.println(array.remove((Integer)Integer.MAX_VALUE));
//		System.out.println(array.remove((Long.valueOf((Integer.MAX_VALUE)) + 1)));
		System.out.println(array.toString());
		
		str = "[\"a\", \"b\"]";
		array = JSONArray.fromObject(str);
		array.remove("a");
		System.out.println(array.toString());
		
		str = "[1,3,6,2147483647,2147483649]";
		array = JSONArray.fromObject(str);
		removeObject(array, 6L);
		removeObject(array, 2147483647L);
		removeObject(array, 2147483649L);
		removeObject(array, 21474836L);
		System.out.println(array);
	}

	private void removeObject(JSONArray array, Long slideId) {
		Object toDelSlideId;
		if(slideId <= Integer.MAX_VALUE){
			toDelSlideId = Integer.valueOf(slideId.toString());
		}else{
			toDelSlideId = Long.valueOf(slideId);
		}
		if(array.contains(toDelSlideId)){
			System.out.println(array.remove(toDelSlideId));
		}else{
			System.out.println("not contains");
		}
	}
	
	@Test
	public void testGetOpt(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("s1", "string1");
		jsonObject.put("s2", "string2");
//		System.out.println("get:" + jsonObject.getString("s3"));
		System.out.println("opt:" + jsonObject.optString("s4"));
	}
	
	@Test
	public void testOptNull(){
		Group group = new Group("group1");
		User user = new User("guopeng", 19, group);
		JSONObject jsonObject = JSONObject.fromObject(user);
		JSONObject groupObject = jsonObject.getJSONObject("group");
		Long id = groupObject.optLong("id");
		Long foo = groupObject.optLong("foo");
		Object o = groupObject.optJSONObject("f");
		System.out.println(id);
		System.out.println(foo);
		System.out.println(o);
		
	}
	
	@Test
	public void testIgnore(){
		User user = new User("guopeng");
		user.setExtend("eeee");
		user.setIn(null);
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter( new PropertyFilter(){  
		   public boolean apply( Object source, String name, Object value ) {  
			  //如果value 为null 或者 value是数字 或者 key为 extend 则过滤掉
		      if( value == null || Number.class.isAssignableFrom( value.getClass()) ||  "extend".equals(name) ){  
		         return true;  
		      }  
		      return false;  
		   }  
		}); 
		
		JSONObject jsonObject = JSONObject.fromObject(user, config);
		System.out.println(jsonObject.toString());
		
		jsonObject = jsonObject.fromObject(user);
		System.out.println(jsonObject.toString());
	}
	
	@Test
	public void testJsonFormat(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aaa");
		map.put("b", "bbb");
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		
		JsonElement jsonElement = new JsonParser().parse(jsonObject.toString());
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(jsonElement));
	}
	
	@Test
	public void testJsonFormat1(){
		
//		String uglyJSONString = "{\"data1\":100,\"data2\":\"hello\",\"list\":[\"String 1\",\"String 2\",\"String 3\"]}";
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "aaa");
		map.put("b", "bbb");
		JSONObject jsonObject = JSONObject.fromObject(map);
		String uglyJSONString = jsonObject.toString();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJSONString);
		String prettyJsonString = gson.toJson(je);
		System.out.println(uglyJSONString);
		System.out.println(prettyJsonString);
	}
	
	@Test
	public void testSer(){
		JSONObject name = new JSONObject();
		name.put("content", "<b>春江花月夜</b>");
		
//		User user = new User("{\"content\": \"abc\"}", 12);
		User user = new User(name.toString(), 12);
		JSONObject jsonObject = JSONObject.fromObject(user);
		System.out.println(jsonObject.toString());
		
		
	}
	
	@Test
	public void testToBean(){

		String jsonString = "{\"age\":12,\"in\":0,\"name\":\"{\\\"content\\\":\\\"123\\\"}\"}";
		System.out.println(jsonString);
		
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		
		User user = (User)JSONObject.toBean(jsonObject, User.class);
		
		System.out.println(user.getName());
		System.out.println(user.getAge());
		System.out.println(user.getGroup());
		System.out.println(user.getIn());
	}
	
	
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
	
	@Test
	public void testBoolean(){
		Boolean flag = true;
		System.out.println(JSONObject.fromObject(flag)); //{}
	}
	
	@Test
	public void testEnum(){
		JSONObject obj = new JSONObject();
		obj.put("enum", Stats.SLIDE_REPLY_COUNT);
		System.out.println(obj.toString());
		JSONArray arr = JSONArray.fromObject(Stats.WORK_LIKE_COUNT);
		System.out.println(arr.toString());
	}
	
	@Test
	public void testArrayAdd(){
		User user = new User("abc", 12);
		User user1 = new User("def", 11);
		
		JSONObject obj = JSONObject.fromObject(user1);
		String objstr = obj.toString();
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(objstr);
		jsonArray.add(user);
		System.out.println(jsonArray.toString());
		
		
		JSONObject json = new JSONObject();
		json.put("user", objstr);
		json.put("data", user);
		json.put("user1", JSONObject.fromObject(objstr));
		json.put("arr", jsonArray.toArray());
		System.out.println(json.toString());
		
	}
	
}
