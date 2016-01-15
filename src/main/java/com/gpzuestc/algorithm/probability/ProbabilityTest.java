package com.gpzuestc.algorithm.probability;

import java.util.HashSet;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @date: 2014-6-8
 * @description:  
 * 
 */
public class ProbabilityTest {
	
	@Test
	public void test1(){
		int hitCount = 0;
		int eventCount = 100000;
		int MaxPeople = 50; //50个人至少两人生日一样的概率大于97%
//		int MaxPeople = 23; //23个人至少两人生日一样的概率大于50%
		for(int i = 0; i < eventCount; i++){
			HashSet<Integer> container = new HashSet<Integer>();
			for(int j = 0; j < MaxPeople; j++){
				
				Integer d = RandomUtils.nextInt(0, 365);
				if(container.contains(d)){
					hitCount++;
					break;
				}else{
					container.add(d);
				}
			}
		}
		System.out.println(hitCount);
		System.out.println(hitCount * 1.0 / eventCount);
	}
	
}
