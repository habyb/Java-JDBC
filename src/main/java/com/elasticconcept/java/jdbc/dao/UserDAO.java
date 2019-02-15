package com.elasticconcept.java.jdbc.dao;

import java.sql.Connection;

import com.elasticconcept.java.jdbc.connection.SingleConnection;

public class UserDAO {


	public UserDAO() {
		
		Connection connection = SingleConnection.getConnection();
	}
	
}
