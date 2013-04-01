package com.wayne.cookoutapp.server.net.packet.client;

import java.sql.SQLException;
import java.util.Map;

import com.wayne.cookoutapp.server.ComboRating;
import com.wayne.cookoutapp.server.CookOutAppServer;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class RateComboPacket extends ClientPacket {

	@Override
	public ServerPacket getResponse() {
		if (data.length != 4)
			return new BadPacket();

		byte flavor1 = data[1];
		byte flavor2 = data[2];
		byte rating = data[3];
		
		try {

			if (flavor1 == flavor2)
				return new ErrorMessagePacket("Flavors are the same.");
			
			if (rating < 0 || rating > 5)
				return new ErrorMessagePacket("Rating must be from 0 to 5.");

			Map<Integer, String> flavors = CookOutAppServer.getDatabase()
					.getFlavors();

			if (flavors.get(Integer.valueOf(flavor1)) == null || flavors.get(Integer.valueOf(flavor2)) == null)
				return new ErrorMessagePacket("Flavors don't exist.");
			
			CookOutAppServer.getDatabase().rateCombo(
					new ComboRating(flavor1, flavor2, 1, rating));
			
		} catch (SQLException e) {
			return new ErrorMessagePacket("Server SQL error");
		}

		return new OKPacket();
	}

}
