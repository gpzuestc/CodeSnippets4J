package com.gpzuestc.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import com.gpzuestc.MD5;

/**
 * Description:
 * 
 * @author: guopengzhang@sohu-inc.com
 * @date: Apr 25, 2012
 * 
 */
public class ReadAndWrite {
	private static final int MAX = 1024 * 2;
	private static StringBuilder templateSb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		long begin = System.currentTimeMillis();
		URL url = ReadAndWrite.class.getClassLoader().getResource("tpl1");
		File tplDir = new File(url.getPath());
		
		File merge = new File(tplDir, "merge.html");
		System.out.println(merge.getPath());
		FileOutputStream fos = new FileOutputStream(merge);
		
		File[] files = tplDir.listFiles();
		int i = 0;
		for (File f : files) {
			if (f.isDirectory()) {
				File[] incFiles = f.listFiles();
				for (File ff : incFiles) {
					readAndwrite(fos, ff);
					i++;
				}
			} else {
				readAndwrite(fos, f);
				i++;
			}
		}
		System.out.println(i);
		System.out.println(templateSb.toString().length());
		System.out.println(merge.length());
//		System.out.println(templateSb.toString());
		
		
		File file = new File(tplDir, "test.html");
		FileOutputStream fos1 = new FileOutputStream(file);
		fos1.write(templateSb.toString().getBytes());
		fos1.close();
		
		
		compareMd5(merge, file, templateSb.toString());
		
		System.out.println(System.currentTimeMillis() - begin);
	}

	private static void readAndwrite(FileOutputStream fos, File f)
			throws FileNotFoundException, IOException {
		if (f.getName().contains(".tpl")) {
			System.out.println(f.getPath());
			System.out.println(f.getName());
			byte[] buffer = new byte[MAX];
			FileInputStream fis = new FileInputStream(f);
			int current;
			boolean notInc = false;
			String lineSeparator =  System.getProperty("line.separator");
			String fileSeparator =  System.getProperty("file.separator");
			
			String name = (f.getName().split("\\."))[0];
			System.out.println(name);
			String prefixScript1 = "<script type=\"text/template\" id=\"";
			String prefixScript2 = "\">";
			StringBuilder sb = new StringBuilder();
			sb.append(prefixScript1);
			if(f.getPath().contains(fileSeparator + "inc" + fileSeparator)){
				sb.append("tpl_inc_");
			}else{
				notInc = true;
			}
			sb.append(name);
			if(notInc){
				sb.append("_template");
			}
			sb.append(prefixScript2);
			sb.append(System.getProperty("line.separator")); 
			fos.write(sb.toString().getBytes());
			templateSb.append(sb);
			
			while ((current = fis.read(buffer, 0, MAX)) != -1) {
				fos.write(buffer, 0, current);
				templateSb.append(new String(buffer, 0, current, Charset.forName("utf-8")));
				fos.flush();
			}
			fos.write((lineSeparator + "</script>" + lineSeparator).getBytes());
			templateSb.append(lineSeparator + "</script>" + lineSeparator);
			fis.close();
		}
	}
	
	public static void compareMd5(File merge, File test, String str) throws Exception{
		System.out.println(MD5.crypt(str));
		System.out.println(MD5.crypt(read(merge)));
		System.out.println(MD5.crypt(read(test)));
		
	}
	
	public static String read(File file) throws Exception{
		FileInputStream fis  = new FileInputStream(file);
		StringBuilder sb = new StringBuilder();
		int current = 0;
		byte[] buffer = new byte[MAX];
		while ((current = fis.read(buffer, 0, MAX)) != -1) {
			sb.append(new String(buffer, 0, current, Charset.forName("utf-8")));
		}
		
		return sb.toString();
	}
}
