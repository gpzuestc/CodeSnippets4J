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
public class HueRecognizer {
	
	private TreeMap<Hue, Integer> hueMap = new TreeMap<Hue, Integer>();
	private List<Hue> ignores;
	
	/**
	 * 计算当前rgb的色调
	 * @param rgb
	 * @return
	 */
	public Hue computerHue(RGB rgb){

		int cr = rgb.getR();
		int cg = rgb.getG();
		int cb = rgb.getB();
		
		//深色调(近似黑)
		int darkLimit = 40;
		if(cr <= darkLimit && cg <= darkLimit && cb <= darkLimit){
			return Hue.DARK;
		}
		
		//灰色调(深灰，浅灰)
		int grayLimt = 15;
		if(Math.abs(cr - cg) <= grayLimt && Math.abs(cr - cb) <= grayLimt && Math.abs(cb - cg) <= grayLimt){
			int average = (cr + cg + cb) / 3;
			if(average > 230){
				return Hue.WHITE;
			}else if(average > 160){
				return Hue.SILVER;
			}else if(average > 80){
				return Hue.GRAY;
			}else{
				return Hue.DARK;
			}
		}
	
		
		HSI hsi = HSI.RGB2HSI(rgb);
		Double ch  = hsi.getHue();
		Double cs = hsi.getSaturation();
		Double ci = hsi.getIntensity();
		
		//根据饱和度和亮度判断dark
		if(cs <= 0.3 && ci <= 0.2){
			return Hue.DARK;
		}
		
		if(ch >= 0 && ch < 5.0/24){ //[0, pi*5/12)
			if(ci <= 1.0/2 && cs <= 1.0/3){
				return Hue.BROWN;
			}
			if(ch < 1.0 /24){
				return Hue.RED;
			}else if(ch < 1.0/8){
				return Hue.ORANGE;
			}else if(ch < 0.18){ // 5.0/24
				return Hue.YELLOW;
			}else{
				return Hue.GREEN;
			}
		}else if(ch < 11.0/24){
			return Hue.GREEN;
		}else if(ch < 13.0/24){
			return Hue.CYAN;
		}else if(ch < 17.0/24){
			if(ci <= 0.1){
				return Hue.DARK;
			}
			return Hue.BLUE;
		}else if(ch < 7.0/8){
			return Hue.PURPLE;
		}else if(ch < 23.0/24){
			return Hue.PINK;
		}else{
			return Hue.RED;
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
	public Hue computerHue(BufferedImage bufferedImage, int lines, int columns, int index){
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
		
		return computerHue(bufferedImage);
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
	public Hue computerHue(BufferedImage bufferedImage, int x, int y, int width, int height){
		bufferedImage = bufferedImage.getSubimage(x, y, width, height);
		return computerHue(bufferedImage);
	}
	
	/**
	 * 计算bufferedImage的主色调
	 * @param bufferedImage
	 * @return
	 */
	public Hue computerHue(BufferedImage bufferedImage){
		hueMap.put(Hue.RED, 0);
		hueMap.put(Hue.ORANGE, 0);
		hueMap.put(Hue.YELLOW, 0);
		hueMap.put(Hue.GREEN, 0);
		hueMap.put(Hue.CYAN, 0);
		hueMap.put(Hue.BLUE, 0);
		hueMap.put(Hue.PURPLE, 0);
		hueMap.put(Hue.PINK, 0);
		hueMap.put(Hue.BROWN, 0);
		hueMap.put(Hue.WHITE, 0);
		hueMap.put(Hue.SILVER, 0);
		hueMap.put(Hue.GRAY, 0);
		hueMap.put(Hue.DARK, 0);
		
		for(int i = 0; i < bufferedImage.getWidth(); i++){
			for(int j = 0; j < bufferedImage.getHeight(); j++){
				RGB currentRgb = RGB.getRGB(bufferedImage, i, j);
				Hue hue = computerHue(currentRgb);
				hueMap.put(hue, hueMap.get(hue) + 1);
			}
		}
		
		if(ignores != null){
			for(Hue hue : ignores){
				hueMap.remove(hue);
			}
		}
		
		List<Map.Entry<Hue, Integer>> entryList = new ArrayList<Map.Entry<Hue, Integer>>(hueMap.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<Hue, Integer>>() {
			public int compare(Map.Entry<Hue, Integer> o1, Map.Entry<Hue, Integer> o2) {
				return (o2.getValue() - o1.getValue()); //倒序
			}
		});
		int total = 0;
		for(Map.Entry<Hue, Integer> entry : entryList){
			System.out.println(entry.getKey().getName() + ":" + entry.getValue() + "; ratio=" + entry.getValue() * 100.0 / (bufferedImage.getWidth() * bufferedImage.getHeight()) + "%");
			total += entry.getValue();
		}
		System.out.println("total=" + total + "; realTotal=" + bufferedImage.getHeight() * bufferedImage.getWidth());
		return entryList.get(0).getKey();
	}
	
	
	public List<Hue> getIgnores() {
		return ignores;
	}

	public void setIgnores(List<Hue> ignores) {
		this.ignores = ignores;
	}
}
