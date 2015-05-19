package com.gpzuestc.network;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class HttpClientTest {
	private static final Log log = LogFactory.getLog(HttpClientTest.class);
	private static final String url = "http://ww2.sinaimg.cn/bmiddle/69b47d90jw1dysdh0b75kj.jpg";
	private static InputStream is;

	public static void main(String[] args) {
			HttpClient hc = new HttpClient();
			GetMethod method = new GetMethod(url);
			
			/*下行代码防止出现警告提示：
			 * 警告: Cookie rejected: "$Version="1"; dssid2="1ed981ac-8646-4861-9007-28b78b4abbe1";
			 *  $Path="/"; $Domain=".apple.com"". Domain attribute ".apple.com" violates RFC 2109: 
			 *  host minus domain may not contain any dots
			 */
//			method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
			String response = "";
			try {
				// Execute the method.
				int statusCode = hc.executeMethod(method);
				
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + method.getStatusLine());
				}
				
				// Read the response body.
//				is = method.getResponseBodyAsStream();
//				byte[] buffer = new byte[1024];
//				int count;
//				while((count = is.read(buffer))> 0){
//					response = response + new String(buffer, 0, count);
//				}
//				System.out.println(response.length());
				System.out.println(method.getResponseBodyAsString());
				
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
	
	@Test
	public void testProxyCompare() throws Exception{
		long a = 0;
		long b = 0;
		int count = 20;
		for(int i = 0; i < count; i++){
			String url = "http://r8.mp.itc.cn/image/out/load/73ec1bf2-451d-49ed-bef3-4a1fd5d90f12.jpg";
			HttpClient hc = new HttpClient();
			GetMethod method = new GetMethod(url);
			
			long start = System.currentTimeMillis();
			int status = hc.executeMethod(method);
			a += System.currentTimeMillis() - start;
			System.out.println("no:\t" + status + ":" + (System.currentTimeMillis() - start));
			
			url = "http://r8.mp.itc.cn/image/out/load/d0435add-80f4-4f67-8b50-7dde52f64a43.jpg";
			hc = new HttpClient();
			method = new GetMethod(url);
			hc.getHostConfiguration().setProxy("179.31.149.36", 81);
//			hc.getParams().setAuthenticationPreemptive(true);
//		hc.getState().setProxyCredentials(AuthScope.ANY, new UsernamePasswordCredentials("llying.iteye.com","llying"));  
			start = System.currentTimeMillis();
			status = hc.executeMethod(method);
			b += System.currentTimeMillis() - start;
			System.out.println("proxy:\t" + status + ":" + (System.currentTimeMillis() - start));
			System.out.println();
		}
		System.out.println("avarage no:\t" + a/10);
		System.out.println("avarate proxy:\t" + b/10);
		
	}
	
	@Test
	public void testProxy() throws Exception{
//		String uid = "504498613";
		String uid = "501023570";
//		String url = "http://www.baidu.com";
//		String url = "http://interface.game.renren.com/ActivityCenter/?method=UserInfo.setUserIP&catalog=plugins&gameid=jzsg&aname=extend&aid=1039&uid=504498613&callback=jQuery15100277294004365074_1363918341626&_=1363918341669";
		String url = "http://interface.game.renren.com/ActivityCenter/?method=UserInfo.setUserIP&catalog=plugins&gameid=jzsg&aname=extend&aid=1039&uid=" + uid;
		
		String proxy = "";
		int port = 0;
		
		List<String> list = FileUtils.readLines(new File("/Users/gpzuestc/Desktop/daili"));
		System.out.println(list.size());
		int suc = 0;
		for(int i = 0; i < list.size(); i++){
			String line = list.get(i);
			if(line.contains("sock")){
				continue;
			}
			Pattern p = Pattern.compile("([\\d]+\\.[\\d]+\\.[\\d]+\\.[\\d]+)");
			Matcher m = p.matcher(line);
			if(m.find()){
				proxy = m.group(1);
			}
			p = Pattern.compile("(\\s[\\d]+\\s)");
			m = p.matcher(line);
			if(m.find()){
				port = Integer.valueOf(m.group(1).trim());
			}
			HttpClient hc = new HttpClient();
			GetMethod method = new GetMethod(url);
			hc = new HttpClient();
			method = new GetMethod(url);
			hc.getHostConfiguration().setProxy(proxy, port);
			hc.getHttpConnectionManager().getParams().setConnectionTimeout(5 * 1000);
			hc.getHttpConnectionManager().getParams().setSoTimeout(5 * 1000);
			try {
				int status = hc.executeMethod(method);
				if(status == 200){
					suc++;
					System.out.println("status[" + suc + "/" + i +"]=" + status);
				}else{
					System.out.println("status[" + suc + "/" + i +"]=" + status + "; " + proxy + " " + port);
				}
			} catch (Exception e) {
				System.out.println("timeout[" + i + "]" + " " + proxy + " " + port);
				continue;
			} 
		}
		System.out.println("success count : " + suc);
	}
	
	@Test
	public void testSocksProxy() throws Exception{
//		String url = "http://www.baidu.com";
		
		long uid = 501023570;
		long[] uids = {
				239363228
//				502205836,
//				483225259,
//				502163066,	
//				502183568
		};
		String url = "http://interface.game.renren.com/ActivityCenter/?method=UserInfo.setUserIP&catalog=plugins&gameid=jzsg&aname=extend&aid=1039&uid=";
		int targetSuc = 175;

		
		String proxyAddrPrefix = "http://www.xici.net.co/nn/"; //高匿
//		String proxyAddrPrefix = "http://www.xici.net.co/nt/"; //普通
		//代理网址分页，页数
		int page = 0;
		int sucCount = 0;
		String proxy = "";
		int port = 0;
		String type = "";
		
		String proxyAddrUrl = "";
		String proxyUrlHtml = "";
		HttpClient hc = new HttpClient();
		GetMethod method = null;
		
		int nowItem = 0;
		while(sucCount < targetSuc){
			page++;
			hc.getHostConfiguration().setProxyHost(null);
			hc.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
			hc.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);
			
			proxyAddrUrl = proxyAddrPrefix + page;
			method = new GetMethod(proxyAddrUrl);
			
			try {
				int status = hc.executeMethod(method);
				if(status == 200){
					System.out.println("get page " + page + " ok");
					proxyUrlHtml = method.getResponseBodyAsString();
//					System.out.println(proxyUrlHtml);
				}else{
					System.out.println("status=" + status + ";");
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				System.out.println("get proxyAddrHtml timeout;url=" + proxyAddrUrl);
				break;
			} 
			
//			Pattern p = Pattern.compile("<td>(\\d+\\.\\d+\\.\\d+\\.\\d+)</td>\\s+<td>(\\d+)</td>[\\s+<td>[^(</td)]+</td>]{2}\\s<td>([^(</td)]+)</td>");
			Pattern p = Pattern.compile("<td>(\\d+\\.\\d+\\.\\d+\\.\\d+)</td>\\s+<td>(\\d+)</td>(\\s+<td>\\s*.+\\s*</td>){2}\\s+<td>(.+)</td>");
			Matcher m = p.matcher(proxyUrlHtml);
			while(m.find() && sucCount < targetSuc){
				proxy = m.group(1);
				port = Integer.valueOf(m.group(2));
				type = m.group(4);
				if(page > 2 && type.contains("sock")){
					continue;
				}
				hc.getHostConfiguration().setProxy(proxy, port);
				hc.getHttpConnectionManager().getParams().setConnectionTimeout(3 * 1000);
				hc.getHttpConnectionManager().getParams().setSoTimeout(3 * 1000);
				int i = 0;
				boolean flag = true;
				int status = 0;
				for(; i < uids.length && flag == true; i++){
					try {
						method = new GetMethod(url + uids[i]);
						status = hc.executeMethod(method);
						if(status == 200){
							System.out.println("page:" + page + " status[" + i + ":" + sucCount + "/" + nowItem +"]=" + status + "; type=" + type);
						}else{
							System.out.println("page:" + page + " status[" + i + ":" + sucCount + "/" + nowItem +"]=" + status + "; " + proxy + " " + port + "; type=" + type);
							if(i == 0){
								flag = false;
							}
						}
					} catch (Exception e) {
						System.out.println("page:" + page + " timeout[" + sucCount + "/" + nowItem + "]" + " " + proxy + " " + port + "; type=" + type);
						if(i == 0){
							flag = false;
						}

					}
				}
				nowItem++;
				if(i == uids.length && status == 200){
					sucCount++;
				}
			}
			
		}
		System.out.println("success ratio=" + sucCount * 100 / nowItem + "%");
		
	}
	
	@Test
	public void testCookie() throws Exception{
		String loginUrl = "http://haijia.bjxueche.net:8001/System/Login?username=bjcsxq&password=bjcsxq2012";
		HttpClient hc = new HttpClient();
//		hc.getHostConfiguration().setProxy("127.0.0.1", 8888);
		GetMethod method = new GetMethod(loginUrl);
		
//		method.setRequestHeader("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
//		method.setRequestHeader("Content-Type", "application/json; charset=utf-8");
//		int status = hc.executeMethod(method);
//		System.out.println(status);
//		Cookie[] cookies = hc.getState().getCookies();
//		String cookie = "";
//		for(Cookie c : cookies){
//			cookie += (c.getName() + "=" + c.getValue() + ";");
//		}
//		System.out.println( cookie);
		
		Date now = new Date();
		Date date = DateUtils.addDays(now, 7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		System.out.println(dateStr);
		String queryUrl = "http://haijia.bjxueche.net:8001/KM2/ClYyCars2?filters%5Byyrq%5D=" + dateStr + "&filters%5Bxnsd%5D=812&filters%5Bjlcbh%5D=&filters%5Bxxzh%5D=50994936";
		method = new GetMethod(queryUrl);
		method.setRequestHeader("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
		method.setRequestHeader("Content-Type", "application/json; charset=utf-8");
//		method.setRequestHeader("Cookie", "UserInfo=Cx7QwxIOJ3wc6Ex2RPQrpigfyCGkPQo7XQlGScAQCQw8yqvteGeLTInRwD9UsdzrgS05Dg9kKDqS/Ovm8kscaZ9sSPANNWvBMwtYreYtzLDfk7uBtTpR1gU/TTb6vxDgiZzhhbl07BEr7SfmkUOa1sRcjNYN4gHeUFRUkK1DOOmjMCeW9A+1GJenXtLbuB4ziNrNCt84U5Jpt8+iRlo4RNYIyhbSXxq1ONe/PgbwgAx/yJLSVlRfWrSR9kTxUSDtVpNxVqVKcBfZfpGfmQwun218Shop1o4oBeX5qHRgtP828HagWanJvGBPAACzHJBRGtu85FJa76FFeSuSS0XqGEvZPiVSjElJkc/qr3eUSyfy8Vf498ExTu4aBFZuxKJW4E1RdzSDJLf9FtU1/mV3P55tD0TIUDNsUQyPrDi0CtPSnFvclcwxXFOUy1M8nGW33BCyhgmHmxgP3RMD2Mfvy13y51Spq+jtB231IbX3Q9wHnV4yC5UGcJLKaJj8w+V80sLGBBprWXQhbxwljD086gDKn39OrLEIpbmyjgUCY4Dq3WjEJkpFBl7aofqT8j7bCD2rNSMK34P/S19cByMKHuqVDg+jBc7rQFu1VgBEKRxBrKKrz42AGhxDcPdGkyUcoKV97Ksqh54fR82Lhz4MA4LNuOCo2Q63RIi/3LQqd+rBvvVCo6x6P5QxhjCqGVz6RvAGzgmcsaThbpXZzkZvnN07hNZ8FoCY12LpHOf/GW3fYkbnBz6INcqJlMzMOl0aiwxS/i3A959UDIFK7M8etrb1BZLbdPRBmt625qTM2P5wzI7uXxQBfQMy3TmPd67WQ1E8K0qaVDu6djbo99TBq+ItBits7oP/WAWJLNaZsfhuQKNRAhSur2kgRkKL7EJV5ldpJIznlicEFmBBaSmXv0nJwQkv0ccITN5KHeTxTTe4TRv9jBlZ92iGUIqi9ZIX9sm3z3UsXmnkzkM0r4S1GZ37P62dFtB4iNrNCt84U5I6wZoI/j30sjwf2HL5bhIlrDnTNj2lmSU1sZX6YgoCv+m2lD+ODtP4TcExXjKIQ3hAVYsujcvkCxFCIXazS8/vYSDJT4ycDr8jiNWqmX5p3kMlgwU1GWnN5qmrn//z8JqadXMZQkzkP3Vibze4yeAsK4d8+AFfEjEmqOKPZwTDJZy5eCownGq656oniSh5RUFYBYks1pmx+PT8VrNKTNe+exHpGBuOboBJXTsbscHKqYmGkLlR1NVD;u={%0d%0a  \"ID\": \"499\",%0d%0a  \"UserName\": \"bjcsxq\",%0d%0a  \"Pwd\": \"\",%0d%0a  \"RealName\": \"\",%0d%0a  \"UserType\": 2,%0d%0a  \"UserRank\": \",323,518,324,172,163,445,171,170,169,168,116,241,117,247,291,248,329,304,342,313,321,348,266,254,450,451,460,452,455,457,459,465,475,332,447,148,164,149,147,146,145,144,143,142,165,167,166,263,206,208,205,249,207,243,303,448,209,368,369,1301,346,345,154,152,151,150,153,211,213,257,338,256,519,344,158,157,156,155,217,218,219,300,301,302,314,347,370,162,220,221,334,326,333,331,325,1001,1017,1016,1015,1014,1013,1012,1011,1002,1018,1003,1019,1005,1006,1020,1007,1008,1009,1021,1010\",%0d%0a  \"LoginGroupID\": -1,%0d%0a  \"LoginJgID\": 115,%0d%0a  \"LoginJgSysNo\": \"001002001\",%0d%0a  \"RightLimitType\": 2,%0d%0a  \"LastDateTime\": \"0001/01/01 00:00:00\",%0d%0a  \"TimeOutTime\": \"2013/11/28 02:30:42\",%0d%0a  \"IP\": \"123.125.116.232\",%0d%0a  \"SessionID\": null,%0d%0a  \"PlatID\": 1,%0d%0a  \"LoginJgName\": \"%e4%ba%ac%e6%b5%b7%e8%80%83%e8%af%95%e5%9c%ba\"%0d%0a};");
		method.setRequestHeader("Cookie", "UserInfo=Cx7QwxIOJ3wc6Ex2RPQrpigfyCGkPQo7XQlGScAQCQw8yqvteGeLTInRwD9UsdzrgS05Dg9kKDqS/Ovm8kscaZ9sSPANNWvBMwtYreYtzLDfk7uBtTpR1gU/TTb6vxDgiZzhhbl07BEr7SfmkUOa1sRcjNYN4gHeUFRUkK1DOOmjMCeW9A+1GJenXtLbuB4ziNrNCt84U5Jpt8+iRlo4RNYIyhbSXxq1ONe/PgbwgAx/yJLSVlRfWrSR9kTxUSDtVpNxVqVKcBfZfpGfmQwun218Shop1o4oBeX5qHRgtP828HagWanJvGBPAACzHJBRGtu85FJa76FFeSuSS0XqGEvZPiVSjElJkc/qr3eUSyfy8Vf498ExTu4aBFZuxKJW4E1RdzSDJLf9FtU1/mV3P55tD0TIUDNsUQyPrDi0CtPSnFvclcwxXFOUy1M8nGW33BCyhgmHmxgP3RMD2Mfvy13y51Spq+jtB231IbX3Q9wHnV4yC5UGcJLKaJj8w+V80sLGBBprWXQhbxwljD086gDKn39OrLEIpbmyjgUCY4Dq3WjEJkpFBl7aofqT8j7bCD2rNSMK34P/S19cByMKHuqVDg+jBc7rQFu1VgBEKRxBrKKrz42AGhxDcPdGkyUcoKV97Ksqh54fR82Lhz4MA4LNuOCo2Q63RIi/3LQqd+rBvvVCo6x6P5QxhjCqGVz6RvAGzgmcsaThbpXZzkZvnN07hNZ8FoCY12LpHOf/GW3fYkbnBz6INcqJlMzMOl0aiwxS/i3A959UDIFK7M8etrb1BZLbdPRBmt625qTM2P5wzI7uXxQBfQMy3TmPd67WQ1E8K0qaVDu6djbo99TBq+ItBits7oP/WAWJLNaZsfhuQKNRAhSur2kgRkKL7EJV5ldpJIznlicEFmBBaSmXv0nJwQkv0ccITN5KHeTxTTe4TRv9jBlZ92iGUIqi9ZIX9sm3z3UsXmnkzkM0r4S1GZ37P62dFtB4iNrNCt84U5I6wZoI/j30sjwf2HL5bhIlrDnTNj2lmSU1sZX6YgoCv+m2lD+ODtP4TcExXjKIQ3hAVYsujcvkCxFCIXazS8/vb1jCyWIzEAYgZ+jLttGIkUMlgwU1GWnN5qmrn//z8JpfuQbTgS980AjkWq4alg7FgqZ8+LE6QVc0H4KS94kjqFc8opvwHzVuX+ns/2CTK6dTrlkONqnfmKFGpS/xdrxJ3WZSR67+dtyXqLZhkOSX8ETiXedEDdU+;u={%0d%0a  \"ID\": \"499\",%0d%0a  \"UserName\": \"bjcsxq\",%0d%0a  \"Pwd\": \"\",%0d%0a  \"RealName\": \"\",%0d%0a  \"UserType\": 2,%0d%0a  \"UserRank\": \",323,518,324,172,163,445,171,170,169,168,116,241,117,247,291,248,329,304,342,313,321,348,266,254,450,451,460,452,455,457,459,465,475,332,447,148,164,149,147,146,145,144,143,142,165,167,166,263,206,208,205,249,207,243,303,448,209,368,369,1301,346,345,154,152,151,150,153,211,213,257,338,256,519,344,158,157,156,155,217,218,219,300,301,302,314,347,370,162,220,221,334,326,333,331,325,1001,1017,1016,1015,1014,1013,1012,1011,1002,1018,1003,1019,1005,1006,1020,1007,1008,1009,1021,1010\",%0d%0a  \"LoginGroupID\": -1,%0d%0a  \"LoginJgID\": 115,%0d%0a  \"LoginJgSysNo\": \"001002001\",%0d%0a  \"RightLimitType\": 2,%0d%0a  \"LastDateTime\": \"0001/01/01 00:00:00\",%0d%0a  \"TimeOutTime\": \"2013/11/28 02:30:42\",%0d%0a  \"IP\": \"123.125.116.232\",%0d%0a  \"SessionID\": null,%0d%0a  \"PlatID\": 1,%0d%0a  \"LoginJgName\": \"%e4%ba%ac%e6%b5%b7%e8%80%83%e8%af%95%e5%9c%ba\"%0d%0a};");
		
		
		now = new Date();
		hc.executeMethod(method);
		Cookie[] cookies = hc.getState().getCookies();
//		cookies = hc.getState().getCookies();
		for(Cookie c : cookies){
			if("u".equals(c.getName())){
				String serverTime = c.getValue().split("\"TimeOutTime\": \"")[1].split("\",")[0];
				SimpleDateFormat sdf_server = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date serverDate = sdf_server.parse(serverTime);
				System.out.println(DateUtils.addHours(now, 10).getTime() - serverDate.getTime());
			}
		}
		
		String data = method.getResponseBodyAsString();
		JSONObject json = JSONObject.fromObject(data);
		System.out.println(json);
		JSONArray arr = json.optJSONObject("data").optJSONArray("Result");
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < arr.size(); i++){
			list.add(arr.optJSONObject(i).optString("CNBH"));
		}
		System.out.println(list.toString());
	}
	
//	@Test
//	public void testRelease() throws InterruptedException{
	public static void main1(String[] args) throws InterruptedException {
		
		MultiThreadedHttpConnectionManager httpClientManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = httpClientManager.getParams();
		params.setStaleCheckingEnabled(true);
		params.setMaxTotalConnections(10);
		params.setDefaultMaxConnectionsPerHost(6);

		final HttpClient httpClient = new HttpClient(httpClientManager);
		httpClient.getParams().setParameter("http.socket.timeout", 2000);
		httpClient.getParams().setParameter("http.connection.timeout", 2000);
		httpClient.getParams().setParameter("http.connection-manager.timeout", 2000L);
		
		GetMethod method = new GetMethod("http://www.baidu.com");
//		GetMethod method = new GetMethod("http://www.sogou.com");
		try {
			int status = httpClient.executeMethod(method);
			System.out.println(method.getResponseBodyAsString().length());
			System.out.println(method.getResponseBodyAsString());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			method.releaseConnection();
		}
		
		try {
			int status = httpClient.executeMethod(method);
			System.out.println(method.getResponseBodyAsString().length());
			System.out.println(method.getResponseBodyAsString());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			method.releaseConnection();
		}
		
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		for(int i = 0; i < 5; i++){
//			new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					GetMethod method = new GetMethod("http://www.baidu.com");
//					try {
//						httpClient.executeMethod(method);
//						System.out.println(method.getResponseBodyAsString().length());
//						System.out.println("aaa" + method.getResponseBodyAsString());
//					} catch (HttpException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}finally{
//						method.releaseConnection();
//					}
//					
//				}
//			}).start();
//			
//		}
//		final GetMethod method1 = new GetMethod("http://www.google.com");
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					httpClient.executeMethod(method1);
//					System.out.println(method1.getResponseBodyAsString().length());
//					System.out.println(method1.getResponseBodyAsString());
//				} catch (HttpException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally{
////			method.releaseConnection();
//				}
//				
//			}
//		}).start();
		
		for(int i = 0; i < 5; i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					GetMethod method = new GetMethod("http://www.sogou.com");
					try {
						httpClient.executeMethod(method);
						System.out.println(method.getResponseBodyAsString().length());
						System.out.println("bbb" + method.getResponseBodyAsString());
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						method.releaseConnection();
					}
					
				}
			}).start();
			Thread.sleep(2000);
		}
		
		Thread.sleep(100000000L);
		
	}
}
