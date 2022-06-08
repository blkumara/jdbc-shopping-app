package com.ty.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ty.shopping.dto.User;
import com.ty.shopping.util.AES;
import com.ty.shopping.util.ConnectionObject;

public class UserDao {
	
	
	public User validateUser(String email,String password )
	{
		String sql="Select *from user where email=? and password=?";
		Connection connection= ConnectionObject.getConnection();
		
		String enc=AES.encrypt(password ,secret);
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, enc);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				User user=new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setMobile(resultSet.getLong(5));
				return user;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		
	}
	
	
	private String secret="varun";
	public int saveUser(User user)
	{
		String sql="insert into user values(?,?,?,?,?)";
		
		Connection connection=ConnectionObject.getConnection();
		
		PreparedStatement preparedStatement;
		try {
			
			String enc=AES.encrypt(user.getPassword() ,secret);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, enc);
			preparedStatement.setLong(5, user.getMobile());
			
			return preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection !=null)
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
			
	}
	
		public void updateUser(User user)
		{
			Connection connection=ConnectionObject.getConnection();
			String sql="Update user Set name=?,email=?,password=?,mobile=? where id=?";
			String enc=AES.encrypt(user.getPassword() ,secret);
			try {
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, user.getName());
				preparedStatement.setString(2, user.getEmail());
				preparedStatement.setString(3, enc);
				preparedStatement.setLong(4, user.getMobile());
				preparedStatement.setInt(5, user.getId());
				
				preparedStatement.execute();
				System.out.println("Data updated");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public void deleteUserById(int id)
		{
			Connection connection=ConnectionObject.getConnection();
			
			String sql="delete  from user where id=?";
			
			try {
				PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
				System.out.println("Data Deleted");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
}
