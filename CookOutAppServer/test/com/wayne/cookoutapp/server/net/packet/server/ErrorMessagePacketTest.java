package com.wayne.cookoutapp.server.net.packet.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class ErrorMessagePacketTest {

	@Test
	public final void testErrorMessagePacket() {
		ErrorMessagePacket packet = new ErrorMessagePacket("TESTXYZ");
		
		byte[] data = packet.getData();
		
		assertEquals(ErrorMessagePacket.SERVER_PACKET_HEADER_ERROR_MESSAGE, data[0]);
		
		assertEquals('T', data[1]);
		
		assertEquals('E', data[2]);
		
		assertEquals('Z', data[7]);
		assertEquals(0, data[8]);
		
		
	}

}
