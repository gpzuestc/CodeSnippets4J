package com.gpzuestc.geo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 来自：http://www.sunjs.com 老孙个人博客
 * 更多精彩请继续关注老孙个人博客
 * @author Kevon.sun
 * 2013-10-25 14:15:16
 */
public class JDBCUtil {

	public Connection conn = null;

	public JDBCUtil(String db) {
		if("mysql".equalsIgnoreCase(db)){
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
			}

			try {
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/bigsun?autoreconnect=true",
						"root", "root");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if("oracle".equalsIgnoreCase(db)){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
			}

			try {
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:orcl",
						"bigsun", "oracle");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
