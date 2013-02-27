package com.wayne.cookoutapp.server.net.packet.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wayne.cookoutapp.server.CookOutAppServer;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ComboInfoPacket;
import com.wayne.cookoutapp.server.net.packet.server.FailPacket;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;

public class IdentPacketTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetResponse() {
		IdentPacket packet = new IdentPacket();
		
		byte versionHi = (byte) ((CookOutAppServer.getVersion() & 0xFF00) >> 8);
		byte versionLo = (byte) (CookOutAppServer.getVersion() & 0x00FF);
		
		packet.setData(new byte[] { IdentPacket.CLIENT_PACKET_HEADER_IDENT, versionHi, versionLo });
		assertEquals(OKPacket.class, packet.getResponse().getClass());
		
		packet.setData(new byte[] { IdentPacket.CLIENT_PACKET_HEADER_IDENT, (byte) (versionHi + 1), versionLo });	
		assertEquals(FailPacket.class, packet.getResponse().getClass());
		
		packet.setData(new byte[] { IdentPacket.CLIENT_PACKET_HEADER_IDENT });	
		assertEquals(BadPacket.class, packet.getResponse().getClass());
		
		
		
	}

}
