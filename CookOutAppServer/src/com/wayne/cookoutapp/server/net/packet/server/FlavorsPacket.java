package com.wayne.cookoutapp.server.net.packet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class FlavorsPacket extends ServerPacket {
	
	public FlavorsPacket(Map<Integer, String> flavors) {
		ByteArrayOutputStream resp = new ByteArrayOutputStream();
		
		resp.write(SERVER_PACKET_HEADER_FLAVORS);
		
		try {
			for(Map.Entry<Integer, String> e : flavors.entrySet()) {
				resp.write(e.getKey());
				resp.write(e.getValue().getBytes());
				resp.write(0x00);
			}
			resp.write(0x00);
		} catch (IOException e) {
			
		}
		
		data = resp.toByteArray();
		
	}
}
