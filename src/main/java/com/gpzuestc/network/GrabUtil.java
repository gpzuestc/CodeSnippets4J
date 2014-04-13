package com.gpzuestc.network;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.gpzuestc.encoding.HtmlDecoder;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-3-3
 * @todo: 
 * 
 */
public class GrabUtil {
//	private static final String HTTP_HIGHSCHOOL = "http://support.renren.com/highschool/";
//	private static final String BaseDir = "/Users/gpzuestc/Documents/schoolData/highschool";
	
//	private static final String HTTP_HIGHSCHOOL = "http://support.renren.com/collegeschool/";
//	private static final String BaseDir = "/Users/gpzuestc/Documents/schoolData/collegeschool";
	
	private static final String HTTP_HIGHSCHOOL = "http://support.renren.com/juniorschool/";
	private static final String BaseDir = "/Users/gpzuestc/Documents/schoolData/juniorschool";
	@Test
	public void testGrabHighSchool() throws Exception{
		HttpClient hc = new HttpClient();
		GetMethod getMethod = new GetMethod("http://support.renren.com/highschool/1101.html");
		
		hc.executeMethod(getMethod);
		
		String res = getMethod.getResponseBodyAsString();
		System.out.println(HtmlDecoder.decode(res));
	}
	
	@Test
	public void testGrabAllHighSchool() throws Exception{
		List<String> lines = FileUtils.readLines(new File("/Users/gpzuestc/Documents/schoolData/city.txt"));
		System.out.println(lines.size());
		JSONArray jsonArray = new JSONArray();
		for(int i = 0; i < lines.size(); i++){
			String line = lines.get(i);
			JSONObject prov = new JSONObject();
			if(line.startsWith("//")){
				Pattern p = Pattern.compile("(\\d+):([^\"]+)");
				Matcher m = p.matcher(line);
				String provName = null;
				Integer provId = null;
				if(m.find()){
					provId = Integer.valueOf(m.group(1));
					provName = m.group(2);
					prov.put("id", provId); //省(市)id
					prov.put("name", provName); //省(市)名
					if(isZXS(provName)){
						String xiaqu = httpGet(HTTP_HIGHSCHOOL + (provId + 1) + ".html");
						FileUtils.write(new File(BaseDir, (provId + 1) + ".txt"), HtmlDecoder.decode(xiaqu));
						if(!provName.contains("香港")){
							String xiang = httpGet(HTTP_HIGHSCHOOL + (provId + 2) + ".html");
							FileUtils.write(new File(BaseDir, (provId + 2) + ".txt"), HtmlDecoder.decode(xiang));
						}
					}
				}
				line = lines.get(++i);
//				System.out.println(line);
//				p = Pattern.compile("(\\[[^]]+\\])");
//				m = p.matcher(line);
//				if(m.find()){
//					JSONArray a = JSONArray.fromObject(m.group(1));
//					System.out.println(a);
//					prov.put("city", m.group(1));
//				}
				JSONArray city_qus = new JSONArray();
				m = p.matcher(line);
				while(m.find()){
					JSONObject city_qu = new JSONObject();
					Integer id = Integer.valueOf(m.group(1));
					String name = m.group(2);
					city_qu.put("id", id); //市(区)id
					city_qu.put("name", name); //市(区)名
					city_qus.add(city_qu);
					if(!isZXS(provName)){
						String city = httpGet(HTTP_HIGHSCHOOL + id + ".html");
						FileUtils.write(new File(BaseDir, id + ".txt"), HtmlDecoder.decode(city));
					}
				}
				if( city_qus != null){
					prov.put("city", city_qus);
				}
				jsonArray.add(prov);
			}
		}
		System.out.println(jsonArray);
	}
	
	private String httpGet(String url) throws Exception{
		HttpClient hc = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		
		hc.executeMethod(getMethod);
		
		String res = getMethod.getResponseBodyAsString();
		return res;
	}
	
	private boolean isZXS(String name){ //是否为直辖市
		if(StringUtils.isBlank(name)){
			return false;
		}
		if(name.contains("北京") || name.contains("天津") || name.contains("上海") || name.contains("重庆") || name.contains("香港")){
			return true;
		}
		return false;
	}
}
