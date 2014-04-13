package com.gpzuestc.img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;
import com.drew.metadata.exif.ExifReader;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2013-1-5
 * @todo: 
 * 
 */
public class ImageTest {
	
	@Test
	public void testMetaData(){
		 File f = new File("/Users/gpzuestc/Downloads/2.JPG");

		   try {

		    ExifReader er = new ExifReader(f);

		    Metadata exif = er.extract();

		    Iterator itr = exif.getDirectoryIterator();

		    while (itr.hasNext()) {

		     Directory directory = (Directory) itr.next();



//		     System.out.println("EXIF版本：" + directory.getString(ExifIFD0Directory.TAG_EXIF_VERSION));   

		     System.out.println("相机品牌：" + directory.getString(ExifDirectory.TAG_MAKE));   

		     System.out.println("相机型号：" + directory.getString(ExifDirectory.TAG_MODEL));   

		     System.out.println("光 圈 值：" + directory.getString(ExifDirectory.TAG_FNUMBER));   

		     System.out.println("快    門：" + directory.getString(ExifDirectory.TAG_EXPOSURE_TIME));   

		     System.out.println("感 光 度：" + directory.getString(ExifDirectory.TAG_ISO_EQUIVALENT));

		     System.out.println("软    件：" + directory.getString(ExifDirectory.TAG_SOFTWARE)); 

		     System.out.println("原始拍摄时间：" + directory.getString(ExifDirectory.TAG_DATETIME_ORIGINAL));

		     System.out.println("数字化时间：" + directory.getString(ExifDirectory.TAG_DATETIME_DIGITIZED));

		     System.out.println("方    向：" + directory.getString(ExifDirectory.TAG_ORIENTATION));

		     System.out.println("图片宽度：" + directory.getString(ExifDirectory.TAG_EXIF_IMAGE_WIDTH));

		     System.out.println("图片高度：" + directory.getString(ExifDirectory.TAG_EXIF_IMAGE_HEIGHT));

		     System.out.println("水平分辨率：" + directory.getString(ExifDirectory.TAG_X_RESOLUTION));

		     System.out.println("垂直分辨率：" + directory.getString(ExifDirectory.TAG_Y_RESOLUTION));

		     System.out.println("垂直分辨率：" + directory.getString(ExifDirectory.TAG_EXPOSURE_BIAS));

//		     break;



		     //获得全部metadata   

		     Iterator tags = directory.getTagIterator();

		     while (tags.hasNext()) {

		      Tag tag = (Tag) tags.next();

		      System.out.println(tag);

		     }

		     if (directory.hasErrors()) {

		      Iterator errors = directory.getErrors();

		      while (errors.hasNext()) {

		       System.out.println("ERROR:   " + errors.next());

		      }

		     }

		    }

		   } catch (JpegProcessingException e) {

		    System.err.println("not   jpeg   file");

		   }
	}
	
	/**
	 * @param srcFile
	 *            源图像文件
	 * @param x
	 *            阴影宽度
	 * @param y
	 *            阴影高度
	 * @param color
	 *            阴影颜色
	 * @param alpha
	 *            透明度，0－1之间取值
	 * @throws Exception
	 *             为指定的文件加上透明阴影
	 */
	public final static void addOutShadow(File srcFile, int x, int y, Color color,
			float alpha) throws Exception {
		// 读取源文件
		BufferedImage srcImg = ImageIO.read(srcFile);
		// 在新建一个图像缓冲区，大小包括阴影部分
		BufferedImage bufImg = new BufferedImage(srcImg.getWidth() + x,
				srcImg.getHeight() + y, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufImg.createGraphics();
		// 创建一个支持有透明度的图像缓冲区
		bufImg = g.getDeviceConfiguration().createCompatibleImage(
				srcImg.getWidth() + x, srcImg.getHeight() + y,
				Transparency.TRANSLUCENT);
		g.dispose();
		// 画阴影部分
		g = bufImg.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.XOR, alpha));
		g.setColor(Color.black);
		g.setBackground(Color.red);
		g.fillRect(x, y, srcImg.getWidth(), srcImg.getHeight());
		g.dispose();
		// 画源图像
		g = bufImg.createGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1));
		g.drawImage(srcImg, 0, 0, null);
		g.dispose();
		String oldName = srcFile.getName();
		// 目标文件必须是PNG文件
		String fileName = "/Users/gpzuestc/Desktop/"
				+ oldName.substring(0, oldName.length() - 4) + ".png";
		ImageIO.write(bufImg, "PNG", new File(fileName));
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File f = new File("/Users/gpzuestc/Desktop/cover.jpg");
		addOutShadow(f, 20, 20, Color.red, 0.3f);
		System.out.println("阴影添加成功，阴影文件扩展名为.png");
	}
}
