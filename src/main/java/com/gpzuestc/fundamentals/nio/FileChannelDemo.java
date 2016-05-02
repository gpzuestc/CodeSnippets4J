package com.gpzuestc.fundamentals.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.joda.time.DateTime;
import org.junit.Test;

public class FileChannelDemo {

	@Test
	public void testRead() throws Exception{
		File file = new File("/Users/gpzuestc/test.txt");
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		FileChannel fc = raf.getChannel();
		ByteBuffer bb = ByteBuffer.allocate(20);
		int count = -1;
		
		Charset charset = Charset.forName("UTF-8");;
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = null;
        
		while((count = fc.read(bb)) != -1){
			bb.flip();
            charBuffer = decoder.decode(bb);
			System.out.print(charBuffer.toString());
//			System.out.println(bb.position());
//			System.out.print(new String(bb.array()));
//			System.out.println(charBuffer.);
			bb.clear();
		}
		
		fc.close();
	}
	
	@Test
	public void testWrite() throws Exception{
		File file = new File("/Users/gpzuestc/test1.txt");
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		FileChannel fc = raf.getChannel();
		
		int bufferSize = 10;
		String str = "一个人把所有的青春都已丢在路上，一直走走到陌生的地方";
		ByteBuffer byteBuffer = // ByteBuffer.allocate(bufferSize);
		
		ByteBuffer.wrap(str.getBytes());
		
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.limit());
//		byteBuffer.flip();
//		System.out.println(byteBuffer.position());
//		System.out.println(byteBuffer.limit());
		
		while(byteBuffer.hasRemaining()){
			System.out.println(1);
			fc.write(byteBuffer);
		}
	}
	
	@Test
	public void test(){
		DateTime dt = new DateTime();
		DateTime b = dt.minusDays(424);
		System.out.println(b.toDate());
	}
}
