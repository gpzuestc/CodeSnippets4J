package com.gpzuestc.fundamentals.exceptions;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-9-28
 *
 */

public class ThrowException {
	public static void main(String[] args) {
		ThrowException tx = new ThrowException();
		try {
			tx.a();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		tx.a();
		System.out.println(1 + 1);
	}
	
	public void a() throws Exception{
		b();
	}
	
	public void b()throws Exception{
		try {
			throw new RuntimeException("�Ǻ�");
		} catch (Exception e) {
			//e.printStackTrace();
//			throw new Exception("����");
			throw new RuntimeException("����", e);
		}
	}
}

/**
 * 
java.lang.RuntimeException: �Ǻ�
	at com.gpzuestc.exception.ThrowException.b(ThrowException.java:27)
	at com.gpzuestc.exception.ThrowException.a(ThrowException.java:22)
	at com.gpzuestc.exception.ThrowException.main(ThrowException.java:13)
java.lang.Exception: ����
	at com.gpzuestc.exception.ThrowException.b(ThrowException.java:30)
	at com.gpzuestc.exception.ThrowException.a(ThrowException.java:22)
	at com.gpzuestc.exception.ThrowException.main(ThrowException.java:13)
2
*/
