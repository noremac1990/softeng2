package com.wayne.cookoutapp.server.net.packet.client;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientPacketTest {

	@Test
	public final void testParseIncomingPacket() {

		ClientPacket packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_HEADER_IDENT });
		assertTrue(packet instanceof IdentPacket);
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_REQUEST_FLAVORS });
		assertTrue(packet instanceof RequestFlavorsPacket);
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_REQUEST_COMBO_INFO });
		assertTrue(packet instanceof RequestComboInfoPacket);
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_SHUTDOWN_SERVER });
		assertTrue(packet instanceof ShutdownPacket);
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_SEND_COMBO_RATING });
		assertTrue(packet instanceof RateComboPacket);
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_REQUEST_TOP_COMBOS });
		assertTrue(packet instanceof RequestTopCombosPacket);
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { 0x26 });
		assertTrue(packet instanceof UnidentifiedPacket);
		
	}

}
