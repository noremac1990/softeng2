package com.wayne.cookoutapp.server.net;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import com.wayne.cookoutapp.server.net.packet.client.ClientPacket;

public class Connection {
	private Socket socket;
	
	Connection(Socket socket) {
		this.socket = socket;
	}
	
	public void handle() throws IOException {
		byte[] data = new byte[1024];
		int size = socket.getInputStream().read(data, 0, 1024);
		
		if(size <= 0)
			return;
		
		ClientPacket clientPacket = ClientPacket.parseIncomingPacket(Arrays.copyOf(data, size));
		
		byte[] response = clientPacket.getResponse().getData();
		
		socket.getOutputStream().write(response);
		
		return;
	}
	
	public void close() throws IOException {
		socket.shutdownOutput();
		socket.close();
	}
}
