package com.wayne.cookoutapp.client.net;

public abstract class Packet {

	protected static final byte CLIENT_PACKET_HEADER_IDENT = 0x00;
	protected static final byte CLIENT_PACKET_REQUEST_FLAVORS = 0x01;
	protected static final byte CLIENT_PACKET_REQUEST_TOP_COMBOS = 0x02;
	protected static final byte CLIENT_PACKET_REQUEST_COMBO_INFO = 0x03;
	protected static final byte CLIENT_PACKET_SEND_COMBO_RATING = 0x04;
	
	protected static final byte CLIENT_PACKET_SHUTDOWN_SERVER = 0x7F;
	
	protected static final byte SERVER_PACKET_HEADER_FAIL = 0x00;
	protected static final byte SERVER_PACKET_HEADER_OK = 0x01;
	protected static final byte SERVER_PACKET_HEADER_FLAVORS = 0x02;
	protected static final byte SERVER_PACKET_HEADER_TOP_COMBOS = 0x03;
	protected static final byte SERVER_PACKET_HEADER_COMBO_INFO = 0x04;
	
	protected static final byte SERVER_PACKET_HEADER_ERROR_MESSAGE = (byte) 0xFE;
	protected static final byte SERVER_PACKET_HEADER_BAD_PACKET = (byte) 0xFF;
	
	protected byte[] data;

	
	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}
	
	public static String decodeError(byte[] data) {
		String error = "";
		
		int pos = 1;
		while(data[pos] != 0) {
			error += (char) data[pos++];
		}
		
		return error;
		
	}

}
