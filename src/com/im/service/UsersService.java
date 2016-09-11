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
	
	//��֤�û��Ƿ�Ϸ�
	public boolean checkUser(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		
		//������ݿ�����
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from ofuser where username = ? and plainPassword = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			//ȡ�ý��
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
	
	//ͨ��id���һ���û���Ϣ
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
	
	
	//ͨ���û������һ���û���Ϣ
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
	
	//���ط�ҳ���
	public ArrayList<User> getUsersByPage(int pageNow,int pageSize) {
		ArrayList<User> arrayList = new ArrayList<User>();
		
		//��ҳ��ѯ
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from users limit " + (pageNow-1)*pageSize + "," + pageSize;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			//�������װ��arraylist��
			while (resultSet.next()) {
				User user = new User();//�����ѭ������
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
	
	
	//��ȡpageCount
	public int getPageCount(int pageSize) {
		//���ҳ��
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
	
	//�����û�
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
	
	//���������û�
	public ArrayList<User> getAllUsers(){
		ArrayList<User> allUsers = new ArrayList<User>();
		
		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from ofuser;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet  = preparedStatement.executeQuery();
			
			//�������װ��arraylist��
			while (resultSet.next()) {
				User user = new User();//�����ѭ������
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
