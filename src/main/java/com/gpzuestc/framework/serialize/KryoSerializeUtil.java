package com.gpzuestc.framework.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 30, 2012
 * 
 */
public class KryoSerializeUtil {
//	private static Kryo kryo = new Kryo();
	static{
//		kryo.register(boolean.class);
//		kryo.register(int.class);
//		kryo.register(long.class);
//		kryo.register(byte.class);
//		kryo.register(short.class);
//		kryo.register(char.class);
//		kryo.register(float.class);
//		kryo.register(double.class);
//		kryo.register(String.class);
//		kryo.register(Date.class);
//		kryo.register(HashMap.class);
//		kryo.register(NodeConf.class);
//		kryo.register(Boolean.class);
//		kryo.register(Integer.class);
//		kryo.register(Long.class);
//		kryo.register(Byte.class);
//		kryo.register(Float.class);
//		kryo.register(Short.class);
//		kryo.register(Character.class);
//		kryo.register(Double.class);
//		kryo.register(HashSet.class);
//		kryo.register(int[].class);
//		kryo.register(byte[].class);
//		kryo.register(ArrayList.class);
		
	}
	
	private static Kryo kryo = new Kryo();
	
	public static byte[] encode(Object value){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Output out = new Output(bos);
    	kryo.writeObject(out, value);
    	try {
			bos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	out.close();
    	return bos.toByteArray();
    }
    
    public boolean decodeBoolean(byte[] obj){
    	return (boolean)decode(obj, boolean.class);
    }
    
    public Double decodeDouble(byte[] obj){
    	return (Double)decode(obj, Double.class);
    }
    
    public static <T> T decode(byte[]obj, Class<T> c){
    	Input in = new Input(new ByteArrayInputStream(obj));
    	return kryo.readObject(in, c);
    }
}
