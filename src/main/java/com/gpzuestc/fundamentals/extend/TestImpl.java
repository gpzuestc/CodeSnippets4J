package com.gpzuestc.fundamentals.extend;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gpzuestc.util.JsonUtil;

public class TestImpl implements ITestInterface{

	@Override
	public void test() {
		System.out.println(1);
	}
	
	
	public static void main(String[] args) {
//		System.out.println(TestImpl.class.asSubclass(ITestInterface.class));
//		System.out.println(JsonUtil.toJSONString(TestImpl.class.getInterfaces()));
//		System.out.println(TestImpl.class.isAssignableFrom(ITestInterface.class));
		System.out.println(ITestInterface.class.isAssignableFrom(TestImpl.class));
		System.out.println(ITestInterface.class);
		System.out.println(Arrays.asList(TestImpl.class.getInterfaces()));
		System.out.println(Arrays.asList(TestImpl.class.getInterfaces()).contains(ITestInterface.class));
	}
}
