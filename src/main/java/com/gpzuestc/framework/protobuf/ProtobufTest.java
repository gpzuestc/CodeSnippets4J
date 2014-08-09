package com.gpzuestc.framework.protobuf;

import org.junit.Test;

public class ProtobufTest {
	
	@Test
	public void testSerialize(){
		
		/**
		 * 生成ProtobufferPractice类时，
		
		[java] protoc --java_out=./ com/gpzuestc/framework/protobuf/msg.proto      23:40:39  ☁  master ☂ ✭
		[java] pwd                                                                 23:41:09  ☁  master ☂ ✭
		/Users/gpzuestc/Documents/workspace/Test-Demo/src/main/java
		
		*/
		ProtoBufferPractice.msgInfo.Builder builder = ProtoBufferPractice.msgInfo.newBuilder();
	    builder.setGoodID(100);
	    builder.setGuid("11111-23222-3333-444");
	    builder.setOrder(0);
	    builder.setType("ITEM");
	    builder.setID(10);
	    builder.setUrl("http://xxx.jpg");
	    ProtoBufferPractice.msgInfo info=builder.build();

	    byte[] result=info.toByteArray() ;
	    System.out.println(result.length);
	    System.out.println(new String(result));
	    
	    try{
	        ProtoBufferPractice.msgInfo msg = ProtoBufferPractice.msgInfo.parseFrom(result);
	        System.out.println(msg);
	    }
	    catch(Exception ex){
	        System.out.println(ex.getMessage());
	    }
	}
}
