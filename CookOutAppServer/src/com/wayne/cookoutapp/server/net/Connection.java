package com.wayne.cookoutapp.server.net;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.wayne.cookoutapp.server.net.packet.client.ClientPacket;

public class Connection {
	private Socket socket;
	
	private static final Logger LOG = Logger.getLogger(Connection.class);
	
	Connection(Socket socket) {
		this.socket = socket;
	}
	
	public void handle() throws IOException {
		byte[] data = new byte[1024];
		int size = socket.getInputStream().read(data, 0, 1024);
		
		if(size <= 0)
			return;
		
		LOG.debug("Recieved data: " + DatatypeConverter.printHexBinary(data));
		
		ClientPacket clientPacket = ClientPacket.parseIncomingPacket(Arrays.copyOf(data, size));
		
		byte[] response = clientPacket.getResponse().getData();
		
		LOG.debug("Sent data: " + DatatypeConverter.printHexBinary(data));
		
		socket.getOutputStream().write(response);
		
		return;
	}
	
	public void close() throws IOException {
		socket.shutdownOutput();
		socket.close();
	}
	
	@Override
	public String toString() {
		return socket.getRemoteSocketAddress().toString();
	}
}
