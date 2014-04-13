package com.gpzuestc.network;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

import com.gpzuestc.javamail.SendMail;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jul 23, 2012
 * 
 */
public class HttpClientDemo {
//	private static final String url = "https://secure1.store.apple.com/cn/order/guest/W273633705/honamchaiar%2540yahoo.com";
	private static final String url = "http://mrt2.ap.dhl.com/mrt?AWB=4932416101";
//	private static final String MAILBODY = "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
//			+ "<div align=center>正在处理产品。查看链接1：" + url + "</div>";
	private static final String DOMAIN = "163.com";
	private static final String USERNAME = "zhangguopeng19870";
	private static final String SEND_TO = "guopeng.zhang2010@gmail.com";
	private static final String PASSWORD = "***";
	private static final int M = 5;
	private static boolean unnormal = false;
	private static boolean prepare = true;
	private static boolean shipment = false;
	private static boolean exception = false;
	private static int nowNum = 3;
	
	private static InputStream is;
//	@Test
	public static void m(String[] args){
		for(;;){
			HttpClient hc = new HttpClient();
			GetMethod method = new GetMethod(url);
			
			/*下行代码防止出现警告提示：
			 * 警告: Cookie rejected: "$Version="1"; dssid2="1ed981ac-8646-4861-9007-28b78b4abbe1";
			 *  $Path="/"; $Domain=".apple.com"". Domain attribute ".apple.com" violates RFC 2109: 
			 *  host minus domain may not contain any dots
			 */
			method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
			String response = null;
			String prefix = "<span class=\"strong\">";
			String subfix = "</span>";
			SimpleDateFormat sdf = new SimpleDateFormat("[MM-dd HH:mm:ss]");
			String time = "01/08 - 08/08";
			try {
				// Execute the method.
				int statusCode = hc.executeMethod(method);
				
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + method.getStatusLine());
				}
				
				// Read the response body.
				byte[] responseBody = method.getResponseBody();
				
				// Deal with the response.
				response = new String(responseBody, "utf8");
				
//				System.out.println(response);
				
				if(response!= null){
					String[] sub = response.split("交付:&nbsp;");
					if(sub != null && sub.length >=2){
						String[] sub1 = sub[1].split("通过");
						if(sub1 != null && sub1.length >= 1){
							String str = sub1[0].replace("/2012", "").trim();
							if(!time.equals(str)){
								time = str;
								sendMail("预计交付时间更改为：" + time, "Date changed");
							}
						}
					}
					String date = sdf.format(new Date());
					if(response.contains(prefix + "正在处理产品" + subfix)){
						String content = "正在处理产品。查看链接：" + url;
						String status = "Dealing...";
						if(unnormal){
							recovery(content, status);
						}
						System.out.println(date + " 正在处理产品…… 交付日期：" + time);
//						if(i == 0){
//							if(sendMail("正在处理产品。查看链接：" + url)){
//								System.out.println(date + " 邮件发送成功");
//							}
//						}
					}else if (response.contains(prefix + "正在准备发货" + subfix)){
						String content = "正在准备出货。查看链接：" + url;
						String status = "Prepare...";
						if(unnormal){
							recovery(content, status);
						}
						System.out.println(date + " 正在准备发货…… 交付日期：" + time);
						if(!prepare){
							if(sendMail(content, status)){
								prepare = true;
							}
						}
					}else if(response.contains(prefix + "已发货" + subfix)){
						String content = "已出货。查看链接：" + url;
						String status = "Shipment...";
						if(unnormal){
							recovery(content, status);
						}
						System.out.println(date + " 已出货！！ 交付日期:" + time);
						if(!shipment){
							if(sendMail("已出货。查看链接：" + url, "Shipment...")){
								shipment = true;
							}
						}
					}else{
						System.err.println(date + " 订单状态异常");
						if(!unnormal){
							if(sendMail("订单状态异常！！查看链接：" + url, "Exception!!!")){
								unnormal = true;
							}
						}
					}
				}
				Thread.sleep(1000 * 60 * M);
				if(exception){
					if(sendMail("监控程序恢复正常", "Service Recovery")){
						exception = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				if(!exception){
					if(sendMail("监控程序出错，错误信息：" + e.getMessage(), "Service Error!")){
						exception = true;
					}
				}
			} finally {
				// Release the connection.
				method.releaseConnection();
			}		
		}
	}
	private static void recovery(String content, String status){
		if(sendMail(content, " Recovery! " + status)){
			unnormal = false;
		}
	}
	
	public static void main(String[] args) {
		for(;;){
			HttpClient hc = new HttpClient();
			GetMethod method = new GetMethod(url);
			
			/*下行代码防止出现警告提示：
			 * 警告: Cookie rejected: "$Version="1"; dssid2="1ed981ac-8646-4861-9007-28b78b4abbe1";
			 *  $Path="/"; $Domain=".apple.com"". Domain attribute ".apple.com" violates RFC 2109: 
			 *  host minus domain may not contain any dots
			 */
			method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
			String response = "";
			try {
				// Execute the method.
				int statusCode = hc.executeMethod(method);
				
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + method.getStatusLine());
				}
				
				// Read the response body.
				is = method.getResponseBodyAsStream();
				byte[] buffer = new byte[1024];
				int count;
				while((count = is.read(buffer))> 0){
					response = response + new String(buffer, 0, count);
				}

				// Deal with the response.
//				System.out.println(response);
				
				Pattern pattern = Pattern.compile("<FONT face=arial size=1>\\d{2}:\\d{2}</FONT></TD>");
				Matcher m = pattern.matcher(response);
				int num = 0;
				while(m.find()){
					num++;
//					System.out.println("res: " + m.group(0));
				}
				String[] str1 = response.split("<TH align=left><FONT face=arial color=#aa0000 size=2>Checkpoint Details</FONT></TH>");
				String[] str2 = str1[1].split("<TD vAlign=top noWrap align=left><FONT face=arial size=1>");
				String status = str2[4].split("</FONT></TD>")[0];
				System.out.println("物流状态：" +  status);
				if(num > nowNum){
					nowNum = num;
					System.out.println("物流状态改变!!");
					pattern = Pattern.compile("<CENTER>[\\s\\S]*</CENTER>");
					m = pattern.matcher(response);
					
					if(m.find()){
						sendMail("查看链接：" + url + "<br/>" + m.group(0).replace("80%", "100%"), status);
					}
				}
				
				Thread.sleep(1000 * 60 * M);
//				System.out.println(num);
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}finally {
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
					// Release the connection.
				method.releaseConnection();
			}		
		}
	}
	
	
	private static boolean sendMail(String content, String state) {

		String mailbody = content;
		SendMail fromMail = new SendMail("smtp." + DOMAIN);
		
		fromMail.setNamePass(USERNAME, PASSWORD); // 登陆帐号密码
		fromMail.setNeedAuth(true);// 设置SMTP是否验证
		if (fromMail.setSubject("RMBP DHL状态：[ " + state + " ]") == false)
			return false; // 设置标题
		if (fromMail.setBody(mailbody) == false)
			return false; // 设置邮件体
		if (fromMail.setTo(SEND_TO) == false)
			return false; // 设置收件人
		if (fromMail.setFrom(USERNAME + "@" + DOMAIN) == false)
			return false; // 设置发件人
//		if (fromMail.addFileAffix("D:\\Q.jpg") == false)
//			return; // 设置附件
//		if (fromMail.addFileAffix("c:\\tmuninst.ini") == false)
//			return;
		return fromMail.sendout();
	
	}
}
