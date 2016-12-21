package com.cqg.mvcapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC操作的工具类
 * @author lenovo
 *
 */
public class JDBCUtils {
	/**
	 * 关闭数据库连接
	 * @param connection
	 */
	public static void close(Connection connection){
		try {
			if(connection!=null){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取Mysql数据库的连接
	 * @return
	 */
	
	private static DataSource dataSource=null;
	 
	static{
		dataSource=new ComboPooledDataSource("mvcapp");
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
