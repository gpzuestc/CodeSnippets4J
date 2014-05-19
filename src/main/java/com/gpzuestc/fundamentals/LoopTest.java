package com.gpzuestc.fundamentals;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-5-9
 * @todo: 
 * 
 */
public class LoopTest {
	
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
