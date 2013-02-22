package com.wayne.cookoutapp.server.net.packet.client;

import java.sql.SQLException;
import com.wayne.cookoutapp.server.CookOutApp;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ComboInfoPacket;
import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class RequestComboInfoPacket extends ClientPacket {

	@Override
	public ServerPacket getResponse() {
		
		if(data.length != 3)
			return new BadPacket();
		
		try {
			
			return new ComboInfoPacket(CookOutApp.getDatabase().getComboRating(data[1], data[2]));
		} catch (SQLException e) {
			return new ErrorMessagePacket("Server SQL error.");
		}
		
	}

}
