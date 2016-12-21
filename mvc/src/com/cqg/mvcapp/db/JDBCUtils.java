package com.cqg.mvcapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBC�����Ĺ�����
 * @author lenovo
 *
 */
public class JDBCUtils {
	/**
	 * �ر����ݿ�����
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
	 * ��ȡMysql���ݿ������
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
