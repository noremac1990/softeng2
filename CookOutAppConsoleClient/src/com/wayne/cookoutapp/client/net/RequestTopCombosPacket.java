package com.wayne.cookoutapp.client.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

public class RequestTopCombosPacket extends ClientPacket {

	public RequestTopCombosPacket() {
		data = new byte[] { CLIENT_PACKET_REQUEST_TOP_COMBOS };
	}

	public List<ComboRating> handle() throws PacketException {
		byte header = response[0];

		if (header == SERVER_PACKET_HEADER_FAIL)
			throw new PacketException("Failed to get top list.");

		if (header == SERVER_PACKET_HEADER_ERROR_MESSAGE)
			throw new PacketException(Packet.decodeError(response));

		List<ComboRating> topList = new ArrayList<ComboRating>();

		ByteArrayInputStream bis = new ByteArrayInputStream(response);
		DataInputStream dis = new DataInputStream(bis);

		try {

			dis.readByte(); // skip header

			while (true) {
				byte f1 = dis.readByte();
				if (f1 == 0)
					break;

				byte f2 = dis.readByte();

				int timesRated = dis.readInt();
				int totalRating = dis.readInt();

				topList.add(new ComboRating(f1, f2, timesRated, totalRating));
				
			}

		} catch (Exception e) {
		}

		return topList;
	}

}
