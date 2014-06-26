package com.gpzuestc.fundamentals.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-1-23
 * @todo: 
 * 
 */
public class TimeTest {
	@Test
	public void testLong(){
		System.out.println(new Date().getTime());
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date());
		System.out.println(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()));
		
		//unix timestamp to commnon date
		System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(1403750900 * 1000L));
//		System.out.println(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(time)));
		
		//attention: it will throw NullPointerException
		Date date = null;
		System.out.println(DateFormatUtils.ISO_DATE_FORMAT.format(date));
		
	}
	
	@Test
	public void testDay(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
//		cal.add(Calendar.DAY_OF_WEEK, -1);
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		System.out.println(sdf.format(new Date()));
		
	}
}
