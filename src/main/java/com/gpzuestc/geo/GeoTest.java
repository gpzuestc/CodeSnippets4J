package com.gpzuestc.geo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 来自：http://www.sunjs.com 老孙个人博客
 * 更多精彩请继续关注老孙个人博客
 * @author Kevon.sun
 * 2013-10-25 14:15:16
 */
public class GeoTest {

	private static final double EARTH_RADIUS = 6378.137;
	
	private static List<Point> nearPoints1 = new ArrayList<Point>();
	private static List<Point> nearPoints2 = new ArrayList<Point>();

	/**
	 * 根据经纬度计算两点之间的距离
	 * 
	 * @param lat1 第一个点的纬度值
	 * @param lng1 第一个点的经度值
	 * @param lat2 第二个点的纬度值
	 * @param lng2 第二个点的经度值
	 * @return
	 */
	public static double getPointDistance(double lat1, double lng1,
			double lat2, double lng2) {
		double result = 0;
		
		// 两点的纬度弧度值
		double radLat1 = radian(lat1);
		double ratlat2 = radian(lat2);
		// 两点的经纬度的弧度之差
		double a = radian(lat1) - radian(lat2);
		double b = radian(lng1) - radian(lng2);
		
		// 计算距离
		result = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(ratlat2)
				* Math.pow(Math.sin(b / 2), 2)));
		result = result * EARTH_RADIUS;

		result = Math.round(result * 1000); // 返回的单位是米，四舍五入

		return result;
	}

	/**
	 * 将角度转换为弧度
	 * @param d
	 *         待转换的角度
	 * @return 转换后得到的弧度值
	 */
	private static double radian(double d) {
		return (d * Math.PI) / 180.00;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime, endTime;
		// 当前位置
		Point myPoint = new Point();
		System.out.println("当前位置是：("+myPoint.getLatitude()+","+myPoint.getLongitude()+")");
		DBHelper dbHelper = new DBHelper();
		startTime = System.currentTimeMillis();
		String sql = "select id, latitude, longitude from address";
		/**
		 * 方案1：直接遍历所有地点的经纬度进行筛选
		 */
//		try {
//			ResultSet rs = dbHelper.executeQuery(sql);
//			while (rs.next()) {
//				double lati = rs.getDouble("latitude");
//				double longi = rs.getDouble("longitude");
//				// 根据经纬度直接计算距离
//				double distance = GeoTest.getPointDistance(myPoint.getLatitude(), myPoint.getLongitude(), lati, longi);
//				if(distance <= 1000){
//					nearPoints1.add(new Point(lati, longi));
//				}
//			}
//			dbHelper.closeConn();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		endTime = System.currentTimeMillis();
//        System.out.println("使用方案1共找到"+nearPoints1.size()+"个周边地点。");
//        System.out.println("方案1所耗时间："+(endTime-startTime)/1000.0+"s");
        
        /**
		 * 方案2：利用Geohash前缀匹配
		 */
        Geohash geohash = new Geohash();
        String myGeohash = geohash.encode(myPoint.getLatitude(), myPoint.getLongitude());
        String prefix = myGeohash.substring(0, 5);
		startTime = System.currentTimeMillis();
        sql = "select id, latitude, longitude from address where geohash like '"+prefix+"%' limit 10";
//        sql = "select id, latitude, longitude from address where geohash like '"+prefix+"%'";
        try {
			ResultSet rs = dbHelper.executeQuery(sql);
			while (rs.next()) {
				double lati = rs.getDouble("latitude");
				double longi = rs.getDouble("longitude");
				nearPoints2.add(new Point(lati, longi));
			}
			//System.out.println(nearPoints2.size());
			//new DispSearch(nearPoints2);
			dbHelper.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
        System.out.println("使用方案2共找到"+nearPoints2.size()+"个周边地点。");
        System.out.println("方案2所耗时间："+(endTime-startTime)/1000.0+"s");
        
        /**
		 * 方案3：sql语句进行查询
		 */
//        startTime = System.currentTimeMillis();
//		sql = "select * from address where "
//				+ "sqrt((((?-lng )*PI()*12656*cos(((?+lat)/2)*PI()/180)/180)"
//				+ "*((?-lng )*PI()*12656*cos (((?+lat )/2)*PI()/180)/180))+"
//				+ "(((?-lat)*PI()*12656/180)*((?-lat )*PI()*12656/180))) limit ?,?";
//		try {
//			ResultSet rs = dbHelper.executeQuery(sql);
//			ps.setString(2, lng);
//			ps.setString(3, lat);
//			ps.setString(4, lng);
//			ps.setString(5, lat);
//			ps.setString(6, lat);
//			ps.setString(7, lat);
//			ps.setInt(8, beginSize);
//			ps.setInt(9, size);
//			while (rs.next()) {
//				double lati = rs.getDouble("latitude");
//				double longi = rs.getDouble("longitude");
//				// 根据经纬度直接计算距离
//				double distance = GeoTest.getPointDistance(myPoint.getLatitude(), myPoint.getLongitude(), lati, longi);
//				if(distance <= 1000){
//					nearPoints1.add(new Point(lati, longi));
//				}
//			}
//			dbHelper.closeConn();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		endTime = System.currentTimeMillis();
//        System.out.println("使用方案1共找到"+nearPoints1.size()+"个周边地点。");
//        System.out.println("方案1所耗时间："+(endTime-startTime)/1000.0+"s");
	}

}
