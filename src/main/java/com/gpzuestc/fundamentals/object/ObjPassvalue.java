package com.gpzuestc.fundamentals.object;

import org.junit.Test;

class PassObj {
	String str = "init value";
}

public class ObjPassvalue {
	public static void main(String[] args) {
		PassObj objA = new PassObj();
		PassObj objB = objA;
		objA.str = "changed in objA";
		System.out.println("Print objB.str value: " + objB.str);
		
		/*
		 * RUN RESULT
		 * Print objB.str value: changed in objA
		 */
	}
	
	@Test
	public void testPassLong(){
		Long a = 1L;
		changeLong(a);
		System.out.println(a);
	}
	
	private void changeLong(Long l){
		l = 2L;
		return;
	}
}


