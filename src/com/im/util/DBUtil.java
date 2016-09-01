package com.im.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/imooc?characterEncoding=utf8";
	private static final String USER = "root";
	private static final String PASSWORD = "guoyang121";
	
	private Connection connection = null;
	
	public DBUtil() {
		try {
			//加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//获得数据库的连接
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		
		return connection;
		
	}
	
	
	public void close(PreparedStatement preparedStatement,ResultSet resultSet,Connection connection) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
