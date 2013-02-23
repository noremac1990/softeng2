package com.wayne.cookoutapp.server.net.packet.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wayne.cookoutapp.server.CookOutAppServer;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.FailPacket;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;

public class TestIdentPacket {

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
		IdentPacket testItem = new IdentPacket();
		
		byte versionHi = (byte) ((CookOutAppServer.getVersion() & 0xFF00) >> 8);
		byte versionLo = (byte) (CookOutAppServer.getVersion() & 0x00FF);
		
		testItem.setData(new byte[] { 0x00, versionHi, versionLo });
		
		assertTrue(testItem.getResponse() instanceof OKPacket);
		
		testItem.setData(new byte[] { 0x00, (byte) (versionHi + 1), versionLo });
		
		assertTrue(testItem.getResponse() instanceof FailPacket);
		
		testItem.setData(new byte[] { 0x00 });
		
		assertTrue(testItem.getResponse() instanceof BadPacket);
		
	}

}
