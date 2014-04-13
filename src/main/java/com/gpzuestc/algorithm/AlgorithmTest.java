package com.gpzuestc.algorithm;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-2-19
 * @todo: 
 * 
 */
public class AlgorithmTest {
	@Test
	public void testAverage(){
		int[] nums = {10, 9, 8, 3, 8, 19};
		int sum1, sum2, sum3;
		for(int i : nums){
			
		}
	}
	
	@Test
	public void test(){
		int[] nums = {19, 18, 17};
		int sum = (int) (Math.pow(nums[0] - nums[1], 2) + Math.pow(nums[0] - nums[2], 2) + Math.pow(nums[2] - nums[1], 2));
		System.out.println(sum);
	}
}
/*

10 9 8 3 8 19
19 10 9 8 8 3

19 / 10+8 / 9+8+3

19 / 10+9 / 8+8+3

*/