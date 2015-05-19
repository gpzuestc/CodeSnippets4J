package com.gpzuestc.img;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.filters.Caption;
import net.coobird.thumbnailator.filters.Colorize;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Positions;

import org.junit.Test;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-11-25
 * @todo: 
 * 
 */
public class ThumbnailTest {
		public static void main(String[] args) throws Exception{
			File origFile = new File("C:\\Documents and Settings\\asus\\桌面\\s01.png");
			File thumbnailFile = new File("C:\\Documents and Settings\\asus\\桌面\\s01_comp.png");
			File thumbnailFile2 = new File("C:\\Documents and Settings\\asus\\桌面\\m_58317501017876321.gif");
			ImageWrap iw = new ImageWrap(origFile);
			System.out.println(iw.getWidth());
			Thumbnailator.createThumbnail(origFile, thumbnailFile, iw.getWidth()/2, iw.getHeight()/2);
			
			System.out.println(iw.getWidth());
			OutputStream os = new FileOutputStream(thumbnailFile2);
			Thumbnails.of(origFile).outputQuality(1.0f).size(iw.getWidth()/2, iw.getHeight()/2).outputFormat("gif").toOutputStream(os);
		}
		

		@Test
		public void testResize() throws Exception{
			Thumbnails.of(new URL("http://ww1.sinaimg.cn/mw690/70b6b917jw1drx80m8suuj.jpg"))
//			Thumbnails.of(new URL("http://r4.mp.itc.cn/image/out/load/935f48f9-c8e1-4541-91b8-75a771467720.jpg"))
//			Thumbnails.of(new URL("http://r7.mp.itc.cn/image/out/load/f9efeed7-8fe8-4ef5-a96b-023962724a5b.jpg"))
//			Thumbnails.of(new File("/Users/gpzuestc/Desktop/web2.jpg"))
//				.scale(0.5f)
				.width(160)
				.outputQuality(1.0f)
				.imageType(BufferedImage.TYPE_INT_RGB)
//				.sourceRegion(Positions.CENTER, 160, 160)
				.toFile("/Users/gpzuestc/Desktop/web1.jpg");
		}
		
		@Test
		public void testResizeAndCorp() throws Exception{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Thumbnails.of(new URL("http://ww1.sinaimg.cn/mw690/70b6b917jw1drx80m8suuj.jpg"))
//			Thumbnails.of(new URL("http://r4.mp.itc.cn/image/out/load/935f48f9-c8e1-4541-91b8-75a771467720.jpg"))
//			Thumbnails.of(new URL("http://r7.mp.itc.cn/image/out/load/f9efeed7-8fe8-4ef5-a96b-023962724a5b.jpg"))
//			Thumbnails.of(new File("/Users/gpzuestc/Desktop/web2.jpg"))
//				.scale(0.5f)
				.width(160)
				.outputQuality(1.0f)
				.imageType(BufferedImage.TYPE_INT_RGB)
//				.sourceRegion(Positions.CENTER, 160, 160)
				.toOutputStream(baos);
//				.toFile("/Users/gpzuestc/Desktop/web1.jpg");
			
			Thumbnails.of(new ByteArrayInputStream(baos.toByteArray()))
			.scale(1.0f)
			.sourceRegion(Positions.CENTER, 160, 160)
			.toFile("/Users/gpzuestc/Desktop/web1.jpg");
		}
		
		@Test
		public void testGif() throws Exception{
			URL url = ClassLoader.getSystemResource("com/guopeng/img/houzi.gif");
			Thumbnails.of(url).outputFormat("png").scale(1.0f).imageType(BufferedImage.TYPE_INT_RGB).toFile("/Users/gpzuestc/Desktop/houzi.png");
		}
		
		@Test
		public void testWholeGif() throws Exception{
			URL url = ClassLoader.getSystemResource("com/guopeng/img/houzi.gif");
			Thumbnails.of(url).scale(1f).imageType(BufferedImage.TYPE_INT_RGB).toFile("/Users/gpzuestc/Desktop/houzi.gif");
		}
		
		@Test
		public void test() throws Exception{
			Thumbnails.of(new URL("http://ww1.sinaimg.cn/mw690/70b6b917jw1drx80m8suuj.jpg"))
			.addFilter(new Colorize(Color.GREEN, 0.2f))
			.scale(1.0).
			outputQuality(1.0f).
			imageType(BufferedImage.TYPE_INT_RGB).
			toFile("/Users/gpzuestc/Desktop/web.jpg");
		}

		@Test
		public void testWater() throws Exception{
			Thumbnails.of(new URL("http://ww1.sinaimg.cn/mw690/70b6b917jw1drx80m8suuj.jpg"))
			.watermark(Positions.CENTER, ImageIO.read(new URL("http://tp3.sinaimg.cn/1745046962/180/39997666396/0")), 0.5f)
			.scale(1.0)
			.outputQuality(1.0f)
			.imageType(BufferedImage.TYPE_INT_RGB)
			.toFile("/Users/gpzuestc/Desktop/water.jpg");
		}
		
