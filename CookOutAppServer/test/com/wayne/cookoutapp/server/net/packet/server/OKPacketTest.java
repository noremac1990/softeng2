package com.wayne.cookoutapp.server.net.packet.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class OKPacketTest {

	@Test
	public final void testOKPacket() {
		OKPacket packet = new OKPacket();
		
		assertEquals(OKPacket.SERVER_PACKET_HEADER_OK, packet.getData()[0]);
	}

}
