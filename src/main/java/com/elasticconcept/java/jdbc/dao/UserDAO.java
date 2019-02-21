package com.elasticconcept.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
