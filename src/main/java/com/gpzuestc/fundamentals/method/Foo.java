package com.gpzuestc.fundamentals.method;

/**
 * Description  
 * @author guopengzhang@sohu-inc.com @2011-8-7
 *
 */

public class Foo { 
    private String msg; 
    private String text;

    public Foo(String msg) { 
        this.msg = msg; 
    } 

    public void setMsg(String msg) { 
        this.msg = msg; 
    } 

    public String getMsg() { 
        return msg; 
    } 

    public void outInfo() { 
        System.out.println("java"); 
    } 
    
    public void setPara(String msg, String text){
    	this.msg = msg;
    	this.text = text;
    }
    
    public String getText(){
    	return text;
    }
}

