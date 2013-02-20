package com.wayne.cookoutapp.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Database {
	private Connection conn = null;
	
	private static final String CONNECTION_STRING = "jdbc:sqlite:cookout.db";
	private static final Logger LOG = Logger.getLogger(Database.class);
	
	public Database() throws SQLException, ClassNotFoundException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			LOG.fatal("Failed to load sqlite JDBC drivers");
			throw e;
		}
		
		conn = DriverManager.getConnection(CONNECTION_STRING);
		
	}
	
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			LOG.error("Failed to close sql connection.");
		}
	}
	
}
