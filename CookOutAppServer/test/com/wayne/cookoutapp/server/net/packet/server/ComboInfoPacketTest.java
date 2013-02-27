package com.wayne.cookoutapp.server.net.packet.server;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import org.junit.AfterClass;
import org.junit.Test;

import com.wayne.cookoutapp.server.ComboRating;

public class ComboInfoPacketTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public final void testComboInfoPacket() throws Exception {
		ComboInfoPacket packet = new ComboInfoPacket(new ComboRating(0, 0, 2, 3));
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.getData());
		DataInputStream dis = new DataInputStream(bis);
		
		
		byte header = dis.readByte();
		
		assertEquals(ComboInfoPacket.SERVER_PACKET_HEADER_COMBO_INFO, header);
		
		int totalRating = dis.readInt();
		int timesRated = dis.readInt();
		
		assertEquals(timesRated, 2);
		assertEquals(totalRating, 3);
		
		packet = new ComboInfoPacket(null);
		bis = new ByteArrayInputStream(packet.getData());
		dis = new DataInputStream(bis);
		
		header = dis.readByte();
		
		assertEquals(ComboInfoPacket.SERVER_PACKET_HEADER_COMBO_INFO, header);
		
		totalRating = dis.readInt();
		timesRated = dis.readInt();
		
		assertEquals(timesRated, 0);
		assertEquals(totalRating, 0);
	}

}
