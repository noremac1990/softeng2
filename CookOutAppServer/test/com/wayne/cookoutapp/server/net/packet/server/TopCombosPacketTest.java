package com.wayne.cookoutapp.server.net.packet.server;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.wayne.cookoutapp.server.ComboRating;

public class TopCombosPacketTest {

	@Test
	public final void testTopCombosPacket() throws Exception {
		List<ComboRating> comboRatingList = new ArrayList<>();
		
		comboRatingList.add(new ComboRating(1, 2, 3, 4));
		comboRatingList.add(new ComboRating(5, 6, 7, 8));
		
		TopCombosPacket packet = new TopCombosPacket(comboRatingList);
		
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
		DataInputStream dis = new DataInputStream(bis);
		
		assertEquals(TopCombosPacket.SERVER_PACKET_HEADER_TOP_COMBOS, dis.readByte());
		
		// get first flavor
		assertEquals(1, dis.readByte());
		assertEquals(2, dis.readByte());
		
		// get ratings
		assertEquals(4, dis.readInt());
		assertEquals(3, dis.readInt());
		
		// next combo
		
		// get first flavor
		assertEquals(5, dis.readByte());
		assertEquals(6, dis.readByte());
		
		// get ratings
		assertEquals(8, dis.readInt());
		assertEquals(7, dis.readInt());
		
		// 00 ending
		assertEquals(0, dis.readByte());
		
		
		
	}

}
