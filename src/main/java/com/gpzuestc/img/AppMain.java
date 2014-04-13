package com.gpzuestc.img;

import java.io.File;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jun 4, 2012
 * 
 */
public class AppMain {
	private static final float SCALE = 0.5f;

	@Test
	public void testProportion(){
		ImageTool it = new ImageTool();
		it.compressPic("C:\\Documents and Settings\\asus\\����\\", 
				"C:\\Documents and Settings\\asus\\����\\", "NewT5b.jpg", "NewT5b_compress.jpg");
	}
	
	public static void main(String[] args) throws Exception{
		if(args.length == 0){
			System.out.println(" Command format is incorrect: \n Usage: <Application-Name> <picture path> [output path | scale]");
		}else{
			final String separator = System.getProperty("file.separator");
			String path = args[0];
			if(!path.contains(":")){
				path =  System.getProperty("user.dir") + separator + args[0];
			}
			File file = new File(path);
			ImageWrap iw = new ImageWrap(file);
			if(iw != null){
				if(args.length == 1){
					iw.compress(SCALE);
				}else if(args.length == 2){
					if(NumberUtils.isNumber(args[1])){
						iw.compress(Float.valueOf(args[1]));
					}else{
						iw.compress(SCALE, args[1]);
					}
				}else{
					iw.compress(Float.valueOf(args[2]), args[1]);
				}
			}
		}
	}
}
