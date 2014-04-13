package com.gpzuestc.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.jhlabs.image.BlockFilter;
import com.jhlabs.image.BoxBlurFilter;
import com.jhlabs.image.DisplaceFilter;
import com.jhlabs.image.EmbossFilter;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.MirrorFilter;
import com.jhlabs.image.ScaleFilter;
import com.jhlabs.image.SharpenFilter;
import com.jhlabs.image.TwirlFilter;
import com.jhlabs.image.VariableBlurFilter;
import com.jhlabs.image.WaterFilter;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-11-28
 * @todo: 
 * 
 */
public class ImageFilterTest {
	private static final String TEST_IMG = "com/guopeng/img/test.jpg";
	private static final String path = "/Users/gpzuestc/Desktop/image/";
	@Test
	public void testScale() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		int width = 300;
		int height = 200;
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "scaleFilter.jpg");
		BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new ScaleFilter(width, height).filter(src, dst), "jpg", outf);
	}
	
	
	@Test
	public void testBoxBlur() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "boxBlurFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new BoxBlurFilter(5,5,3).filter(src, dst), "jpg", outf);
	}
	
	@Test
	public void testGaussianFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "GaussianFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new GaussianFilter(40.0f).filter(src, dst), "jpg", outf);
	}
	
	@Test //x
	public void testVariableBlur() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "variableBlurFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new VariableBlurFilter().filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testEmboss() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "EmbossFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new EmbossFilter().filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testMirrorFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "MirrorFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new MirrorFilter().filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testWaterFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "WaterFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new WaterFilter().filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testSharpenFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "SharpenFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new SharpenFilter().filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testGrayscaleFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "GrayscaleFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new GrayscaleFilter().filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testDisplaceFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "DisplaceFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		ImageIO.write(new DisplaceFilter().filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testBlockFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "BlockFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		BlockFilter blockFilter = new BlockFilter();
		blockFilter.setBlockSize(10);
		blockFilter.setEdgeAction(0);
		blockFilter.setInterpolation(0);
		ImageIO.write(blockFilter.filter(src, dst), "jpg", outf);
	}
	
	@Test 
	public void testTwirlFilter() throws Exception{
		URL url = ClassLoader.getSystemResource(TEST_IMG);
		File inf = new File(url.toURI());
		System.out.println(inf.getPath());
		BufferedImage src = ImageIO.read(inf);
		File outf = new File(path + "TwirlFilter.jpg");
		BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
		TwirlFilter twirlFilter = new TwirlFilter();
		twirlFilter.setAngle(0.9f);
		twirlFilter.setRadius(400);
		ImageIO.write(twirlFilter.filter(src, dst), "jpg", outf);
	}
	
	
	
	
}
