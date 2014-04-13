package com.gpzuestc.framework.json;

import net.sf.json.processors.DefaultValueProcessor;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: May 8, 2012
 * 
 */
public class NullValueProcessor implements DefaultValueProcessor {

	public Object getDefaultValue(Class type) {
		return "";
	}
}
