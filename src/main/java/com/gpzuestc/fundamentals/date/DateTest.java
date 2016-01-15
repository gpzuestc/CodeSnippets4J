package com.gpzuestc.fundamentals.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.gpzuestc.util.JsonUtil;

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
	
	
	@Test
	public void testDateFormatOfConcurrent(){ //线程不安全
		final DateFormat yearMonthFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i = 0; i < 20; i++){
			new Thread(new RunDate(yearMonthFormat, i)
			).start();
		}
	}
	class RunDate implements Runnable{
		private DateFormat df;
		private int i;
		
		RunDate(DateFormat df, int i){
			this.df = df;
			this.i = i;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(df.format(DateUtils.addDays(new Date(), i)));
		}
		
	}
	
	/*
	2014-12-04 15:26:21
	2014-11-21 15:26:21
	2014-11-23 15:26:21
	2014-11-21 15:26:21
	2014-11-22 15:26:21
	2014-12-04 15:26:21
	2014-12-04 15:26:21
	2014-11-26 15:26:21
	2014-12-04 15:26:21
	2014-12-04 15:26:21
	2014-12-04 15:26:21
	2014-12-26 15:26:21
	2014-12-04 15:26:21
	2014-12-04 15:26:21
	2014-12-02 15:26:21
	2014-11-18 15:26:21
	2014-11-23 15:26:21
	2014-11-23 15:26:21
	2014-11-21 15:26:21
	2014-12-01 15:26:21
	*/
	
	@Test
	public void testJodaDateFormatOfConcurrent(){ //线程安全
//	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime s = new DateTime();
		System.out.println(s.toString());
		for(int i = 0; i < 20; i++){
			new Thread(new RunDateOfJoda(dtf, i)).start();
		}
	}
	static class RunDateOfJoda implements Runnable{
		private DateTimeFormatter df;
		private int i;
		
		RunDateOfJoda(DateTimeFormatter df, int i){
			this.df = df;
			this.i = i;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			DateTime todayDt = new DateTime();
			System.out.println(todayDt.plusDays(i).toString(df));
		}
		
	}
	/*
	2014-11-18T15:31:34.271+08:00
	2014-11-18 15:31:34
	2014-11-29 15:31:34
	2014-11-28 15:31:34
	2014-11-23 15:31:34
	2014-11-25 15:31:34
	2014-11-20 15:31:34
	2014-11-19 15:31:34
	2014-11-30 15:31:34
	2014-11-21 15:31:34
	2014-12-02 15:31:34
	2014-12-01 15:31:34
	2014-11-26 15:31:34
	2014-12-03 15:31:34
	2014-11-24 15:31:34
	2014-12-04 15:31:34
	2014-11-22 15:31:34
	2014-11-27 15:31:34
	2014-12-05 15:31:34
	2014-12-06 15:31:34
	2014-12-07 15:31:34
	*/

	@Test
	public void test(){
//		System.out.println(1000000000L / (3600000L * 24L));
		System.out.println(System.currentTimeMillis() - 45L * (3600000L * 24L * 365L));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse("2015-01-01");
			Long base = date.getTime();
			System.out.println(base);
			Long result = System.currentTimeMillis() - base;
			System.out.println(result);
			System.out.println(Long.toHexString(result));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Long.toHexString(System.currentTimeMillis()));
		System.out.println(47301246000L / (1L * 3600000L * 24L * 30L));
	}
	
	
	@Test
	public void test0(){
		Date date = new Date(0L);
		System.out.println(JsonUtil.toJSONString(date));
	}
	
	@Test
	public void timeZone(){
		Long curLong = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GM+0"));
		System.out.println(sdf.format(new Date(curLong)));
	}
}
