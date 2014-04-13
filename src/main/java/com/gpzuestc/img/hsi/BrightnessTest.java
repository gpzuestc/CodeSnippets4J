package com.gpzuestc.img.hsi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;


/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-22
 * @todo: 
 * 
 */
public class BrightnessTest {
	
	@Test
	public void testLimit(){ //白色饱和度s:0
		RGB rgb = new RGB("color", 250, 250, 250); 
		HSI hsi = HSI.RGB2HSI(rgb);
		System.out.println("h:" + hsi.getHue());
		System.out.println("s:" + hsi.getSaturation());
		System.out.println("i:" + hsi.getIntensity());
		System.out.println(new BrightnessRecognizer().computerBrightness(rgb).getName());
	}
	

	@Test
	public void testSubImage() throws IOException{
		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1256.PNG"));
		System.out.println(new HueRecognizer().computerHue(bufferedImage, 0, 0, 320, 480).getName());
	}
	
	@Test
	public void test() throws Exception{
		
		long begin = System.currentTimeMillis();
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Desktop/hue/purple.png")); //紫色
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1208.PNG")); //红
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1224.PNG")); //粉红
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/pad/IMG_0557.PNG")); //青色
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1261.PNG"));  //棕色
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1241.PNG"));  //浅灰色
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1257.PNG"));  //黄绿色
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1224.PNG"));  //1x3
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1229.PNG"));  //3x1
		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1225.PNG"));  //3x3
//		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gpzuestc/Downloads/phone/IMG_1207.PNG"));  //3x3
//		BufferedImage bufferedImage = ImageIO.read(new URL("http://news.saisai.com/attach_img/2009/08/07/12496003367155.jpg")); //红色
//		BufferedImage bufferedImage = ImageIO.read(new URL("http://hiphotos.baidu.com/%CA%F3%B2%BB%C7%E5%B5%C4%B0%AE/pic/item/d9ced61b1730650142a9adbc.jpg")); //蓝色
//		BufferedImage bufferedImage = ImageIO.read(new URL("http://hiphotos.baidu.com/%CA%F3%B2%BB%C7%E5%B5%C4%B0%AE/pic/item/02d8c8493d1ca0b482025c96.jpg")); //绿色
//		BufferedImage bufferedImage = ImageIO.read(new URL("http://img3.yxlady.com/yl/UploadFiles_5361/20110228/20110228190530166.jpg"));  //黑白 (浅灰)
//		BufferedImage bufferedImage = ImageIO.read(new URL("http://www.shm.com.cn/newscenter/attachement/jpg/site1/20050411/xin_370402111609862774128.jpg"));  //dark
//		BufferedImage bufferedImage = ImageIO.read(new URL("http://img2.niutuku.com/desk/232/231-27014.jpg"));  //gray 深灰
//		BufferedImage bufferedImage = ImageIO.read(new URL("http://ww2.sinaimg.cn/bmiddle/7b38fb67jw1e03zfduiphj.jpg"));  //green
		System.out.println("get image:" + (System.currentTimeMillis() - begin));
		BrightnessRecognizer brightnessRecognizer = new BrightnessRecognizer();
		Brightness brightness = brightnessRecognizer.computerBrightness(bufferedImage);
		System.out.println("get hue:" + (System.currentTimeMillis() - begin));
		System.out.println(brightness.getName());
		
	}
}
