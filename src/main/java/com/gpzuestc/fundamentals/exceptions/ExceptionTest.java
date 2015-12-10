package com.gpzuestc.fundamentals.exceptions;

import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.util.Assert;


/**
 * @author gpzuestc
 * @date: 2013-12-3
 * @description:  
 * 
 */
public class ExceptionTest {
	
	public static void main(String[] args) {
		try {
			exceptionMethod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void exceptionMethod() throws Exception{
		try {
			int a = Integer.valueOf("a");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw e;
		}finally{
			System.out.println("final!");
		}
	}
	
	public static void throwExceptionMethod() throws Exception{
		throw new Exception("A exception was throwed;");
	}
	
	public static void throwRuntimeExceptionMethod(){ //非常严重的错误，上层调用者可以不感知该异常，并且线程会立即停止，不会继续执行。
		throw new RuntimeException("A runtime exception was throwed;");
	}
	
	
	@Test
	public void testAssert()
	{
		Integer a = 1;
		System.out.println(1);
		assert(a>1);
		System.out.println(a>1);
	}
	
//	public static void main(String[] args){
//		try {
//			throwExceptionMethod();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		throwExceptionMethod();
//		throwRuntimeExceptionMethod();
//		System.out.println("the end");
//	}
//	
	
	
	
	
	
	public int amethod()
	 {
	        try {
	               FileInputStream dis =new FileInputStream("Hello.txt"); //1，抛出异常
	      }catch ( Exception ex) {
	                 System.out.println("No such file found");   //2.catch捕获异常，并执行
	                 return -1;                               //4,return 返回
	      }finally{
	                 System.out.println("Doing finally");       //3.finally一定会执行，在return之前。
	                 return 2;
	      }
	     // return 0; //finally 有返回,return 0是错误的
	     }
	 
	 public int tryThis()
	   {
	     try{
	       System.out.println("1-");
	       throw new Exception();
	        //return 1; 抛出异常 的后面不能有return ,编译不通过
	     }catch(Exception ex){
	       System.out.println("2-");
	       return 2; //finally 若没有return 会返回 改语句  
	     }finally{
	       System.out.println("4-");
//	       return 3;  //return  最高返回 finally 中有返回语句 finally之后的就不能有其它语句
	     }
	     //System.out.println("5"); 
	   }

//	 public static void main(String[] args){
//	  ExceptionTest t = new ExceptionTest();
//	  System.out.println ("--------测试 1 -------");
//	  System.out.println ("-------- "+t.amethod());
//	  System.out.println ("--------测试 2 -------");
//	  System.out.println("-------- "+t.tryThis());
//	 }
}
