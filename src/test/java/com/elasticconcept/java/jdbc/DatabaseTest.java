package com.elasticconcept.java.jdbc;

import org.junit.Test;

import com.elasticconcept.java.jdbc.dao.UserDAO;
import com.elasticconcept.java.jdbc.model.User;

public class DatabaseTest {

	@Test
	public void initDatabase() {
		
		UserDAO userDAO = new UserDAO();
		User user = new User();
		
		user.setId(5L);
		user.setName("Joseph");
		user.setEmail("joseph@joseph.com");
		
		userDAO.save(user);
	}
}
