package com.wayne.cookoutapp.server;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.wayne.cookoutapp.server.db.Database;
import com.wayne.cookoutapp.server.net.Server;

public class CookOutAppServer {

	private static final Logger LOG = Logger.getLogger(CookOutAppServer.class);
	private static final short VERSION = 0001;
	private static Server server = null;
	private static Database database = null;
	
	/**
	 * @author Cameron
	 * @param args Program arguments.
	 */
	public static void main(String[] args) {
		LOG.info(String.format("Starting server version %d.", VERSION));
		
		try {
			database = new Database();
		} catch(ClassNotFoundException e) {
			LOG.fatal("Unable to load JDBC driver.", e);
			return;
		} catch(SQLException e) {
			LOG.fatal("SQL Error", e);
			return;
		}
		
		
		server = new Server();
		
		try {
			server.run();
		} catch (IOException e) {
			LOG.fatal("Failed to start server", e);
		}
		
		database.close();
		
		return;
	}

	public static short getVersion() {
		return VERSION;
	}
	
	public static Server getServer() {
		return server;
	}
	
	public static Database getDatabase() {
		return database;
	}
}
