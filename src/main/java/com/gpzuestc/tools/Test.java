package com.gpzuestc.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Test {
	public static void main(String[] args) throws Exception {
//		System.out.println(Integer.toHexString(10001));
		
		List<String> lines = FileUtils.readLines(new File("/Users/gpzuestc/Documents/data"));
		for(String line : lines){
			if(line == null || line.trim().isEmpty()){
				continue;
			}
			
			System.out.println("0x" + Integer.toHexString(Integer.valueOf(line)));
		}
	}
}
