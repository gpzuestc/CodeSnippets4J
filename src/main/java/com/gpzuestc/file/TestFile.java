package com.gpzuestc.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @date: 2013-12-17
 * @description:  
 * 
 */
public class TestFile {
	
	@Test
	public void stats() throws IOException{
		Map<String, Integer> map = new HashMap<String, Integer>();
//		File f = new File("/Users/gpzuestc/Downloads/yc-search.log");
		File f = new File("/Users/gpzuestc/Downloads/zc-search.log");
		List<String> list = FileUtils.readLines(f);
		for(String line : list){
			String[] cols = line.split(" ");
			String path = cols[1];
			Integer count = Integer.valueOf(cols[2]);
			if(map.get(path) == null){
				map.put(path, count);
			}else{
				map.put(path, map.get(path) + count);
			}
		}
		
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			System.out.println(entry.getKey() + "\t" + entry.getValue());
		}
	}
}
