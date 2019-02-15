package com.elasticconcept.java.jdbc;

import com.elasticconcept.connection.SingleConnection;

public class DatabaseTest {

	public void initDatabase() {
		
		SingleConnection.getConnection();
	}
}
