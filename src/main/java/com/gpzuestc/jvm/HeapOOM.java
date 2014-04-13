package com.gpzuestc.jvm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-4-13
 * @todo: 
 * 
 */
public class HeapOOM {
	
	@Test
	public void testHeapOOM(){
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
}

class OOMObject{
	int i = 0;
}
