package com.wayne.cookoutapp.server.net.packet.server;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wayne.cookoutapp.server.net.packet.server.BadPacket;


public class BadPacketTest {
	
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
	public void testGetData() {
		BadPacket testItem = new BadPacket();
		assertArrayEquals(new byte[]{ (byte) 0xFF }, testItem.getData());
	}

}