		@Test
		public void testWordPress() throws Exception{
			Thumbnails.of(new URL("http://ww1.sinaimg.cn/mw690/70b6b917jw1drx80m8suuj.jpg"))
			.addFilter(new Caption("gpzuestc国鹏", new Font("Arial", Font.BOLD,80), Color.LIGHT_GRAY, 0.5f, Positions.CENTER, 0))
//			.addFilter(new Colorize(Color.GREEN, 0.3f))
//			.addFilter(new Canvas(100, 100,Positions.TOP_LEFT, false, Color.orange))
			.scale(1.0)
			.outputQuality(1.0f)
			.imageType(BufferedImage.TYPE_INT_RGB)
			.toFile("/Users/gpzuestc/Desktop/web.jpg");
		}
		
		@Test
		public void testWordPress1() throws Exception{
			BufferedImage bufferedImage = new BufferedImage(690, 600, BufferedImage.TYPE_INT_RGB);
//			Graphics2D g = bufferedImage.createGraphics();
//			g.setBackground(Color.CYAN);
			Thumbnails.of(bufferedImage)
			.addFilter(new Colorize(Color.GRAY, 1.0f))
			.addFilter(new Caption("gpzuestc国鹏", new Font("Arial", Font.BOLD,80), Color.CYAN, 1f, Positions.CENTER, 0))
//			.addFilter(new Canvas(bufferedImage.getWidth(), bufferedImage.getHeight(), Positions.CENTER, Color.red))
			.scale(1.0)
			.outputQuality(1.0f)
			.imageType(BufferedImage.TYPE_INT_RGB)
			.toFile("/Users/gpzuestc/Desktop/web1.jpg");
		}
		
		@Test
		public void testWordPressPoint() throws Exception{
//			Thumbnails.of(new URL("http://ww1.sinaimg.cn/mw690/70b6b917jw1drx80m8suuj.jpg"))
			Thumbnails.of(new File("/Users/gpzuestc/Downloads/1224.jpeg"))
//			.addFilter(new Caption("gpzuestcb", new Font("Arial", Font.BOLD, 50), Color.ORANGE, 0.8f, new Coordinate(0, 0), 10))
			.addFilter(new CaptionAntialiasing("gpzuestcsb国鹏", new Font("Arial", Font.BOLD, 80), Color.white, 1.0f, Positions.BOTTOM_RIGHT, 40))
			.scale(1.0)
			.outputQuality(1.0f)
			.imageType(BufferedImage.TYPE_INT_RGB)
			.toFile("/Users/gpzuestc/Desktop/web2.jpg");
		}
		
		@Test
		public void testHW() throws IOException{
			BufferedImage image = ImageIO.read(new File("/Users/gpzuestc/Downloads/3.JPG"));
			System.out.println("width:" + image.getWidth());
			System.out.println("height:" + image.getHeight());
		}
		
		@Test
		public void testCover() throws Exception{
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			Thumbnails.of(new URL("http://r7.mp.itc.cn/image/out/load/8cbf23dc-2612-489e-864f-75720f0e26de.jpg"))
			.scale(0.7)
			.outputQuality(1.0f)
			.imageType(BufferedImage.TYPE_INT_RGB)
			.toOutputStream(baos);
			
			ByteArrayInputStream slideIs = parse(baos);
			
			Builder builder = Thumbnails.of(new File("/Users/gpzuestc/Pictures/background.jpg"))
			.watermark(new Coordinate(10, 20), ImageIO.read(slideIs), 1.0f)
			.watermark(new Coordinate(10, 10), ImageIO.read(new File("/Users/gpzuestc/Pictures/setting.png")), 1.0f)
			;
			Font f = new Font("Arial", Font.BOLD,60);
			FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
			String str = "Gpzuestc国鹏 我是傻逼，sw，nm也是,dou tm shab";
			char[] chars = str.toCharArray();

			int MAX_WIDTH = 300;
			for(int i = 0, j = 0, begin = 0; i < chars.length; j++, begin = i){
				int width = 0;
				for(;i < chars.length && width + fm.charWidth(chars[i]) < MAX_WIDTH; i++){
					width = width + fm.charWidth(chars[i]);
					System.out.println(chars[i]);
				}
				builder = builder.addFilter(new Caption(str.substring(begin, i), f, Color.orange, 1f, new Coordinate(500, 50 + fm.getHeight() * j), 0));
			}
			
			builder.scale(1.0)
			.outputQuality(1.0f)
			.imageType(BufferedImage.TYPE_INT_RGB)
			.toFile("/Users/gpzuestc/Desktop/cover.jpg");
			
		}
		
		public static ByteArrayInputStream parse(OutputStream out) throws Exception
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = (ByteArrayOutputStream) out;
			ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
			return swapStream;
		}
		
		@Test
		public void testFont(){
			Font f = new Font("Arial", Font.BOLD, 24);
			FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
//			FontMetrics fm = new JLabel().getFontMetrics(f);
			//高度
			System.out.println( fm.getHeight() );
			//单个字符宽度
			System.out.println( fm.charWidth( 'a' ));
			//整个字符串的宽度
			System.out.println( fm.stringWidth( "0" ));
		}
		
		@Test
		public void testPin() throws Exception{
			Thumbnails.of(new File("/Users/gpzuestc/Pictures/test.jpg"))
			.watermark(Positions.BOTTOM_CENTER, ImageIO.read(new File("/Users/gpzuestc/Pictures/imgbottom.jpg")), 1.0f)
			.scale(1.0)
			.outputQuality(1.0f)
			.imageType(BufferedImage.TYPE_INT_RGB)
			.toFile("/Users/gpzuestc/Pictures/wild.jpg");
		}
}	
