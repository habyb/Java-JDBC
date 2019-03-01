package com.elasticconcept.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elasticconcept.java.jdbc.connection.SingleConnection;
import com.elasticconcept.java.jdbc.model.BeanUserPhone;
import com.elasticconcept.java.jdbc.model.Phone;
import com.elasticconcept.java.jdbc.model.User;

public class UserDAO {

	private Connection connection;

	public UserDAO() {
		
		connection = SingleConnection.getConnection();
	}
	
	public void save(User user) {
		
		String sql = "INSERT INTO user_jdbc (name, email) VALUES (?, ?)";
		
		try {
			PreparedStatement insert = connection.prepareStatement(sql);
			
			insert.setString(1, user.getName());
			insert.setString(2, user.getEmail());
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
	
	public void savePhone(Phone phone) {
	
		try {
			
			String sql = "INSERT INTO public.user_phone (\"number\", \"type\", personuser) VALUES(?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, phone.getNumber());
			statement.setString(2, phone.getType());
			statement.setLong(3, phone.getPersonuser());
			
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
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
	
	public List<BeanUserPhone> listUserPhone(Long idUser) {
		
		List<BeanUserPhone> beanUserPhone = new ArrayList<BeanUserPhone>();
		
		String sql = "select name, number, email from user_phone as up\n" + 
				"	inner join user_jdbc as u\n" + 
				"	on up.personuser = u.id\n" + 
				"	where u.id = " + idUser;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				BeanUserPhone userPhone = new BeanUserPhone();
				
				userPhone.setName(resultSet.getString("name"));
				userPhone.setNumber(resultSet.getString("number"));
				userPhone.setEmail(resultSet.getString("email"));
				
				beanUserPhone.add(userPhone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return beanUserPhone;
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
	
	public void delete(Long id) {
		
		try {
			
			String sql = "DELETE FROM user_jdbc WHERE id = " + id;
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.execute();
			
			connection.commit();
		} catch (Exception e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deletePhonesByUser(Long idUser) {
		
		String sqlPhone = "DELETE FROM user_phone WHERE personuser = " + idUser;
		
		String sqlUser = "DELETE FROM user_jdbc WHERE id = " + idUser;
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sqlPhone);
			prepareStatement.executeUpdate();
			connection.commit();
			
			prepareStatement = connection.prepareStatement(sqlUser);
			prepareStatement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
