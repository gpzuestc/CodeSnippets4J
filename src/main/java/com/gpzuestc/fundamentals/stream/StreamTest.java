package com.gpzuestc.fundamentals.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-3-26
 * @todo: 
 * 
 */
public class StreamTest {

	@Test
	public void testTrans() throws Exception{
		InputStream inputStream = StreamTest.class.getResourceAsStream("/a.txt");
		OutputStream outputStream = new ByteArrayOutputStream();
		IOUtils.copy(inputStream, outputStream);
		System.out.println(outputStream.toString());
		
//		FileInputStream fileInputStream = 
	}
	
	public static ByteArrayOutputStream parse(InputStream in) throws Exception
	{
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {   
			swapStream.write(ch);   
		}
        return swapStream;
	}
	
	
	public static ByteArrayInputStream parse(OutputStream out) throws Exception
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}
	
	public static String parse_String(InputStream in) throws Exception
	{
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {   
			swapStream.write(ch);   
		}
        return swapStream.toString();
	}
	
	public static String parse_String(OutputStream out)throws Exception
	{
		ByteArrayOutputStream   baos=new   ByteArrayOutputStream();
		baos=(ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream.toString();
	}
	
	public static ByteArrayInputStream parse_inputStream(String in)throws Exception
	{
		ByteArrayInputStream input=new ByteArrayInputStream(in.getBytes());
		return input;
	}
	
	public static ByteArrayOutputStream parse_outputStream(String in)throws Exception
	{
		return parse(parse_inputStream(in));
	}
}
