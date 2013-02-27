package com.wayne.cookoutapp.server.net.packet.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wayne.cookoutapp.server.CookOutAppServer;
import com.wayne.cookoutapp.server.db.Database;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.TopCombosPacket;

public class RequestTopCombosPacketTest {

	@Before
	public void setUp() throws Exception {
		Database mockDb = mock(Database.class);
		
		Field f = CookOutAppServer.class.getDeclaredField("database");
		f.setAccessible(true);
		f.set(null, mockDb);

	}

	@Test
	public final void testGetResponse() throws Exception {
		RequestTopCombosPacket packet = new RequestTopCombosPacket();
		
		// wrong len packet
		packet.setData(new byte[] { RequestTopCombosPacket.CLIENT_PACKET_REQUEST_TOP_COMBOS, 0x00 });
		assertEquals(BadPacket.class, packet.getResponse().getClass());
		
		// correct packet
		packet.setData(new byte[] { RequestTopCombosPacket.CLIENT_PACKET_REQUEST_TOP_COMBOS });
		assertEquals(TopCombosPacket.class, packet.getResponse().getClass());
		
		
	}

}
