package com.gpzuestc.encrypt;

import org.junit.Test;

public class EncryptTest {

	
	@Test
	public void testMd5(){
		String str = "654321";
		System.out.println(MD5.crypt(str));
	}
}
