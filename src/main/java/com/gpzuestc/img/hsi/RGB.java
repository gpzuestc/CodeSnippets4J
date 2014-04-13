package com.gpzuestc.img.hsi;

import java.awt.image.BufferedImage;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-23
 * @todo: 
 * 
 */
public class RGB {

	private int r;
	private int g;
	private int b;
	private String name;
	
	public RGB(){}
	
	public RGB(int r, int g, int b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public RGB(String name, int r, int g, int b){
		this.name = name;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public static RGB getRGB(BufferedImage bufferedImage, int x, int y){
		RGB rgb = new RGB();
		
		int getRgb = bufferedImage.getRGB(x, y);
		rgb.setR((getRgb & 0xff0000) >> 16);
		rgb.setG((getRgb & 0xff00) >> 8);
		rgb.setB(getRgb & 0xff);
		
		return rgb;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
