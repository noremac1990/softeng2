package com.wayne.cookoutapp.client.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class RequestComboInfoPacket extends ClientPacket {
	
	byte flavor1, flavor2;
	
	
	public RequestComboInfoPacket(byte flavor1, byte flavor2) {
		data = new byte[] { CLIENT_PACKET_REQUEST_COMBO_INFO, flavor1, flavor2 };
		this.flavor1 = flavor1;
		this.flavor2 = flavor2;
	}
	
	public ComboRating handle() throws PacketException {
		ByteArrayInputStream bis = new ByteArrayInputStream(response);
		DataInputStream	dis = new DataInputStream(bis);
		
		byte header = response[0];
		
		if(header == SERVER_PACKET_HEADER_ERROR_MESSAGE)
			throw new PacketException(Packet.decodeError(response));
		
		if(header == SERVER_PACKET_HEADER_FAIL)
			throw new PacketException("Failed to get combo info.");
		
		int timesRated = 0;
		int totalRating = 0;
		
		try {
			totalRating = dis.readInt();
			timesRated = dis.readInt();
		} catch (Exception e) {
			
		}
		
		ComboRating cr = new ComboRating(flavor1, flavor2, timesRated, totalRating);
		
		
		return cr;
	}
}
