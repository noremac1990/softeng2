package com.wayne.cookoutapp.server.net.packet.client;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientPacketTest {

	@Test
	public final void testParseIncomingPacket() {

		ClientPacket packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_HEADER_IDENT });
		assertEquals(IdentPacket.class, packet.getClass());
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_REQUEST_FLAVORS });
		assertEquals(RequestFlavorsPacket.class, packet.getClass());
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_REQUEST_COMBO_INFO });
		assertEquals(RequestComboInfoPacket.class, packet.getClass());
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_SHUTDOWN_SERVER });
		assertEquals(ShutdownPacket.class, packet.getClass());
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_SEND_COMBO_RATING });
		assertEquals(RateComboPacket.class, packet.getClass());
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { ClientPacket.CLIENT_PACKET_REQUEST_TOP_COMBOS });
		assertEquals(RequestTopCombosPacket.class, packet.getClass());
		
		packet = ClientPacket.parseIncomingPacket(new byte[] { 0x26 });
		assertEquals(UnidentifiedPacket.class, packet.getClass());
		
	}

}
