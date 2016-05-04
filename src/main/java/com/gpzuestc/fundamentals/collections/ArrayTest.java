package com.gpzuestc.fundamentals.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gpzuestc.util.JsonUtil;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-18
 * @todo: 
 * 
 */
public class ArrayTest {
	@SuppressWarnings("unused")
	@Test
	public void joinArray(){
		String head = "head";
		String content = "content";
		byte[] headBytes = head.getBytes();
		byte[] headBytesFull = Arrays.copyOf(headBytes, 20);
		byte[] data = ArrayUtils.addAll(headBytesFull, content.getBytes());
		
		byte[] getHeadBytes = ArrayUtils.subarray(data, 0, 20);
		String a = new String(getHeadBytes);
		System.out.println(a);
		System.out.println(a.trim().length());
		byte[] getContentBytes = ArrayUtils.subarray(data, 20, data.length);
		String b = new String(new String(getContentBytes));
		System.out.println(b);
		Integer i = 1500;
		byte ib = i.byteValue();
		Integer ii = new Integer(ib);
	}
	
	@Test
	public void joinArray2(){
		String type = "";
		String content = "content";
		byte[] typeBytes = type.getBytes();
		int headLen = typeBytes.length;
		byte[] lengthBytes = Arrays.copyOf(String.valueOf(1 + 3 + 3 + headLen).getBytes(), 3);
		byte[] totalLenBytes = ArrayUtils.addAll("a".getBytes(), lengthBytes); //添加总长度
		byte[] contentTypeLenBytes = ArrayUtils.addAll(totalLenBytes, Arrays.copyOf(String.valueOf(headLen).getBytes(), 3));
		byte[] headBytes = ArrayUtils.addAll(contentTypeLenBytes, type.getBytes());
		byte[] dataBytes = ArrayUtils.addAll(headBytes, content.getBytes());
		
		byte[] getVersion = ArrayUtils.subarray(dataBytes, 0, 1);
		System.out.println(new String(getVersion));
		byte[] getTotalLen = ArrayUtils.subarray(dataBytes, 1, 4);
		int totalLen = Integer.valueOf(new String(getTotalLen).trim());
		System.out.println(totalLen);
		byte[] getTypeLen = ArrayUtils.subarray(dataBytes, 4, 7);
		int typeLen = Integer.valueOf(new String(getTypeLen).trim());
		System.out.println(typeLen);
		byte[] getType = ArrayUtils.subarray(dataBytes, 7, 7 + typeLen);
		System.out.println(new String(getType));
		
		byte[] getContent = ArrayUtils.subarray(dataBytes, totalLen, dataBytes.length);
		System.out.println(new String(getContent));
	
	}
	
	@Test
	public void testCopy(){
		int[] arr = {1, 2, 3};
		int[] copy = Arrays.copyOfRange(arr, 0, arr.length);
	}
	
	@Test
	public void testSub(){
		int[] arr = {1,2,3,4,5,6,7,8,9,10,11};
		int[] sub = ArrayUtils.subarray(arr, 5, 91);
		for(int i : sub){
			System.out.println("a:" + i);
		}
		
		int max = 50;
		int part = arr.length % max == 0 ? arr.length / max : arr.length / max + 1;
		for(int i = 0 ; i < part; i++){
			int b = i * max;
			int e = (i + 1) * max;
			if(e > arr.length){
				e = arr.length;
			}
			System.out.println(i + ":" + ArrayUtils.subarray(arr, b, e).length);
		}
	}

	@Test
	public void testToArray(){
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		Object[] objArr = list.toArray();
		System.out.println(JsonUtil.toJSONString(objArr));

		Object[] objArr2 = {"", "", "", ""};
		list.toArray(objArr2);
		System.out.println(JsonUtil.toJSONString(objArr2));

		Object[] objArr3 = {""};
		list.toArray(objArr3);
		System.out.println(JsonUtil.toJSONString(objArr3));
	}
}
