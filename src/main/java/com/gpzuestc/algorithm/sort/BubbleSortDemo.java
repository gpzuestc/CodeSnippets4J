package com.gpzuestc.algorithm.sort;

import java.util.Arrays;

import org.junit.Test;

public class BubbleSortDemo {
//	Integer[] arr = new Integer[]{10,3,1,6,5,8,0,43,12};
	Integer[] arr = new Integer[]{1,2,3,4};
	
	@Test
	public void commonBubble(){
		for(int i = 0; i < arr.length - 1; i++){
			for(int j = 0; j < arr.length - i - 1; j++){
				if(arr[j] > arr[j+1]){
					Integer temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			System.out.println("第" + (i + 1) + "次排序结果：" + Arrays.toString(arr));
		}
//		System.out.println(Arrays.toString(arr));
	}

	/*

			第1次排序结果：[3, 1, 6, 5, 8, 0, 10, 12, 43]
			第2次排序结果：[1, 3, 5, 6, 0, 8, 10, 12, 43]
			第3次排序结果：[1, 3, 5, 0, 6, 8, 10, 12, 43]
			第4次排序结果：[1, 3, 0, 5, 6, 8, 10, 12, 43]
			第5次排序结果：[1, 0, 3, 5, 6, 8, 10, 12, 43]
			第6次排序结果：[0, 1, 3, 5, 6, 8, 10, 12, 43]
			第7次排序结果：[0, 1, 3, 5, 6, 8, 10, 12, 43]
			第8次排序结果：[0, 1, 3, 5, 6, 8, 10, 12, 43]
			
			*/
	
	@Test
	public void optBubble(){
		int pos = arr.length - 1;
		for(int i = 0; (i < arr.length - 1) && pos > 0; i++){
			pos = 0; //每一次都先重置为0，如果当前这次遍历没有发生交换说明已经排好序了，不需要继续下一次循环
			for(int j = 0; j < arr.length - i - 1; j++){
				if(arr[j] > arr[j+1]){
					Integer temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					pos = j;
				}
			}
			System.out.println("第" + (i + 1) + "次排序结果：" + Arrays.toString(arr));
		}
	}
	
	/*
			第1次排序结果：[3, 1, 6, 5, 8, 0, 10, 12, 43]
			第2次排序结果：[1, 3, 5, 6, 0, 8, 10, 12, 43]
			第3次排序结果：[1, 3, 5, 0, 6, 8, 10, 12, 43]
			第4次排序结果：[1, 3, 0, 5, 6, 8, 10, 12, 43]
			第5次排序结果：[1, 0, 3, 5, 6, 8, 10, 12, 43]
			第6次排序结果：[0, 1, 3, 5, 6, 8, 10, 12, 43]
	*/
	
}
