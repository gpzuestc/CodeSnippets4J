package com.gpzuestc.geo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * 来自：http://www.sunjs.com 老孙个人博客
 * 更多精彩请继续关注老孙个人博客
 * @author Kevon.sun
 * 2013-10-25 14:15:16
 */
public class LonLat {
	
	public static Integer size = 1000000;// 一共插入条数
	public int num = 0;
	public static int comSize = 50000;// 批处理条数
	Geohash geo = new Geohash();
	public static Connection conn = null;
	public static PreparedStatement ps = null;
	
	public static void main(String[] args) {
		LonLat main = new LonLat();
		Long start = System.currentTimeMillis();
		main.autoLoading();
		System.out.println("插入:"+size+"条数据， 一共需要："+(System.currentTimeMillis()-start));
		main.free();
	}
	
	public String getRandomNum() {
		String a = "116."+(int)(Math.random()*1000000);
		double lon = Double.valueOf(a);
		String b = "39."+(int)(Math.random()*1000000);
		double lat = Double.valueOf(b);
		String geohash = geo.encode(lat, lon);
		return lon+","+lat+","+geohash;
	}
	
	public static void main2(String[] args) {
		Geohash geo = new Geohash();
		String geohash = geo.encode(39.92324, 116.3906);
		System.out.println(geohash);
	}

	public void autoLoading(){
		JDBCUtil ju = new JDBCUtil("mysql");
		conn = ju.conn;
		List<String> list = new ArrayList<String>();
		Long start = System.currentTimeMillis();
		Long time1 = System.currentTimeMillis();
		Long time2 = 0L;
		for (int i = 0; i < size; i++) {
			String lonlat = getRandomNum();
			list.add(lonlat);
			if (list != null && list.size() % comSize == 0) {
				time2 = System.currentTimeMillis();
				System.out.println("开始装载list， 此时list大小为："+(list.size())+" -- 此次共耗时为："+(time2-time1));
				time1=time2;
				batchSave(conn, list);
				list.clear();
				list = new ArrayList<String>();
			}
		}
		free();
		System.out.println("执行完毕一共耗时："+(System.currentTimeMillis()-start));
	}
	
	/**批量插入数据库
	 * @param conn
	 * @param list
	 * @param roleid
	 * @return
	 */
	public Integer batchSave(Connection conn, List<String> list) {
		Integer ret = 0;
		String sql = "insert into address(longitude, latitude, geohash) values(?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for (int i = 0; i < list.size(); i++) {
				String lon = list.get(i).split(",")[0];
				String lat = list.get(i).split(",")[1];
				String geohash = list.get(i).split(",")[2];
				ps.setString(1, lon);
				ps.setString(2, lat);
				ps.setString(3, geohash);
				ps.addBatch();
				num++;
				if (num % comSize == 0) {
					Long start = System.currentTimeMillis();
					ps.executeBatch();
					Long end = System.currentTimeMillis();
					System.out.println("------------插入第 " + num / comSize + " 批成功,一共插入"+ num + "条数据-- 此次插入共耗时: " + (end - start));
					conn.commit();
					ps.clearBatch();
					num=0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 关闭数据库
	 */
	public void free(){
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
