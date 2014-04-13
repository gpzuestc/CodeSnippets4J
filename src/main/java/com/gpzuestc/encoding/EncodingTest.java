package com.gpzuestc.encoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.sf.json.JSONArray;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-3-2
 * @todo: 
 * 
 */
public class EncodingTest {
	
	@Test
	public void testEncode() throws Exception{
		String a = "\u6e05\u534e\u5927\u5b66";
		System.out.println(a);
		System.out.println(a.length());
		
		String b = "&#22825;&#27941;&#19977;&#20013;"; //html 
		System.out.println(HtmlDecoder.decode(b));
	}
	
	@Test
	public void translate() throws Exception{
		FileInputStream fis = new FileInputStream(new File("/Users/gpzuestc/快盘/VGoody/allunivlist.txt"));
		char[] buff = new char[fis.available()];
		
		Reader r = new InputStreamReader(fis);
		
		r.read(buff);
		
		JSONArray json = JSONArray.fromObject(new String(buff));
		System.out.println(json.size());
		System.out.println(json.getJSONObject(0).getString("name"));
		
		FileOutputStream fos = new FileOutputStream(new File("/Users/gpzuestc/快盘/VGoody/allunivlist1.txt"));
		fos.write(json.toString().getBytes());
		
	}
}
