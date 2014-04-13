package com.gpzuestc.timer;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("hello" + System.currentTimeMillis());
			}
		}, 0, 2*1000);
		while(true){
			 try { 
	                int ch = System.in.read();
	                if(ch-'c'==0){ 
	                    timer.cancel();//使用这个方法退出任务
	                    
	                }
	            } catch (IOException e) { 
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
		}
	}
}
