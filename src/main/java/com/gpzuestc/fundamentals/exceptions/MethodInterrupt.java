package com.gpzuestc.fundamentals.exceptions;
/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jun 4, 2012
 * 
 */
public class MethodInterrupt {
	public static void main(String[] args) {
		int a = 1;
		try{
			a = Integer.parseInt("333s");
		}catch (Exception e) {
			System.out.println(a);
		}
		System.out.println(a);
	}
	
	/** result:
	 * 1
	 * 1
	 */
}
