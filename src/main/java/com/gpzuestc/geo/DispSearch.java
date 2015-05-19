package com.gpzuestc.geo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
/**
 * 来自：http://www.sunjs.com 老孙个人博客
 * 更多精彩请继续关注老孙个人博客
 * @author Kevon.sun
 * 2013-10-25 14:15:16
 */
public class DispSearch extends JFrame{
	ScatterPanel panel;

	public DispSearch(List<Point> geolList) {
		this.setTitle("周边地点查询");
		this.setBounds(300, 200, 500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFreeChart chart = createJFreeChart(createXYDataset(geolList));
		panel = new ScatterPanel(chart);
		this.getContentPane().add(panel);
		this.setVisible(true);
	}

	/**
	 * 将地点信息转换成XYDataset数据集合
	 * @param geoList 包含搜索结果的List
	 * @return 数据集合
	 */
	private DefaultXYDataset createXYDataset(List<Point> geoList) {
		DefaultXYDataset xyDataset = new DefaultXYDataset();
		// 添加我的位置
		xyDataset.addSeries("我的位置", new double[][] {
				{ geoList.get(0).getLatitude() },
				{ geoList.get(0).getLongitude() } });
		int size = geoList.size();
		double[][] data = new double[2][size-1];
		for (int i = 0; i < size-1; i++) {
			data[0][i] = geoList.get(i+1).getLatitude();
			data[1][i] = geoList.get(i+1).getLongitude();
		}
		xyDataset.addSeries("附近的点", data);
		return xyDataset;
	}

	/**
	 * 根据提供的XYDataset数据集来生成散点图
	 * @param dataset 要显示的数据集合
	 * @return JFreeChart 返回JFreeChart对象
	 */
	private JFreeChart createJFreeChart(XYDataset dataset) {
		// 正常解析中文的样式
		StandardChartTheme chartTheme = new StandardChartTheme("CN");
		// 设置标题的样式
		chartTheme.setExtraLargeFont(new Font("楷体", Font.BOLD, 20));
		// 设置图例的样式
		chartTheme.setRegularFont(new Font("楷体", Font.BOLD, 12));
		// 设置轴向的样式
		chartTheme.setLargeFont(new Font("楷体", Font.BOLD, 12));
		ChartFactory.setChartTheme(chartTheme);
		// 创建图表
		JFreeChart chart = ChartFactory.createScatterPlot("搜索结果", "X", "Y",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		return chart;
	}

	/**
	 * 将图表放在专用于显示图表的ChartPanel面板中
	 * 
	 * @作者 lyl
	 * @创建日期 2013-1-8
	 * @版本 Version 1.0
	 */
	private class ScatterPanel extends JPanel {
		public ScatterPanel(JFreeChart chart) {
			this.setLayout(new BorderLayout());
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setMouseWheelEnabled(true);
			this.add(chartPanel);
		}
	}
}
