package com.gpzuestc.framework.jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.gpzuestc.User;

/**
 * Created by IntelliJ IDEA.
 * User: xuehuayang
 * Date: 12-4-12
 * To change this template use File | Settings | File Templates.
 */
public class HessianSerializeUtil {
    public static byte[] encode(Object o) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Hessian2Output out = new Hessian2Output(bos);
        try {
            out.writeObject(o);
            out.flush();

            byte[] bytes = bos.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public static Object decode(byte[] bArr) {
        try {
            ByteArrayInputStream bin = new ByteArrayInputStream(bArr);
            Hessian2Input in = new Hessian2Input(bin);
            Object o = (Object) in.readObject(Object.class);
            return o;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
    
    public static void main(String[] args) {
		User user = new User("guopeng", 10);
		byte[] b = encode(user);
		System.out.println(new String(b));
		
		User u = (User)decode(b);
		System.out.println(u.getName());
		
		String str = "1000";
		byte[] b1 = encode(str);
		System.out.println(b1.length);
		
		String s = (String)decode(b1);
		System.out.println(s);
		
		Long i = 123456L;
		byte[] b2 = encode(i);
		System.out.println(new String(b2) + ";len=" + b2.length);
		
		Long in = (Long)decode(b2);
		System.out.println(in);
		
		User user1 = new User("xxxx", 20);
		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user);
		byte[] bb = encode(list);
		System.out.println(new String(bb));
		List<User> ll = (List<User>)decode(bb);
		for(User u1 : ll){
			System.out.println(u1.getName());
		}
		
		List<Long> larr = new ArrayList<Long>();
		larr.add(100L);
		larr.add(102L);
		larr.add(2222L);
		byte[] la = encode(larr);
		System.out.println(la.length);
	}
    
    @Test
    public void testString(){
    	String str = "{\"abc\":啦啦啦}";
    	System.out.println(new String(encode(str)));
    }
}
