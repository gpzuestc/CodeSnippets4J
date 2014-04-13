package com.gpzuestc.img.hsi;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-21
 * @todo: 
 * 
 */
public class BrightnessRecognizer {
	
	private TreeMap<Brightness, Integer> brightnessMap = new TreeMap<Brightness, Integer>();
	
	/**
	 * 计算当前rgb的亮度
	 * @param rgb
	 * @return
	 */
	public Brightness computerBrightness(RGB rgb){

		
		HSI hsi = HSI.RGB2HSI(rgb);
//		Double ch  = hsi.getHue();
//		Double cs = hsi.getSaturation();
		Double ci = hsi.getIntensity();
		
		if(ci < 0.5){
			return Brightness.DARK;
		}else{
			return Brightness.LIGHT;
		}
		
	}
	
	/**
	 * 计算bufferedImage的区块主色调
	 * @param bufferedImage
	 * @param lines 图片划分为lines行
	 * @param columns 图片划分为columns列
	 * @param index 区块的索引号，从左上角横向开始数，以1开始记
	 * @return
	 */
	public Brightness computerBrightness(BufferedImage bufferedImage, int lines, int columns, int index){
		if(lines < 1 || columns < 1 || index < 1 || index > lines * columns){
			throw new IllegalArgumentException("lines, columns or index of splited image must be positive integers, " +
					"and index <= lines*columns;lines=" + lines + ";columns=" + columns + ";index=" + index);
		}
		
		int width = bufferedImage.getWidth() / columns;
		int height = bufferedImage.getHeight() / lines;
		
		if(columns == 1 && lines > 1){
			bufferedImage = bufferedImage.getSubimage(0, 0 + (index - 1) % lines * height, width, height);
		}else if(lines == 1 && columns > 1){
			bufferedImage = bufferedImage.getSubimage(0 + (index - 1) % columns * width, 0, width, height);
		}else if(lines > 1 && columns > 1){
			bufferedImage = bufferedImage.getSubimage(0 + (index - 1) % columns * width, 0 + (index - 1) / columns * height, width, height);
		}
		
		return computerBrightness(bufferedImage);
	}
	
	/**
	 * 计算bufferedImage的自定义区块的色调
	 * @param bufferedImage 
	 * @param x 指定区域的左上角x坐标
	 * @param y 指定区域的左上角y坐标
	 * @param width 自定义区块的宽
	 * @param height 自定义区块的高
	 * @return
	 */
	public Brightness computerHue(BufferedImage bufferedImage, int x, int y, int width, int height){
		bufferedImage = bufferedImage.getSubimage(x, y, width, height);
		return computerBrightness(bufferedImage);
	}
	
	/**
	 * 计算bufferedImage的主色调
	 * @param bufferedImage
	 * @return
	 */
	public Brightness computerBrightness(BufferedImage bufferedImage){
		brightnessMap.put(Brightness.DARK, 0);
		brightnessMap.put(Brightness.LIGHT, 0);
		
		for(int i = 0; i < bufferedImage.getWidth(); i++){
			for(int j = 0; j < bufferedImage.getHeight(); j++){
				RGB currentRgb = RGB.getRGB(bufferedImage, i, j);
				Brightness brightness = computerBrightness(currentRgb);
				brightnessMap.put(brightness, brightnessMap.get(brightness) + 1);
			}
		}
		
		List<Map.Entry<Brightness, Integer>> entryList = new ArrayList<Map.Entry<Brightness, Integer>>(brightnessMap.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<Brightness, Integer>>() {
			public int compare(Map.Entry<Brightness, Integer> o1, Map.Entry<Brightness, Integer> o2) {
				return (o2.getValue() - o1.getValue()); //倒序
			}
		});
		int total = 0;
		for(Map.Entry<Brightness, Integer> entry : entryList){
			System.out.println(entry.getKey().getName() + ":" + entry.getValue() + "; ratio=" + entry.getValue() * 100.0 / (bufferedImage.getWidth() * bufferedImage.getHeight()) + "%");
			total += entry.getValue();
		}
		System.out.println("total=" + total + "; realTotal=" + bufferedImage.getHeight() * bufferedImage.getWidth());
		return entryList.get(0).getKey();
	}
	
}
