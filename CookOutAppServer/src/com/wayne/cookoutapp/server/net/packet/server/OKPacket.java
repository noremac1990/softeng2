package com.wayne.cookoutapp.server.net.packet.server;


public class OKPacket extends ServerPacket {

	public OKPacket() {
		data = new byte[] { SERVER_PACKET_HEADER_OK };
	}

}
