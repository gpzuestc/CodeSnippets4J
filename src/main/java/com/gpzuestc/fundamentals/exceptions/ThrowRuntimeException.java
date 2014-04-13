package com.gpzuestc.fundamentals.exceptions;

import org.junit.Test;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-9-28
 *
 */

public class ThrowRuntimeException {
	public static void main(String[] args) {
		ThrowRuntimeException tx = new ThrowRuntimeException();
		tx.a();
		System.out.println(1 + 2);
		
		/*
		 * 
		java.lang.RuntimeException: �ǺǺ�
			at com.gpzuestc.exception.ThrowException.b(ThrowException.java:22)
			at com.gpzuestc.exception.ThrowException.a(ThrowException.java:17)
			at com.gpzuestc.exception.ThrowException.main(ThrowException.java:12)
		Exception in thread "main" java.lang.RuntimeException: ������
			at com.gpzuestc.exception.ThrowException.b(ThrowException.java:26)
			at com.gpzuestc.exception.ThrowException.a(ThrowException.java:17)
			at com.gpzuestc.exception.ThrowException.main(ThrowException.java:12)
		 * 
		 */

	}
	
	private void a(){
		b();
	}
	
	private void b(){
		try {
			throw new RuntimeException("�ǺǺ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("������");
		}
		
	}
	
	
	@Test
	public void testThrow(){
		throw new RuntimeException("eeee");
	}
	
}

