package com.wayne.cookoutapp.server.net.packet.client;

import com.wayne.cookoutapp.server.CookOutAppServer;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class ShutdownPacket extends ClientPacket {

	@Override
	public ServerPacket getResponse() {
		CookOutAppServer.getServer().shutdown();
		return new OKPacket();
	}

}
