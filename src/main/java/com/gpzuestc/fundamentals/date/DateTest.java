package com.gpzuestc.fundamentals.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

/**
 * @author gpzuestc
 * @date: 2013-10-30
 * @description:  
 * 
 */
public class DateTest {
	
	@Test
	public void testIsoFormat(){
		System.out.println(DateFormatUtils.formatUTC(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testDate(){
		System.out.println(new Date());
	}
	
	@Test
	public void testCalendar(){
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
	}
	
}
