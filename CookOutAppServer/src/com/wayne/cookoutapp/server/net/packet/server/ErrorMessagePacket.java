package com.wayne.cookoutapp.server.net.packet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ErrorMessagePacket extends ServerPacket {

	public ErrorMessagePacket(String message) {
		ByteArrayOutputStream response = new ByteArrayOutputStream();
		
		try {
			response.write(SERVER_PACKET_HEADER_ERROR_MESSAGE);
			response.write(message.getBytes());
			response.write(0x00);
		} catch(IOException e) {
			
		}
		
		data = response.toByteArray();
		
	}

}
