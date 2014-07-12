package com.gpzuestc.fundamentals.runtime;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class CmdExecutor {
	
	@Test
	public void testRunCurl() throws IOException{
		Runtime runtime = Runtime.getRuntime();
//		Process process = runtime.exec("curl www.baidu.com");
		Process process = runtime.exec("curl -o /dev/null -s -w time_total:%{time_total}\\ntime_namelookup:%{time_namelookup}\\ntime_connect:%{time_connect}\\ntime_appconnect:%{time_appconnect}\\ntime_pretransfer:%{time_pretransfer}\\ntime_redirect:%{time_redirect}\\ntime_starttransfer:%{time_starttransfer} http://www.baidu.com");
		InputStream in = process.getInputStream();
		byte[] buffer = new byte[1000];
		StringBuilder sb = new StringBuilder();
		int readCount = -1;
		while((readCount = in.read(buffer)) != -1){
			sb.append(new String(buffer, 0, readCount));
		}
		System.out.println(sb.toString());
	}
}
