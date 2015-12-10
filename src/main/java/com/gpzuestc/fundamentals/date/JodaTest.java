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
    public void testBetween(){
    	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime now = new DateTime();
		DateTime end = DateTime.parse("2014-06-02", dtf);
		DateTime current = now;
		while(current.isBefore(end.getMillis())){
			System.out.println(current.toString(dtf));
			current = current.plusDays(1);
		}
    }
    
    @Test
    public void testNext(){
    	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    	countNext(DateTime.parse("2014-06-02 12:46", dtf));
		countNext(DateTime.parse("2014-06-02 15:00", dtf));
		countNext(DateTime.parse("2014-06-02 15:01", dtf));
		countNext(DateTime.parse("2014-06-02 15:21", dtf));
		countNext(DateTime.parse("2014-06-02 15:31", dtf));
		countNext(DateTime.parse("2014-06-02 15:51", dtf));
    }
    
    private void countNext(DateTime now){
    	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

		DateTime next = now.plusHours(1).plusMinutes(5);
		long circle = 15 * 60 * 1000;
		long div = next.getMillis() / circle;
		long mod = next.getMillis() % circle;
		
		long count = (mod == 0) ? div : div + 1;
		DateTime nextDate = new DateTime(count * circle);
		System.out.println(nextDate.toString(dtf));
    }
    
    @Test
    public void testPlus(){
    	DateTime dt = new DateTime();
		DateTime endDt = dt.plusDays(1 + 5);
		System.out.println(endDt.getMillis()/1000);
    }

}
