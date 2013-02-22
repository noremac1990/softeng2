package com.wayne.cookoutapp.server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

public class Server {
	private final int DEFAULT_PORT = 1939;
	private final Logger LOG = Logger.getLogger(Server.class);
	
	private ServerSocket serverSocket = null;
	private boolean running = true;
	
	public Server() {
		
	}
	
	public void run() throws IOException {
		serverSocket = new ServerSocket(DEFAULT_PORT);
		
		serverSocket.setSoTimeout(1000);
		
		while(running) {
			try {
				Connection con = new Connection(serverSocket.accept());
				con.handle();
				con.close();
			} catch (SocketTimeoutException e) {
				continue;
			} catch (IOException e) {
				LOG.info("Connection failed", e);
			}
		}
		
		serverSocket.close();
	}
	
	public void shutdown() {
		running = false;
		
		

		
		
	}
}
