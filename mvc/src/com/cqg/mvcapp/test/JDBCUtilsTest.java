package com.cqg.mvcapp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.cqg.mvcapp.db.JDBCUtils;

public class JDBCUtilsTest extends JDBCUtils {

	@Test
	public void testGetConnection() throws SQLException {
		Connection connection=JDBCUtils.getConnection();
		System.out.println(connection);
	}

}
