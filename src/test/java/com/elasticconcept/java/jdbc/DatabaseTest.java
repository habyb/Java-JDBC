package com.elasticconcept.java.jdbc;

import org.junit.Test;

import com.elasticconcept.java.jdbc.dao.UserDAO;
import com.elasticconcept.java.jdbc.model.User;

public class DatabaseTest {

	@Test
	public void initDatabase() {
		
		UserDAO userDAO = new UserDAO();
		User user = new User();
		
		user.setId(6L);
		user.setName("Robert");
		user.setEmail("robert@robert.com");
		
		userDAO.save(user);
	}
}
