package com.gpzuestc.fundamentals.date;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * @author gpzuestc
 * @date: 2014-3-24
 * @description:  
 * 
 */
public class JodaTest {

	@Test
	public void testDayOfWeek(){
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMdd");
		
		DateTime dt = new DateTime();
		System.out.println(dt.toDate());
		System.out.println(dt.toString(dtf));
		
		dt = dt.plusDays(-1);
		System.out.println(dt.getDayOfWeek());
		
		System.out.println(DateTime.parse("20140322", dtf));
		System.out.println(DateTime.parse("20140322", dtf).getDayOfWeek());
		
		DateTime.Property p = dt.dayOfWeek();
		System.out.println(p.getAsText(Locale.CHINA));
		System.out.println(p.getAsShortText(Locale.CHINESE));
		System.out.println(p.getAsString());
        System.out.println();
    }

    @Test
    public void test(){
        System.out.println();
        System.out.println("yss");
        System.out.println();
        String test = "";
    }

    public void test1(){
        System.out.println();
    }

}
