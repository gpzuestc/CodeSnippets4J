package com.gpzuestc.framework.selenium;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-8-1
 *
 */

public class SeleniumTest extends SeleneseTestCase {
    private Selenium selenium ;

    public void setUp() throws Exception {
        String url = "http://www.sogou.com";
        //Զ��selenium server
//        selenium = new DefaultSelenium("192.168.1.103", 4444, "*firefox", url);  //4444 is default server port 
//        selenium = new DefaultSelenium("localhost", 4444, "*iexplore", url);  //4444 is default server port 
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", url);  //4444 is default server port 
        selenium.start();      
    }
    
    public void tearDown() throws Exception {
        selenium.stop();
    }

    public void testSogou() throws Throwable {
    	 selenium.open("/");
//         selenium.type("wd", "�Ѻ�΢��");  //�ٶ�
         selenium.type("query", "�Ѻ�΢��"); //�ѹ�
//         selenium.click("su");             //�ٶ� 
         selenium.click("stb");             //�ѹ�
         selenium.waitForPageToLoad("30000");
         assertTrue(selenium.isTextPresent("���Ѻ�΢������"));
    }
    
    public void testSogouMore() throws Throwable {
    	selenium.open("/docs/more.htm");
         selenium.type("query", "�Ѻ�΢��"); //�ѹ�
         selenium.click("css=input[type=submit]");             //�ѹ�
    	selenium.waitForPageToLoad("30000");
    	assertTrue(selenium.isTextPresent("���Ѻ�΢������"));
    }

} 



