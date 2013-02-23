package com.wayne.cookoutapp.server.net.packet;

public abstract class Packet {

	protected byte[] data;

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}

}
