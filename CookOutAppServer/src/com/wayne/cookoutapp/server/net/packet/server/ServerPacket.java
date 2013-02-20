package com.wayne.cookoutapp.server.net.packet.server;

import com.wayne.cookoutapp.server.net.packet.Packet;

public abstract class ServerPacket extends Packet {

	protected static final byte SERVER_PACKET_HEADER_FAIL = 0x00;
	protected static final byte SERVER_PACKET_HEADER_OK = 0x01;
	protected static final byte SERVER_PACKET_HEADER_FLAVORS = 0x02;
	protected static final byte SERVER_PACKET_HEADER_TOP_COMBOS = 0x03;
	protected static final byte SERVER_PACKET_HEADER_COMBO_INFO = 0x04;
	
	protected static final byte SERVER_PACKET_HEADER_ERROR_MESSAGE = (byte) 0xFE;
	protected static final byte SERVER_PACKET_HEADER_BAD_PACKET = (byte) 0xFF;
	
}
