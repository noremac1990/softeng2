package com.wayne.cookoutapp.server.net.packet.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class FailPacketTest {

	@Test
	public final void testFailPacket() {
		FailPacket packet = new FailPacket();
		
		assertEquals(FailPacket.SERVER_PACKET_HEADER_FAIL, packet.getData()[0]);
	}

}
