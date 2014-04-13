package com.gpzuestc.framework.kestrel;

import java.util.List;

import net.rubyeye.xmemcached.MemcachedClient;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-11-30
 * @todo: 
 * 
 */
public class KestrelTest {
    private static final int COUNT = 30;

	private final static String QUEUE_NAME = "kq";  
	private final static String QUEUE_NAME_SINGLE = "kqs";  
  
    private ApplicationContext app;  
    private MemcachedClient memcachedClient;  
  
    /** 
     * @throws java.lang.Exception 
     */  
    @Before  
    public void before() throws Exception {  
        app = new ClassPathXmlApplicationContext("applicationContext.xml");  
        memcachedClient = (MemcachedClient) app.getBean("memcachedClient");  
    }  
  
    @Test  
    public void send() {  
    	List<String> list = memcachedClient.getServersDescription();
        for(String str : list){
        	System.out.println(str);
        }
        for (int i = 0; i < COUNT; i++) {  
            try {  
                memcachedClient.set(QUEUE_NAME + i, 0, String.valueOf(i));  
                System.out.println(i + " times");
				Thread.sleep(1000);
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    @Test  
    public void receive() {  
        String value = null;  
        List<String> list = memcachedClient.getServersDescription();
        for(String str : list){
        	System.out.println(str);
        }
        try {  
            for(int i = 0; i < COUNT; i++){  
                value = (String) memcachedClient.get(QUEUE_NAME + i); 
                System.out.println("value" + i + ": " + value);  
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    @Test
	public void sendToSingle() {

		for (int i = 0; i < 30; i++) {
			try {
				memcachedClient.set(QUEUE_NAME_SINGLE, 0, i);
				System.out.println(i + " times");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    
	@Test
	public void receiveFromSingle() {
		Integer value;
		try {
			while (true) {
				value = (Integer) memcachedClient.get(QUEUE_NAME_SINGLE);
				if (value == null) {
					break;
				}
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
