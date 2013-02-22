package com.wayne.cookoutapp.server.net.packet.client;

import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class UnidentifiedPacket extends ClientPacket {

	@Override
	public ServerPacket getResponse() {
		return new BadPacket();
	}

}
