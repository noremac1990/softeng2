package com.wayne.cookoutapp.client.net;

public class IdentPacket extends ClientPacket {
	public IdentPacket(short version) {
		data = new byte[3];
		
		data[0] = CLIENT_PACKET_HEADER_IDENT;
		data[1] = (byte) ((version & 0xFF00) >> 8);
		data[2] = (byte) (version & 0x00FF);
	}
	
	public void handle() throws PacketException {
		byte respHeader = response[0];
		
		if(respHeader == SERVER_PACKET_HEADER_OK)
			return;
		
		if(respHeader == SERVER_PACKET_HEADER_FAIL) {
			throw new PacketException("Failed to request version info.");
		}
	}
	
}
