package com.wayne.cookoutapp.client.net;

public class RateComboPacket extends ClientPacket {
	public RateComboPacket(byte flavor1, byte flavor2, byte rating)
	{
		data = new  byte[] { 0x04, flavor1, flavor2, rating };
	}
	
	public void handle() throws PacketException {
		byte respHeader = response[0];
		
		if(respHeader == SERVER_PACKET_HEADER_OK)
			return;
		
		if(respHeader == SERVER_PACKET_HEADER_FAIL) {
			throw new PacketException("Failed to rate combo info.");
		}
		
		if(respHeader == SERVER_PACKET_HEADER_ERROR_MESSAGE) {
			throw new PacketException(Packet.decodeError(response));
		}
		
	}
	
}
