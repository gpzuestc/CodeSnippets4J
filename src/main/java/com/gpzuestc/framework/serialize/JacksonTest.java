package com.gpzuestc.framework.serialize;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;

import com.gpzuestc.User;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-7-8
 * @todo: 
 * 
 */
public class JacksonTest {
	
	public static void main(String[] args) {
		
		User source = new User("guopeng", 20);
		
		// json测试
		ObjectMapper objectMapper = new ObjectMapper();
		
		// JSON configuration not to serialize null field
		objectMapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		
		// JSON configuration not to throw exception on empty bean class
		objectMapper.getSerializationConfig().disable(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS);
		
		// JSON configuration for compatibility
		objectMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		
		byte[] bs = null;
		 try {
		    bs  =  objectMapper.writeValueAsBytes(source);
		    System.out.println(new String(bs));
		 } catch (JsonGenerationException e) {
		     e.printStackTrace();
		 } catch (JsonMappingException e) {
		     e.printStackTrace();
		 } catch (IOException e) {
		     e.printStackTrace();
		 }
		
		 User user = null;
		 JavaType javaType = TypeFactory.type(User.class);
		 try {
//		    user = objectMapper.readValue(bs, 0, bs.length, javaType);
		    Object obj = objectMapper.readValue(bs, 0, bs.length, TypeFactory.type(new Object().getClass()));
		    user = (User)obj;
		    System.out.println(user.getName());
		 } catch (JsonParseException e) {
		     e.printStackTrace();
		 } catch (JsonMappingException e) {
		     e.printStackTrace();
		 } catch (IOException e) {
		     e.printStackTrace();
		 }
	
	}

}

