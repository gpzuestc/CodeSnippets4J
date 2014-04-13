package com.gpzuestc.img.hsi;

import java.io.Serializable;

/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-21
 * @todo: 
 * 
 */
public class Hue implements Serializable, Comparable<Hue>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6594377946975123548L;
	
	public static final Hue RED = new Hue("red", new RGB(224, 47, 30));
	public static final Hue ORANGE = new Hue("orange", new RGB(255, 112, 0));
	public static final Hue YELLOW = new Hue("yellow", new RGB(255, 190, 0));
	public static final Hue GREEN = new Hue("green", new RGB(86, 164, 36));
	public static final Hue CYAN = new Hue("cyan", new RGB(0, 182, 200));
	public static final Hue BLUE = new Hue("blue", new RGB(0, 16, 254));
	public static final Hue PURPLE = new Hue("purple", new RGB(152, 53, 254));
	public static final Hue PINK = new Hue("pink", new RGB(217, 118, 179));
	public static final Hue BROWN = new Hue("brown", new RGB(116, 53, 18));
	public static final Hue GRAY = new Hue("gray", new RGB(100, 100, 100));
	public static final Hue SILVER = new Hue("silver", new RGB(180, 180, 180));
	public static final Hue WHITE = new Hue("white", new RGB(255, 255, 255));
	public static final Hue DARK = new Hue("dark", new RGB(0, 0, 0));
	
	private String name;
	private RGB rgb;
	
	public Hue(RGB rgb){
		this("", rgb);
	}
	
	public Hue(String name){
		this(name, null);
	}
	
	public Hue(String name, RGB rgb){
		this.name = name;
		this.rgb = rgb;
	}
	
	public int compareTo(Hue o) {
		return name.compareTo(o.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RGB getRgb() {
		return rgb;
	}

	public void setRgb(RGB rgb) {
		this.rgb = rgb;
	}

}
