package com.gpzuestc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil extends JSON {
	
	static{
		DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
		DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteDateUseDateFormat.getMask();
		
//		DEFAULT_GENERATE_FEATURE |= SerializerFeature.SkipTransientField.getMask();
//		DEFAULT_GENERATE_FEATURE |= Feature.IgnoreNotMatch.getMask();
//		DEFAULT_GENERATE_FEATURE |= Feature.DisableCircularReferenceDetect.getMask();
//		DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
	}
	
}
