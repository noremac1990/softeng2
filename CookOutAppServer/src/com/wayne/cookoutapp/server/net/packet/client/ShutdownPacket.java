package com.wayne.cookoutapp.server.net.packet.client;

import com.wayne.cookoutapp.server.CookOutApp;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class ShutdownPacket extends ClientPacket {

	@Override
	public ServerPacket getResponse() {
		CookOutApp.getServer().shutdown();
		return new OKPacket();
	}

}
