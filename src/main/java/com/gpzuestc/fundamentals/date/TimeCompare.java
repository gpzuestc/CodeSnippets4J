package com.gpzuestc.fundamentals.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TimeCompare {

	public static void main(String[] args) {
		boolean flag = isDateBefore("2004-09-09 12:12:12",
				"2005-09-09 16:00:00");
		System.out.println(flag);
		flag = isDateBefore("2006-09-09 01:01:01", "2005-09-09 16:00:00");
		System.out.println(flag);
		flag = isDateBefore("2005-09-09 01:01:01");
		System.out.println(flag);
	}

	// �ж�ʱ��date1�Ƿ���ʱ��date2֮ǰ
	// ʱ���ʽ 2005-4-21 16:16:34
	public static boolean isDateBefore(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	// �жϵ�ǰʱ���Ƿ���ʱ��date2֮ǰ
	// ʱ���ʽ 2005-4-21 16:16:34
	public static boolean isDateBefore(String date2) {
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
