package com.gpzuestc.img;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnailator;

/**
 * Description:  
 * @author: guopengzhang@sohu-inc.com 
 * @date: Jun 4, 2012
 * 
 */
public class ImageWrap {
	private String path;
	private Image image;
	private File file;
	private long size;
	private int width;
	private int height;
	

	public ImageWrap(String input) throws Exception {
		this(new File(input));
	}
	
	public ImageWrap(File file) throws Exception{
		if(file == null){
			throw new RuntimeException("error= The file of image input is null");
		}
		if(!file.exists()){
			throw new RuntimeException("error= The file path of image input is invalid; path=" + path);
		}
		this.file = file;
		path = file.getPath();
		size = file.length();
		image = ImageIO.read(file);
		width = image.getWidth(null);
		height = image.getHeight(null);
		
	}
	public boolean compress (float scale) throws Exception{
		return compress((int)(width * scale), (int)(height * scale));
	}
	
	public boolean compress (float scale, String outpath) throws Exception{
		return compress((int)(width * scale), (int)(height * scale), outpath);
	}
	
	public boolean compress (Integer newWidth, Integer newHeight) throws Exception{
		return compress(newWidth, newHeight, null);
	}
	public boolean compress (Integer newWidth, Integer newHeight, String outputpath) throws Exception{
		boolean success = true;
		if(outputpath == null || "".equals(outputpath.trim())){
			outputpath = file.getParent() + System.getProperty("file.separator") + "thumb_" + file.getName();
			System.out.println(outputpath);
		}
		if(newWidth != null && newHeight == null){
			newHeight = newWidth * height / width;
		}else if(newWidth == null && newHeight != null){
			newWidth = newHeight * width / height;
		}else if(newWidth == null && newHeight == null){
			throw new RuntimeException("error= the width and height of compressed image file can't be null");
		}
		
		File outputFile = new File(outputpath);
		Thumbnailator.createThumbnail(file, outputFile, newWidth, newHeight);
		return success;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	public static void main(String[] args) throws Exception{
		ImageWrap iw = new ImageWrap("C:\\Documents and Settings\\asus\\桌面\\NewT5b.jpg");
		System.out.println("width:" + iw.getWidth());
		System.out.println("height:" + iw.getHeight());
		System.out.println("size:" + iw.getSize());
		iw.compress(null, iw.getWidth() / 2, null);
	}
}
