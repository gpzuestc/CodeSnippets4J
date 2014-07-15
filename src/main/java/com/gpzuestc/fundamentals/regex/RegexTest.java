package com.gpzuestc.fundamentals.regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-7-24
 *
 */

public class RegexTest {
	@Test
	public void test2() {
		BufferedReader in;
        Pattern pattern = Pattern.compile("\"/rmi(.+)");
        try {
			in = new BufferedReader(new FileReader(new File("C:/Documents and Settings/asus/����/download/nagios �����ļ�/checkHttpTaskService_define.cfg")));
			String s;
			int i = 0;
			while ((s = in.readLine()) != null) {
			      Matcher matcher = pattern.matcher(s);
			      if (matcher.find()) {
			    	  	i++;
			    	  	String str = matcher.group();
			    	  	String[] strs = str.split("\"");
			            System.out.println( "rmiHttpArgsItemList.add(new RmiHttpArgsItem(rmi_ip, rmi_port, \""+ strs[1] + "\", \"\\\"" + strs[1] + "\\\"" + strs[2] + "\"));");
			      }
			     
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void test() {
//		String rexStr = "href=\"(.+?)\""; 
//		String rexStr = "href=\"(.+)\""; 
		String rexStr = "href=\"(.*)\""; 
		Pattern pattern = Pattern.compile(rexStr);
		Matcher matcher = pattern.matcher("��a href=\"index.html\"����ҳ��/a��");
		if (matcher.find()){
			System.out.println(matcher.group(1));
		}
	}
	
	@Test
	public void cutHttp() {
		String matherString = "http://pp.mtc.sohu.com/deal_pic.jpg?plat=wap&amp;size=1280&amp;url=http://s2.t.itc.cn/mblog/pic/20123_21_17/61948577277068407.png";
		String rexStr = "url=(.*)"; 
		Pattern pattern = Pattern.compile(rexStr);
		Matcher matcher = pattern.matcher(matherString);
		if (matcher.find()){
			System.out.println("res:  " + matcher.group(1));
		}
	}
	@Test
	public void findXX() {
		String matherString = "qwerqwrwer<FONT face=arial size=1>15:55</FONT>qweqweqe<FONT face=arial size=1>15:57</FONT>";
		String rexStr = "<FONT face=arial size=1>\\d{2}:\\d{2}</FONT>"; 
		Pattern pattern = Pattern.compile(rexStr);
		Matcher matcher = pattern.matcher(matherString);
		while(matcher.find()){
			System.out.println("res:  " + matcher.group() + ": " + matcher.group(0));
		}
	}
	
	@Test
	public void getParam() {
		String matherString = "tuan.baidu.com/?do=search&wd=111&ie=aaa";
		String rexStr = "((do|ie|wd)=([^&]+))(&|)"; 
		Pattern pattern = Pattern.compile(rexStr);
		Matcher matcher = pattern.matcher(matherString);
		while(matcher.find()){
			System.out.println("res:  " + matcher.groupCount() + ": " + matcher.group(1));
		}
	}
	
	public static void main(String[] args) {
		String url = "tuan.baidu.com/?do=search&wd=111&ie=aaa";
		System.out.println(getParam(url, "do"));
		System.out.println(getParam(url, "wd"));
		System.out.println(getParam(url, "ie"));
		
	}
	private static String getParam(String url,String param){
		String rexStr = param + "=([^&]+)(&|)";
		Pattern pattern = Pattern.compile(rexStr);
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()){
			return matcher.group(1);
		}else{
			return null;
		}
	}
	
	@Test
	public void removeBracket(){
		String str = "aa(ddd)xxx";
		Pattern p = Pattern.compile("[(|)]");
		Matcher m = p.matcher(str);
		while(m.find()){
			System.out.println(m.replaceAll(""));
		}
	}
	
	@Test
	public void findContentInBracket(){
//		String str = "miruo_1.0.0_[10001]";
		String str = "miruo_1.0.0";
		Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
		Matcher m = p.matcher(str);
		while (m.find()) {
			System.out.println(m.group(1));
		}
	}
	@Test
	public void except(){
		String str = "/amagazine/123/edit";
		Pattern p = Pattern.compile("/(^[^a]?[^/]*)/([\\d]+)/([edit])");
		Matcher m = p.matcher(str);
		while(m.find()){
			System.out.println(m.groupCount());
			System.out.println(m.group(1));
		}
	}
	
	@Test
	public void findA(){
		String str = "b";
		Pattern p = Pattern.compile("[a-c]");
		Matcher m = p.matcher(str);
		if(m.find()){
			System.out.println("res:" + m.group());
		}
	}
	
	@Test
	public void matchImageFile(){
//		Pattern p = Pattern.compile("([^(|)|'|\"|/]+)/([^(|)|'|\"]+)\\.(jpg|png|gif|swf)");
		Pattern p = Pattern.compile("([^(|)|'|\"|/]+)\\.(jpg|png|gif|swf)", Pattern.CASE_INSENSITIVE);
//		String path = "../i/comment.png";
		String path = "a/back.JPG";
		Matcher m = p.matcher(path);
		if(m.find()){
			System.out.println("find:" + m.group());
			System.out.println("find:" + m.group(1));
//			System.out.println("find:" + m.group(2));
		}
	}
	
	@Test
	public void testReplace(){
		String str = ".leafpanel .comment p {background:url( ../i/comment.png ) no-repeat;} .leafpanel .share p {background:url(../i/share.png) no-repeat;} .leafpanel .like p {background:url(../i/like.png) no-repeat;}";
		Pattern p = Pattern.compile("\\(\\s*([^)]+\\.(jpg|png|gif|swf))\\s*\\)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			System.out.println("find=" + m.group(0));
			System.out.println("find=" + m.group(1));
			System.out.println("find3=" + m.group(2));
//			System.out.println(m.replaceFirst(m.group(1) + "1"));
//			m.appendReplacement(sb, "(" + m.group(1) + 1 + ")");
			m.appendReplacement(sb, m.group(0).replace(m.group(1), m.group(1) + "2x"));
		}
		m.appendTail(sb);
		System.out.println(sb.toString());
	}
	
	@Test
	public void testReplcaeSimple(){
//		String str = "大鹏xxxx";
		String str = "《大鹏》43拾掇拾掇大鹏xx";
//		String str = "xx大鹏xdy";
		
		Pattern p = Pattern.compile("^(大鹏|《大鹏》)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		if(m.find()){
			System.out.println("find=" + m.group(0));
//			m.appendReplacement(sb, "(" + m.group(0) + 1 + ")");
			m.appendReplacement(sb, m.group(0).replace(m.group(0),""));
		}
		m.appendTail(sb);
		System.out.println(sb.toString());
		
	}
	
	@Test
	public void test1(){
		String str = "76b1d76c-f07b-4aa6-b910-57775a2f9b62-[31,4].wav";
		Pattern p = Pattern.compile("\\[([\\d]+),([\\d]+)\\]\\.");
		Matcher m = p.matcher(str);
		if(m.find()){
			System.out.println(m.group(1));
			System.out.println(m.group(2));
		}
		
	}
	
	@Test
	public void cutRequestUri(){
		String str = "http://www.vgoody.com/a.json?asd=234&ad=23";
		Pattern p = Pattern.compile("[^?]+");
		Matcher m = p.matcher(str);
		if(m.find()){
			System.out.println("res=" + m.group());
		}
	}
	
	@Test
	public void testNumber(){
		String str = "a1";
		Pattern p = Pattern.compile("[\\d]+");
		Matcher m = p.matcher(str);
		System.out.println(m.find());
		System.out.println(m.matches());
	}
	
}


