package com.wayne.cookoutapp.server.net.packet.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wayne.cookoutapp.server.ComboRating;

public class ComboInfoPacket extends ServerPacket {
	public ComboInfoPacket(ComboRating cr) {
		
		if(cr == null)
			cr = new ComboRating(0, 0, 0, 0);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream resp = new DataOutputStream(bos);
		
		try {
			resp.writeByte(SERVER_PACKET_HEADER_COMBO_INFO);
			resp.writeInt(cr.getTotalRating());
			resp.writeInt(cr.getTimesRated());
		} catch (IOException e) {

		}
		
		data = bos.toByteArray();
		
	}
}
