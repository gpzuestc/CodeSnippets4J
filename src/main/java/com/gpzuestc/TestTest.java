package com.gpzuestc;

import org.junit.Test;


/**
 * @author gpzuestc
 * @date: 2013-9-22
 * @description:  
 * 
 */
public class TestTest {
	
	@Test
	public void test(){
		int initialCapacity = 16;
		int concurrencyLevel = 16;
		int segmentShift = 0;
		int segmentMask = 0;
		int sshift = 0; 
        int ssize = 1; 
        while(ssize < concurrencyLevel) { 
            ++sshift; 
            ssize <<= 1; 
        } 
        segmentShift = 32 - sshift;       // 偏移量值
        segmentMask = ssize - 1;           // 掩码值 
        
        int c = initialCapacity / ssize; 
        if(c * ssize < initialCapacity) 
            ++c; 
        int cap = 1; 
        while(cap < c) 
            cap <<= 1; 
        System.out.println(ssize);
        System.out.println(cap);
	}
}
