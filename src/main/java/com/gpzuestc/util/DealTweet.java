package com.gpzuestc.util;

import com.gpzuestc.LimitContent;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jun 18, 2012
 * 
 */
public class DealTweet {
	
	public static LimitContent showlimit(String content, int number) {
		LimitContent lc = new LimitContent();
		boolean isOverflow = false;
		char[] chars = content.toCharArray();
		boolean isin = false;
		boolean isina = false;
		boolean notin = false;
		boolean outright = false;
		int num = 0;
		int spannum = 0;
		int endspannum = 0;
		//String result = "";
		StringBuilder resultBuilder=new StringBuilder();
		String htmlTemp = "";
		boolean htmldealing = false;
		boolean con = false;
		boolean rectify = false;//�Խ����о���(add by yangxh)
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (num <= number || con) {
				if (c == '<') {
					if (isina) {
						notin = true;
					}
					isin = true;
					if (i + 1 < chars.length && chars[i + 1] == '/') {
						endspannum++;
					} else
						spannum++;
					if (i + 2 <= chars.length
							&& (chars[i + 1] == 'a' || chars[i + 1] == '<' || (chars[i + 1] == '/')
									&& chars[i + 2] == '<')) {
						isina = true;
					}
				}
				if (c == '>') {
					if (!notin) {
						isin = false;
						isina = false;
						if (i > 0 && chars[i - 1] == '/') {
							endspannum++;
						}
					} else {
						notin = false;
						outright = true;
					}
				}
				if (!isin) {
					if (c == '&')
						htmldealing = true;
					if (c == ';' || htmlTemp.length() > 6)
						htmldealing = false;
					if (htmldealing) {
						htmlTemp += String.valueOf(c);
					} else if (HTMLEscapeCharacter.isContain(htmlTemp + ";")) {
						htmlTemp = "";
					} else
						num++;
				}
				if (!notin && !outright)
					//result = result + String.valueOf(c);
					resultBuilder.append(c);
				if (outright)
					outright = false;
				if (spannum == endspannum && con) {
					con = false;
					//String last = content.substring(result.length());
					String last = content.substring(resultBuilder.length());
					//result += last.substring(0, last.indexOf(">") + 1);
					resultBuilder.append(last.substring(0, last.indexOf(">") + 1));
					if (last.length() == 0) {
						isOverflow = false;
					} else {
						isOverflow = true;
					}
				}
			} else {
				isOverflow = true;
				if (spannum == endspannum) {
					con = false;
					//if(result.equals(content))rectify = true;//add by yangxh
					if(resultBuilder.toString().equals(content))rectify = true;//add by yangxh
					//result += "...";
					resultBuilder.append("...");
				} else {
					con = true;
					//result += String.valueOf(c);
					resultBuilder.append(c);
					continue;
				}
				break;
			}
		}
		if(rectify){//add by yangxh
			lc.setContent(content);
			lc.setOverflow(false);
		}else{
			//lc.setContent(result);
			lc.setContent(resultBuilder.toString());
			lc.setOverflow(isOverflow);
		}
		return lc;
	}
	
	public static void main(String[] args) {
		String content = "�����\"�ʰ����\"���������ݸɲ��������������г�����������\"�ʰ����\"���������͹���͵ؼ����ϸɾ־������������г����ܾ��߳���Щ�������ܲ�����¾ͱ�̥���������г�������һ�ٶࡣ�����������ɣ������ݸɲ����������ƶ��󡱾����������Խ��ܡ�<a href=\"http://t.cn/zOjhQHq\" target=\"_blank\">http://t.cn/zOjhQHq</a>";
		LimitContent lc = showlimit(content, 140);
		System.out.println(lc.getContent());
		System.out.println(lc.isOverflow());
	}
}
