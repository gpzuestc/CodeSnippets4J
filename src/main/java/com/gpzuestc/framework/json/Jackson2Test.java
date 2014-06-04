package com.gpzuestc.framework.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.xml.XmlMapper;
import com.gpzuestc.Group;
import com.gpzuestc.User;
import com.gpzuestc.framework.jedis.HessianSerializeUtil;

/**
 * @author gpzuestc
 * @date: 2013-11-24
 * @description:  
 * 
 */
public class Jackson2Test {
	
	static ObjectMapper m = new ObjectMapper();
	static {
		m.setSerializationInclusion(Include.NON_EMPTY);
		m.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignore unknown property
	}

	@Test
	public void test() throws Exception{
		Group g = new Group(100L, "g1");
		User user = new User("sdfsdfsf", 24, g);
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		
		try {
			System.out.println(m.writeValueAsString(user));
			System.out.println(m.writeValueAsString(list));
			System.out.println(m.writeValueAsString(null));
			String str = "{\"name\":\"sdfsdfsf\",\"age\":24,\"group\":{\"name\":\"g1\",\"id\":100}}";

			ObjectNode node = (ObjectNode)m.readTree(str);
			System.out.println("object:" + node.isObject());
			System.out.println("arr:" + node.isArray());
			System.out.println(node.get("name"));
			System.out.println(node.get("age").asInt());
			System.out.println(node.get("group").get("name"));
			System.out.println(node.get(0));
			System.out.println(node.get(1));
			System.out.println(node.get("name").asInt());
			System.out.println(node.get(null));
			
			System.out.println(node.get("extend"));
			ObjectReader newReader = m.reader(User.class);
			User u = (User)newReader.readValue(str);
			System.out.println("new reader for deserialize:" + u.getName());
			
			
			
			
			str = "[{\"name\":\"sdaa\",\"age\":11}, {\"name\":\"bb\",\"age\":12}]";
			ArrayNode arrayNode = (ArrayNode)m.readTree(str);
			System.out.println("object:" + arrayNode.isObject());
			System.out.println("arr:" + arrayNode.isArray());
			System.out.println(arrayNode.get(0));
			System.out.println(arrayNode.get(1));
			
//			String nullStr = null;
//			System.out.println(r.readTree(nullStr)); //exception
			
			XmlMapper xmlMapper = new XmlMapper();
			System.out.println(xmlMapper.writeValueAsString(user));
			System.out.println(xmlMapper.writeValueAsString(list));
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 40; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
//					ObjectMapper m = new ObjectMapper();
					User user = new User("sdfsdfsf", 12);
					
					try {
						System.out.println(m.writeValueAsString(user));
						String str = "{\"name\":\"sdfsdfsf\",\"age\":12}";
						JsonNode node = m.readTree(str);
						System.out.println(node.get("name"));
						
						str = "[{\"name\":\"sdaa\",\"age\":11}, {\"name\":\"bb\",\"age\":12}]";
						node = m.readTree(str);
						System.out.println(node.get(1));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	@Test
	public void testCompare() throws Exception{
		Group g = new Group(100L, "g1");
		User user = new User("sdfsdfsf", 24, g);
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		
		int count = 10000 * 100;
		long start = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			m.writeValueAsString(list);
		}
		long duration = System.currentTimeMillis() - start;
		System.out.println("jackson serialize:" + duration);
		
		
		start = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			JSON.toJSONString(list);
		}
		System.out.println("fast-json serialize:" + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			JSONArray.fromObject(list).toString();
		}
		System.out.println("json-lib serialize:" + (System.currentTimeMillis() - start));

		/**********************************************/
		
		String str = "[{\"name\":\"sdfsdfsf\",\"age\":24,\"group\":{\"name\":\"g1\",\"id\":100}},{\"name\":\"sdfsdfsf\",\"age\":24,\"group\":{\"name\":\"g1\",\"id\":100}}]";
		start = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			m.readTree(str);
		}
		System.out.println("jackson deSerialize:" + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			JSON.parse(str);
		}
		System.out.println("fast-json deserialize:" + (System.currentTimeMillis() - start));
		
		
		start = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			JSONArray.fromObject(str);
		}
		System.out.println("json-lib deSerialize:" + (System.currentTimeMillis() - start));
	}
	
	
	@Test
	public void testXml() throws JsonGenerationException, JsonMappingException, IOException{
		Group g = new Group(100L, "g1");
		User user = new User("sdfsdfsf", 24, g);
		List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user);
		XmlMapper xmlMapper = new XmlMapper();
		System.out.println(xmlMapper.writeValueAsString(user));
		System.out.println(xmlMapper.writeValueAsString(list));
	}
	
	
	@Test
	public void testBase() throws Exception{
		ArrayNode array = JsonNodeFactory.instance.arrayNode();
		String str = "{\"name\":\"sdfsdfsf\",\"age\":24,\"group\":{\"name\":\"g1\",\"id\":100}}";
		array.add(str);
		System.out.println(m.writeValueAsString(array));
		
		
		
		JSONArray sfArray = new JSONArray();
		sfArray.add(str);
		System.out.println(sfArray.toString());
		
		ObjectNode obj = JsonNodeFactory.instance.objectNode();
		
	}
	
	@Test
	public void test1() throws Exception{
//		Group g = new Group(23L, "group1");
		User user = new User("guopeng", 1222333);
		byte[] abc = HessianSerializeUtil.encode(user);
		System.out.println(new String(abc));
		System.out.println(abc.length);
		
		System.out.println(m.writeValueAsString(user));
		System.out.println(m.writeValueAsString(user).length());
		System.out.println(m.writeValueAsString(user).getBytes().length);
	}
	
	@Test
	public void serialize() throws JsonProcessingException {
		
		User user = new User("guopeng", 112);
		User user2 = new User("guopeng..", 43);
		
		List<User> list = new ArrayList<User>();
		list.add(user2);
		list.add(user);
		
		System.out.println(m.writeValueAsString(list));
		
	}
	
	@Test
	public void testDeserial() throws JsonProcessingException, IOException{
		String includeStr = "{\"name\":\"sdfsdfsf\",\"age\":24,\"group\":{\"name\":\"g1\",\"id\":100}}";
		ObjectReader objectReader = m.reader(User.class);
		User user = objectReader.readValue(includeStr);
		System.out.println(user.getGroup().getName());
		
//		String listStr = "[{\"name\":\"guopeng..\",\"age\":43},{\"name\":\"guopeng\",\"age\":112}]";
		final String listStr = "[{\"name\":\"sdfsdfsf\",\"age\":24,\"group\":{\"name\":\"g1\",\"id\":100}},{\"name\":\"sdfsdfsf\",\"age\":24,\"group\":{\"name\":\"g1\",\"id\":100}}]";
		final ObjectReader objectReader1 = m.reader(new TypeReference<List<User>>() {});
		for(int i = 0; i < 50; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					List<User> list;
					try {
						list = (List<User>)objectReader1.readValue(listStr);
						System.out.println(list.size());
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
			}).start();
		}
		
	}
	
	@Test
	public void testCapital(){
		User u = new User();
		u.setDNA("aaaaa");
		try {
			System.out.println(m.writeValueAsString(u));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuote() throws JsonProcessingException, IOException{
		String str = "{id:61766058,count:8955144,refers:40}";
		JsonNode node = m.readTree(str);
		System.out.println(node.toString());
//		JSONObject obj = JSONObject.fromObject(str);
//		System.out.println(obj.toString());
	}
	
}
