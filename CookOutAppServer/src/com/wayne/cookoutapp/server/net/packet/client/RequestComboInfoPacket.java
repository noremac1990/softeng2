package com.wayne.cookoutapp.server.net.packet.client;

import java.sql.SQLException;
import java.util.Map;

import com.wayne.cookoutapp.server.CookOutAppServer;
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
			byte flavor1 = data[1];
			byte flavor2 = data[2];
			
			Map<Integer, String> flavors = CookOutAppServer.getDatabase()
					.getFlavors();

			if (flavors.get(Integer.valueOf(flavor1)) == null || flavors.get(Integer.valueOf(flavor2)) == null)
				return new ErrorMessagePacket("Flavors don't exist.");
			
			return new ComboInfoPacket(CookOutAppServer.getDatabase().getComboRating(flavor1, flavor2));
		} catch (SQLException e) {
			return new ErrorMessagePacket("Server SQL error.");
		}
		
	}

}
