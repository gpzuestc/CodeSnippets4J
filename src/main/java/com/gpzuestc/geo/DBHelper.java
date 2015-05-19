package com.gpzuestc.geo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 来自：http://www.sunjs.com 老孙个人博客
 * 更多精彩请继续关注老孙个人博客
 * @author Kevon.sun
 * 2013-10-25 14:15:16
 */
public class DBHelper {
	/** 设置数据库连接用户名 */
	private String userName = "root";
	/** 设置数据库连接密码 */
	private String pwd = "root";
	/** 设置数据库 */
	private String database = "d_website_0620";
	/** 设置JDBC驱动程序 */
	private String dbDriver = "com.mysql.jdbc.Driver";
	/** 设置数据库URL */
	private String dbConnect = "jdbc:mysql://192.168.2.222:3306/" + database
			+ "?useUnicode=true&characterEncoding=UTF-8";
	private Connection conn = null;
	private Statement stmt = null;
	ResultSet rs = null;

	// 数据库驱动程序注册
	public DBHelper() {
		try {
			Class.forName(dbDriver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("jdbcDriver Error!");
		}
	}

	/** 建立数据库连接及定义数据查询 */
	public ResultSet executeQuery(String sql) throws SQLException {
		rs = null;
		try {
			conn = DriverManager.getConnection(dbConnect, userName, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			throw new SQLException("executeQuery wrong!!!");
		}
		return rs;
	}

	/** 建立数据库连接及定义数据操作 */
	public void executeUpdate(String sql) throws SQLException {
		stmt = null;
		try {
			conn = DriverManager.getConnection(dbConnect, userName, pwd);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			throw new SQLException("executeUpdate wrong!!!");
		} finally {
			stmt.close();
			conn.close();
		}
	}

	/** 关闭陈述语句 */
	public void closeStmt() {
		try {
			stmt.close();
		} catch (SQLException e) {
			System.err.println("closeStmt Error!");
		}
	}

	/** 关闭连接 */
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("closeConn Error!");
		}
	}

	/** 字符串进行转码 */
	public String toGb(String str) {
		String finallStr = null;
		try {
			finallStr = new String(str.getBytes("ISO8859-1"), "GB2312");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return finallStr;
	}
	
	public static void main(String[] args){
		DBHelper dbHelper = new DBHelper();
		String sql = "select id, lat, lng from t_shop_shops where stu=1 and lat is not null and lng is not null and geohash is null";
		Geohash geo = new Geohash();
		try {
			ResultSet rs = dbHelper.executeQuery(sql);
			while (rs.next()) {
//				double lati = rs.getDouble("lat");
//				double longi = rs.getDouble("lng");
				String a = rs.getString("lat");
				String b = rs.getString("lng");
				if(a!=null && !"".equals(a) && b!=null && !"".equals(b)){
					double lati = Double.valueOf(a);
					double longi = Double.valueOf(b);
					// 根据经纬度得到Geohash值
					String geohash = geo.encode(lati, longi);
					// 将数据库中地点的经纬度转换成geohash值进行存储
					String updatesql = "update t_shop_shops set geohash='"+geohash+"' where id="+rs.getLong("id");
					System.out.println(updatesql);
					dbHelper.executeUpdate(updatesql);
				}else{
					System.out.println("为空=================================");
				}
			}
			dbHelper.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
