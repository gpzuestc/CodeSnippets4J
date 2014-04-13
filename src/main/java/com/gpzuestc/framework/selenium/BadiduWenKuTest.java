package com.gpzuestc.framework.selenium;

import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Selenium;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-8-1
 *
 */

public class BadiduWenKuTest extends SeleneseTestCase {
    private Selenium selenium ;

    public void setUp() throws Exception {
        String url = "http://wenku.baidu.com/";
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", url);  //4444 is default server port 
        selenium.start();      
    }
    
    public void tearDown() throws Exception {
        selenium.stop();
    }
    @Test
    public void test() throws Throwable {
    	for(int i = 2 ; i <= 6; i++){
    		selenium.open("/list/9");
    		selenium.click("//table[@id='docList']//tr[" + i + "]" + "//a[contains(@href,'view')]");
    		selenium.selectWindow("1");
    		selenium.click("//span[@id='rateStar']//span[2]");
    		Thread.sleep(2000);
    	}
    }

} 



