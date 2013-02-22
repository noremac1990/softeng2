package com.wayne.cookoutapp.server.net.packet.client;

import java.sql.SQLException;
import java.util.Map;

import com.wayne.cookoutapp.server.ComboRating;
import com.wayne.cookoutapp.server.CookOutAppServer;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;
import com.wayne.cookoutapp.server.net.packet.server.FailPacket;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class RateComboPacket extends ClientPacket {

	@Override
	public ServerPacket getResponse() {
		if (data.length != 4)
			return new BadPacket();

		int flavor1 = data[1];
		int flavor2 = data[2];
		byte rating = data[3];
		
		try {

			if (flavor1 == flavor2)
				return new FailPacket();
			
			if (rating < 0 || rating > 5)
				return new FailPacket();

			Map<Integer, String> flavors = CookOutAppServer.getDatabase()
					.getFlavors();

			if (flavors.get(flavor1) == null || flavors.get(flavor2) == null)
				return new FailPacket();
			
			CookOutAppServer.getDatabase().rateCombo(
					new ComboRating(flavor1, flavor2, 1, rating));
			
		} catch (SQLException e) {
			return new ErrorMessagePacket("Server SQL error");
		}

		return new OKPacket();
	}

}
