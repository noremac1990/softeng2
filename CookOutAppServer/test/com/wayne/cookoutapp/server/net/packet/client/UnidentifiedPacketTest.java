package com.wayne.cookoutapp.server.net.packet.client;

import static org.junit.Assert.*;

import org.junit.Test;

import com.wayne.cookoutapp.server.net.packet.server.BadPacket;

public class UnidentifiedPacketTest {

	@Test
	public final void testGetResponse() {
		UnidentifiedPacket p = new UnidentifiedPacket();
		
		assertTrue(p.getResponse() instanceof BadPacket);
	}

}
