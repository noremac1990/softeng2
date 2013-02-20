package com.wayne.cookoutapp.server.net.packet.server;

public class BadPacket extends ServerPacket {

	public BadPacket() {
		data = new byte[] { SERVER_PACKET_HEADER_BAD_PACKET };
	}

}
