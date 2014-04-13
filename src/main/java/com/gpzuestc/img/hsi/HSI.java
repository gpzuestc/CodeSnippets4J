package com.gpzuestc.img.hsi;


/**
 * @author gpzuestc
 * @mail: guopengzhang@sohu-inc.com
 * @date: 2012-12-22
 * @todo: 
 * 
 */
public class HSI {
    private Double Hue;
    private Double Saturation;
    private Double Intensity;
    
    public HSI(){}
    
    public HSI(Double hue, Double saturation, Double intensity)
    {
        this.Hue = hue;
        this.Saturation = saturation;
        this.Intensity = intensity;
    }
    
    public static HSI RGB2HSI(RGB rgb)
    {
        HSI hsi = new HSI();

        double r = (rgb.getR() / 255.0);
        double g = (rgb.getG() / 255.0);
        double b = (rgb.getB() / 255.0);

        double theta = Math.acos(0.5 * ((r - g) + (r - b)) / Math.sqrt((r - g) * (r - g) + (r - b) * (g - b))) / (2 * Math.PI);

        hsi.setHue((b <= g) ? theta : (1 - theta));

        hsi.setSaturation(1 - 3 * Math.min(Math.min(r, g), b) / (r + g + b));

        hsi.setIntensity((r + g + b) / 3);

        return hsi;
    }
    
    public static RGB HSI2RGB(HSI hsi)
    {
        double r, g, b;

        double h = hsi.getHue();
        double s = hsi.getSaturation();
        double i = hsi.getIntensity();

        h = h * 2 * Math.PI;

        if (h >= 0 && h < 2 * Math.PI / 3)
        {
            b = i * (1 - s);
            r = i * (1 + s * Math.cos(h) / Math.cos(Math.PI / 3 - h));
            g = 3 * i - (r + b);
        }
        else if (h >= 2 * Math.PI / 3 && h < 4 * Math.PI / 3)
        {
            r = i * (1 - s);
            g = i * (1 + s * Math.cos(h - 2 * Math.PI / 3) / Math.cos(Math.PI - h));
            b = 3 * i - (r + g);
        }
        else //if (h >= 4 * Math.PI / 3 && h <= 2 * Math.PI)
        {
            g = i * (1 - s);
            b = i * (1 + s * Math.cos(h - 4 * Math.PI / 3) / Math.cos(5 * Math.PI / 3 - h));
            r = 3 * i - (g + b);
        }

        return new RGB((int)(r * 255.0 + .5), (int)(g * 255.0 + .5), (int)(b * 255.0 + .5));
    }

	public Double getHue() {
		return Hue;
	}

	public void setHue(Double hue) {
		Hue = hue;
	}

	public Double getSaturation() {
		return Saturation;
	}

	public void setSaturation(Double saturation) {
		Saturation = saturation;
	}

	public Double getIntensity() {
		return Intensity;
	}

	public void setIntensity(Double intensity) {
		Intensity = intensity;
	}

}
