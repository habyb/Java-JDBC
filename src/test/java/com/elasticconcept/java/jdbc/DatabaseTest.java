package com.elasticconcept.java.jdbc;

import org.junit.Test;

import com.elasticconcept.java.jdbc.connection.SingleConnection;

public class DatabaseTest {

	@Test
	public void initDatabase() {
		
		SingleConnection.getConnection();
	}
}
