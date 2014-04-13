package com.gpzuestc.framework.selenium;

import com.thoughtworks.selenium.SeleneseTestCase;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-7-31
 *
 */

@SuppressWarnings("deprecation")
public class SohuSeleniumTest extends SeleneseTestCase{
//	  public void setUp() throws Exception {
//	        setUp("http://www.baidu.com/", "*firefox");
//	    }
//	      public void testNew() throws Exception {
//	          selenium.open("/");
//	          selenium.type("wd", "�Ѻ�΢��");
//	          selenium.click("su");
//	          selenium.waitForPageToLoad("30000");
//	          assertTrue(selenium.isTextPresent("���Ѻ�΢������"));
//	    }
	      
//	      public void setUp() throws Exception {
//	    	  setUp("http://dev.w.sohu.com", "*firefox");
//	    	  //selenium.setTimeout("60000");
//	      }
//	      public void testNew() throws Exception {
//	    	  selenium.open("/t5/fridoc.do?s_m_u=u238483883|577|69c97f5f65&suv=25172995390178");
//	    	  selenium.type("css=textarea", "�Ѻ�΢��gptest2");
//	    	  selenium.click("link=����");
//	    	  System.out.println(selenium.isTextPresent("�Ѻ�΢��gptest2"));
//	      }
	      
	      
	      public void setUp() throws Exception {
	    	  setUp("http://dev.w.sohu.com", "*firefox");
	      }
	      public void testNew() throws Exception {
	    	  selenium.open("/t1/fridoc.do?s_m_u=u238483883|577|69c97f5f65&suv=25172995390178");
	    	  selenium.type("css=input[type=text]", "�Ѻ�΢��gptestT1");
	    	  selenium.click("css=button[type=submit]");
	    	  selenium.waitForPageToLoad("30000");
	    	  assertTrue(selenium.isTextPresent("�Ѻ�΢��gptestT1"));
	      }
//	      public void setUp() throws Exception {
//	    	  setUp("http://dev.w.sohu.com", "*firefox");
//	      }
//	      public void testNew() throws Exception {
//	    	  selenium.open("http://dev.w.sohu.com/t1/fridoc.do?s_m_u=u238483883|577|69c97f5f65&suv=25172995390178");
//	    	  selenium.type("ct113", "�Ѻ�΢��gptest");
//	    	  selenium.click("send");
//	    	  selenium.waitForPageToLoad("30000");
//	    	  assertTrue(selenium.isTextPresent("�Ѻ�΢��gptest"));
//	      }
	      
//	 public void setUp() throws Exception {
//	        setUp("http://www.google.com/", "*firefox");
//	    }
//	      public void testNew() throws Exception {
//	          selenium.open("/");
//	          selenium.type("q", "selenium rc");
//	          selenium.click("btnG");
//	          selenium.waitForPageToLoad("30000");
//	          assertTrue(selenium.isTextPresent("Selenium Remote-Control"));
//	    }



}


