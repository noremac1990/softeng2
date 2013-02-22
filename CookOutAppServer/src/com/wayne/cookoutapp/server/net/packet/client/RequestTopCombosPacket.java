package com.wayne.cookoutapp.server.net.packet.client;

import java.sql.SQLException;

import com.wayne.cookoutapp.server.CookOutApp;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;
import com.wayne.cookoutapp.server.net.packet.server.TopCombosPacket;

public class RequestTopCombosPacket extends ClientPacket {

	@Override
	public ServerPacket getResponse() {
		
		if(data.length != 1)
			return new BadPacket();
		
		try {
			return new TopCombosPacket(CookOutApp.getDatabase().getTopList());
		} catch (SQLException e) {
			return new ErrorMessagePacket("Server SQL Error");
		}
	}

}
