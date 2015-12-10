package com.gpzuestc.algorithm.dynamic;

import java.util.ArrayList;
import java.util.List;

import com.gpzuestc.util.JsonUtil;

public class PackageDynamic {

	
	public static Integer TOTAL_WEIGHT = 10;
	
	
	public static List<ItemObj> itemList = new ArrayList<PackageDynamic.ItemObj>();
	
	public static Answer[][] tempData ;
	
	public static void main(String[] args) {
		
		//init
		itemList.add(new ItemObj(2, 6));
		itemList.add(new ItemObj(2, 3));
		itemList.add(new ItemObj(6, 5));
		itemList.add(new ItemObj(5, 4));
		itemList.add(new ItemObj(4, 6));
		
		tempData = new Answer[itemList.size()][TOTAL_WEIGHT + 1];
		
		compute();
		System.out.println(JsonUtil.toJSONString(tempData[itemList.size() - 1][TOTAL_WEIGHT]));
		System.out.println("end");
		System.out.println(JsonUtil.toJSON(tempData));
	}
	
	
	public static void compute(){
		for(int i = 0; i < itemList.size(); i++){
			for(int j = 1; j <= TOTAL_WEIGHT; j++){
				Answer answer = new Answer();
				if(i == 0){
					if(itemList.get(0).weight <= j){
						answer.totalValue = itemList.get(0).value;
						answer.selectedItemObjs.add(itemList.get(0));
					}else{
						answer.totalValue = 0;
					}
					tempData[0][j] = answer;
				}else{
					if(j - itemList.get(i).weight < 0){
						tempData[i][j] = tempData[i - 1][j];
					}else{
						// > or >= 
//						System.out.println(i + ":" + j);
						Integer addItemValue = 0;
						if(j - itemList.get(i).weight == 0) {
							addItemValue = itemList.get(i).value;
						}else{
							addItemValue = tempData[i - 1][j - itemList.get(i).weight].totalValue + itemList.get(i).value;
						}
						
						if(addItemValue > tempData[i - 1][j].totalValue){
							answer.totalValue = addItemValue;
//							System.out.println(JsonUtil.toJSON(itemList.get(1)));
//							System.out.println(itemList.get(1).weight);
//							System.out.println(tempData[0][6 - itemList.get(1).weight]);
							Answer originalAnswer = tempData[i - 1][j - itemList.get(i).weight];
							if(originalAnswer != null){
								answer.selectedItemObjs.addAll(originalAnswer.selectedItemObjs);
							}
							answer.selectedItemObjs.add(itemList.get(i));
							tempData[i][j] = answer;
						}else{
							tempData[i][j] = tempData[i - 1][j];
						}
					}
				}
				
				
			}
		}
	}
	
	public static class ItemObj{
		public Integer weight;
		public Integer value;
		
		public ItemObj(Integer weight, Integer value){
			this.weight = weight;
			this.value = value;
		}
	}
	
	public static class Answer{
//		public Integer totalWeight;
		public Integer totalValue;
		public List<ItemObj> selectedItemObjs = new ArrayList<PackageDynamic.ItemObj>();
	}
	
	
	//http://blog.csdn.net/mu399/article/details/7722810
}
