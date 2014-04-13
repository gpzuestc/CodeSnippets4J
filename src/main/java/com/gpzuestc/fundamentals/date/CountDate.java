package com.gpzuestc.fundamentals.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CountDate {
	/**  
	   * �õ�����ǰ��ʱ��  
	   * @param d  
	   * @param day  
	   * @return  
	   */  
	  public static String getDateBefore(Date d,int day){   
	   Calendar now =Calendar.getInstance();   
	   now.setTime(d);   
	   now.set(Calendar.DATE, now.get(Calendar.DATE)-day);   
	   return format(now.getTime()); 
	  }   
	     
	  /**  
	   * �õ�������ʱ��  
	   * @param d  
	   * @param day  
	   * @return  
	   */  
	  public static String getDateAfter(Date d,int day){   
	   Calendar now =Calendar.getInstance();   
	   now.setTime(d);   
	   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);   
	   return format(now.getTime());   
	  }  
	  
	  public static String getCurrentSunday(){
		  Calendar cd = Calendar.getInstance();
		  int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		  return getDateBefore(new Date(), dayOfWeek - 1);
	  }
	  
	  public static String getFirstDayOfMonth(){
		  Calendar cd = Calendar.getInstance();
		  int dayOfMonth = cd.get(Calendar.DAY_OF_MONTH);
		  return getDateBefore(new Date(), dayOfMonth - 1);
	  }
	  
	  private static String format(Date date){
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  return sdf.format(date);
	  }
	  public static void main(String[] string) throws ParseException{
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		  System.out.println("ǰ5201314������" + getDateBefore(new Date(),5201314));
		  System.out.println("ǰ20������" + getDateBefore(new Date(),20));
		  System.out.println("ǰ100������" + getDateBefore(new Date(),100));
		  
		  System.out.println("��5201314������" + getDateAfter(new Date(),5201314));
		  System.out.println("��20������" + getDateAfter(new Date(),20));
		  System.out.println("��300������" + getDateAfter(sdf.parse("2010-11-6"),300));
		  
		  System.out.println("����������" + getCurrentSunday());
		  System.out.println("���µ�һ������" + getFirstDayOfMonth());
	  }

}
