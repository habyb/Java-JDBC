package com.elasticconcept.java.jdbc;

import org.junit.Test;

import com.elasticconcept.connection.SingleConnection;

public class DatabaseTest {

	@Test
	public void initDatabase() {
		
		SingleConnection.getConnection();
	}
}
