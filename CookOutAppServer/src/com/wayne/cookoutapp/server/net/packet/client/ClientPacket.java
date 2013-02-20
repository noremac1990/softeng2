package com.wayne.cookoutapp.server.net.packet.client;

import com.wayne.cookoutapp.server.net.packet.Packet;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public abstract class ClientPacket extends Packet {
	
	protected static final byte CLIENT_PACKET_HEADER_IDENT = 0x00;
	protected static final byte CLIENT_PACKET_REQUEST_FLAVORS = 0x01;
	protected static final byte CLIENT_PACKET_REQUEST_TOP_COMBOS = 0x02;
	protected static final byte CLIENT_PACKET_REQUEST_COMBO_INFO = 0x03;
	protected static final byte CLIENT_PACKET_SEND_COMBO_RATING = 0x04;

	
	public static ClientPacket parseIncomingPacket(byte[] data) {
		ClientPacket clientPacket = null;
		
		if(data.length <= 0)
			clientPacket = new UnidentifiedPacket();
		
		switch(data[0])
		{
		case CLIENT_PACKET_HEADER_IDENT:
			clientPacket = new IdentPacket();
			break;
		default:
			clientPacket = new UnidentifiedPacket();
			break;
		}
		
		clientPacket.data = data;
		
		return clientPacket;
	}
	
	public abstract ServerPacket getResponse();
}
