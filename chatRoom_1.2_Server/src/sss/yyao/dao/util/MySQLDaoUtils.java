package sss.yyao.dao.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MySQLDaoUtils {
	private static String driver;
	private static String url;
	private static String userName;
	private static String password;
	static {
		try {
			Properties pro = new Properties();
			pro.load(OracleDaoUtils.class.getClassLoader().getResourceAsStream(
					"sss/yyao/resource/mysql.properties"));
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			userName = pro.getProperty("userName");
			password = pro.getProperty("password");
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con, Statement stat, ResultSet rs){
		close(rs);
		close(stat);
		close(con);
	}
	public static void close(Connection con, Statement stat){
		close(stat);
		close(con);
	}

	public static void close(Connection con) {
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement stat) {
		if(stat!=null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
