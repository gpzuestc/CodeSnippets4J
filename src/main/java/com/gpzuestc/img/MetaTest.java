package com.gpzuestc.img;

import java.io.File;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class MetaTest {
	public static void main(String[] args) throws Exception {
		
//		File jpegFile = new File("/Users/gpzuestc/快盘/Private/IMG_0271.JPG");
		File jpegFile = new File("/Users/gpzuestc/快盘/[Pictures]/来自 GPZIP/相机胶卷/IMG_0058_x.jpg");
//		File jpegFile = new File("/Users/gpzuestc/快盘/Images/Photos/foo/1dda11a1-b5a8-43d5-a071-f7abfe527fd8.jpeg");
		Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
		
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		        System.out.println(tag);
		    }
		}
		System.out.println(metadata.getFirstDirectoryOfType(ExifIFD0Directory.class).getInteger(ExifIFD0Directory.TAG_ORIENTATION));
		
//		Iterable<Directory> list = metadata.getDirectories();
//		int i = 0;
//		for(Directory directory : list){
//			i++;
//			System.out.println(i);
//			System.out.println(directory.getName());
//			 System.out.println("相机品牌：" + directory.getString(ExifSubIFDDirectory.TAG_MAKE));   
//
//		     System.out.println("相机型号：" + directory.getString(ExifSubIFDDirectory.TAG_MODEL));   
//
//		     System.out.println("光 圈 值：" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER));   
//
//		     System.out.println("快    門：" + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME));   
//
//		     System.out.println("感 光 度：" + directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT));
//
//		     System.out.println("软    件：" + directory.getString(ExifSubIFDDirectory.TAG_SOFTWARE)); 
//
//		     System.out.println("原始拍摄时间：" + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
//
//		     System.out.println("数字化时间：" + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED));
//
//		     System.out.println("方    向：" + directory.getString(ExifSubIFDDirectory.TAG_ORIENTATION));
//
//		     System.out.println("图片宽度：" + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH));
//
//		     System.out.println("图片高度：" + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT));
//
//		     System.out.println("水平分辨率：" + directory.getString(ExifSubIFDDirectory.TAG_X_RESOLUTION));
//
//		     System.out.println("垂直分辨率：" + directory.getString(ExifSubIFDDirectory.TAG_Y_RESOLUTION));
//
//		     System.out.println("垂直分辨率：" + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_BIAS));
//		}
//		System.out.println(i);
	}
}
