package com.elasticconcept.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elasticconcept.java.jdbc.connection.SingleConnection;
import com.elasticconcept.java.jdbc.model.User;

public class UserDAO {

	private Connection connection;

	public UserDAO() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void save(User user) {
		
		String sql = "INSERT INTO user_jdbc (id, name, email) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setLong(1, user.getId());
			insert.setString(2, user.getName());
			insert.setString(3, user.getEmail());
			insert.execute();
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	public List<User> list() throws Exception {
		List<User> userlist = new ArrayList<User>();
		
		String sql = "SELECT * FROM user_jdbc";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultset = statement.executeQuery();
		
		while(resultset.next()) {
			User user = new User();
			
			user.setId(resultset.getLong("id"));
			user.setName(resultset.getString("name"));
			user.setEmail(resultset.getString("email"));
			
			userlist.add(user);
		}
		
		return userlist;
	}
	
	public User search(Long id) throws Exception {
		User result = new User();
		
		String sql = "SELECT * FROM user_jdbc WHERE id = " + id;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultset = statement.executeQuery();
		
		while(resultset.next()) {
			result.setId(resultset.getLong("id"));
			result.setName(resultset.getString("name"));
			result.setEmail(resultset.getString("email"));
		}
		
		return result;
	}
	
	public void update(User user) {
		
		String sql = "UPDATE user_jdbc SET name = ? WHERE id = " + user.getId();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			
			statement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
}
