package com.wayne.cookoutapp.server.net.packet.client;

import com.wayne.cookoutapp.server.CookOutApp;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.FailPacket;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class IdentPacket extends ClientPacket {

	public IdentPacket() {
	}

	@Override
	public ServerPacket getResponse() {
		if(data.length != 3)
			return new BadPacket();
		
		short version = (short) ((data[1] << 8) | (data[2]));
		
		if(version != CookOutApp.getVersion())
			return new FailPacket();
		else
			return new OKPacket();
	}

}
