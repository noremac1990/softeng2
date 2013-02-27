package com.wayne.cookoutapp.server.net.packet.server;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FlavorsPacketTest {

	@Test
	public final void testFlavorsPacket() {
		Map<Integer, String> flavors = new HashMap<Integer, String>();
		
		flavors.put(1, "V");
		flavors.put(2, "Ba");
		
		FlavorsPacket packet = new FlavorsPacket(flavors);
		byte[] data = packet.getData();
		
		assertEquals(FlavorsPacket.SERVER_PACKET_HEADER_FLAVORS, data[0]);
		
		//first flavor
		assertEquals(1, data[1]);
		assertEquals('V', data[2]);
		assertEquals(0, data[3]);
		
		// second
		
		assertEquals(2, data[4]);
		assertEquals('B', data[5]);
		assertEquals('a', data[6]);
		assertEquals(0, data[7]);
		assertEquals(0, data[8]);
		
		
		
	}

}
