package com.wayne.cookoutapp.client.net;

public class ClientPacket extends Packet {
	protected byte[] response;
	
	public void setResponse(byte[] resp) {
		response = resp;
	}
	
	public byte[] getResponse() {
		return response;
	}
	
}
