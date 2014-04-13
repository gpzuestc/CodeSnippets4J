package com.gpzuestc.exe;

import java.io.IOException;

/**
 * Description
 * 
 * @author guopengzhang@sohu-inc.com @2011-7-25
 * 
 */

public class CallExe {
	public static void main(String[] args) {
//		openWinExe();
//		openExe();
		openFolder();
	}

	public static void openWinExe() {
		Runtime rn = Runtime.getRuntime();
		Process p = null;
		try {
			String command = "notepad";
			p = rn.exec(command);
		} catch (Exception e) {
			System.out.println("Error win exec!");
		}
	}

	public static void openExe() {
		Runtime rn = Runtime.getRuntime();
		Process p = null;
		try {
			p = rn.exec("\"D:/QQ2011.exe\"");
		} catch (Exception e) {
			System.out.println("Error exec!");
		}
	}
	
	public static void openFolder(){
		Runtime rn = Runtime.getRuntime();
		try {
			rn.exec("explorer d:\\nagios_cfg");
			
//		   System.out.println(rn.exec("ping www.baidu.com"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
