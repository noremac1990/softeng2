package com.wayne.cookoutapp.client.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

public class ServerRequest {

	private static final String ADDRESS = "www.cameronedwards.net";
	private static final int PORT = 1939;
	
	
	public static final void request(ClientPacket p) throws IOException {
		Socket socket = new Socket();
		
		socket.connect(new InetSocketAddress(ADDRESS, PORT), 1000);
		
		socket.getOutputStream().write(p.getData());
		
		byte[] resp = new byte[1024];
		int bytes = socket.getInputStream().read(resp);
		
		resp = Arrays.copyOf(resp, bytes);
		
		p.setResponse(resp);
		
		socket.close();
		return;
	}
	
	
	
	
}
