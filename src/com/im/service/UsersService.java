package com.im.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.im.model.User;
import com.im.util.DBUtil;


public class UsersService {
	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	//验证用户是否合法
	public boolean checkUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		
		//获得数据库连接
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from ofuser where username = ? and plainPassword = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			//取得结果
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(preparedStatement, resultSet, connection);
		}
		
		return false;
	}
	
	public boolean addUser(User user) {
		
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "insert into users (username,email,grade,password) values (?,?,?,?)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setInt(3, user.getGrade());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	//通过id获得一个用户信息
	public User getUserById(String id) {
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		User user = new User();
		
		String sql = "select * from users where id = ?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setGrade(resultSet.getInt(4));
				user.setPassword(resultSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("queryName:" + user.getUsername());
		return user;
	}
	
	
	//通过用户名获得一个用户信息
		public User getUserByName(String name) {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			User user = new User();
			
			String sql = "select * from users where username = ?;";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, name);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					user.setId(resultSet.getInt(1));
					user.setUsername(resultSet.getString(2));
					user.setEmail(resultSet.getString(3));
					user.setGrade(resultSet.getInt(4));
					user.setPassword(resultSet.getString(5));
					return user;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
	
	
	public boolean deleteUser(String id) {
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "delete from users where id = ?";
		System.out.println("Delete id :" + id);
		//int temp = Integer.parseInt(id);
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//返回分页结果
	public ArrayList<User> getUsersByPage(int pageNow,int pageSize) {
		ArrayList<User> arrayList = new ArrayList<User>();
		
		//分页查询
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from users limit " + (pageNow-1)*pageSize + "," + pageSize;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			//将结果封装到arraylist中
			while (resultSet.next()) {
				User user = new User();//必须放循环里面
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setGrade(resultSet.getInt(4));
				arrayList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(preparedStatement, resultSet, connection);
		}
		
		return arrayList;
	}
	
	
	//获取pageCount
	public int getPageCount(int pageSize) {
		//算出页数
		int rowCount = 0;
		DBUtil dbUtil = new DBUtil();
		try {
			connection = dbUtil.getConnection();
			preparedStatement = connection.prepareStatement("select count(*) from users");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			rowCount = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtil.close(preparedStatement, resultSet, connection);
		}
		
		return (rowCount - 1) / pageSize + 1;
	}
	
	//更新用户
	public boolean updateUser(User user) {
		
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "update users set username = ?,email = ?,grade = ?,password = ? where id = ?;";
		System.out.println("userService :" + user.getId());
		System.out.println("userService :" + user.getUsername());
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setInt(3, user.getGrade());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getId());
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbUtil.close(preparedStatement, resultSet, connection);
		}
		
		return true;
	}
	
	//返回所有用户
	public ArrayList<User> getAllUsers(){
		ArrayList<User> allUsers = new ArrayList<User>();
		
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from ofuser;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet  = preparedStatement.executeQuery();
			
			//将结果封装到arraylist中
			while (resultSet.next()) {
				User user = new User();//必须放循环里面
				user.setUsername(resultSet.getString(1));
				user.setEmail(resultSet.getString(9));
				allUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return allUsers;
	}
}
