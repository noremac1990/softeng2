package com.wayne.cookoutapp.server.net.packet.server;

public class FailPacket extends ServerPacket {

	public FailPacket() {
		data = new byte[] { SERVER_PACKET_HEADER_FAIL };
	}

}
