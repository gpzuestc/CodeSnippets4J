package com.gpzuestc.fundamentals;

import org.junit.Test;

public class SyntaxTest {

	@Test
	public void breakTest(){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 4; j++){
				if(j == 1){
					break;
				}
				System.out.println(i + ":" + j);
			}
		}
	}
	
	
	@Test
	public void continueTest(){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 4; j++){
				if(j == 1){
					continue;
				}
				System.out.println(i + ":" + j);
			}
		}
	}
	
}
