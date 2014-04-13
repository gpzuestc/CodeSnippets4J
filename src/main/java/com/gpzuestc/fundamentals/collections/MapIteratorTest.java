package com.gpzuestc.fundamentals.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * @author gpzuestc
 * @date: 2014-3-3
 * @description:  
 * 
 */
public class MapIteratorTest {

	private static Map<Integer, String> map = new HashMap<Integer, String>();
//	private static Map<Integer, String> map = new ConcurrentHashMap<Integer, String>();
	
	public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
        	map.put(i, "value" + i);
        }
        
//        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
//        while(it.hasNext()){
//            Map.Entry<Integer, String> entry = it.next();
//            Integer key = entry.getKey();
//            if(key % 2 == 0){
//           	 	System.out.println("To delete key " + key);
//                map.put(key, "newValue");   
////                map.remove(key);      //ConcurrentModificationException
////           	 	it.remove();        //OK 
//           	 	System.out.println("The key " + + key + " was deleted");
//
//            }
//        }
        
//      Iterator<Integer> it = map.keySet().iterator();
//      while(it.hasNext()){
//          Integer key = it.next();
//          if(key % 2 == 1){
//              System.out.println("delete this: "+key);
////              map.put(key, "奇数");   //ConcurrentModificationException
////              map.remove(key);      //ConcurrentModificationException
//              it.remove();        //OK 
//          }
//      }
        
//        Set<Integer> keySet = map.keySet();
//        for(Integer key : keySet){
//        	 if(key % 2 == 0){
//            	 System.out.println("To delete key " + key);
////                 map.put(key, "奇数");   //ConcurrentModificationException
////                 map.remove(key);      //ConcurrentModificationException
//                 keySet.remove(key);
//               System.out.println("The key " + + key + " was deleted");
//             }
//        }
        
        /*error case*/
        
        for(Map.Entry<Integer, String> entry : map.entrySet()){
        	 Integer key=entry.getKey();
             if(key % 2 == 0){
            	 System.out.println("To delete key " + key);
//                 map.put(key, "奇数");   //ConcurrentModificationException
                 map.remove(key);      //ConcurrentModificationException
                 System.out.println("The key " + + key + " was deleted");
             }
        }
        
        
        //遍历当前的map；这种新的for循环无法修改map内容，因为不通过迭代器。
        System.out.println("map size = " + map.size());
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            System.out.println( entry.getKey() +" = " + entry.getValue());
        }
    }
	
	
	@Test
	public void testRemove(){
		Map<Integer, String> map = new HashMap<Integer, String>();
//		Map<Integer, String> map = new ConcurrentHashMap<Integer, String>(); //ok
		map.put(10, "ten");
		map.put(11, "eleven");
		map.put(12, "twelve");
//		Iterator<Entry<Integer, String>> it = map.entrySet().iterator();
//		while(it.hasNext()){
//			Entry<Integer, String> entry = it.next();
//			map.remove(11);
//			System.out.println(entry.getValue());
//		}
		
		for(Entry<Integer, String> entry : map.entrySet()){
//			map.remove(11);
			map.remove(12);
			System.out.println(entry.getValue());
		}
	}
}

/*
 * iterator的方式不论是hashMap还是concurrentHashMap都不会报异常
 * 直接for循环的方式hashMap会报异常
 * 
 */
