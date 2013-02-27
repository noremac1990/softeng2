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
import com.wayne.cookoutapp.server.net.packet.server.ComboInfoPacket;
import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;

public class RequestComboInfoPacketTest {

	@Before
	public void setUp() throws Exception {
		Map<Integer, String> flavors = new HashMap<>();
		Database mockDb = mock(Database.class);
		
		Field f = CookOutAppServer.class.getDeclaredField("database");
		f.setAccessible(true);
		f.set(null, mockDb);
		
		flavors.put(1, "vanilla");
		flavors.put(2, "chocolate");
		
		when(mockDb.getFlavors()).thenReturn(flavors);
	}

	@Test
	public final void testGetResponse() throws Exception {
		RequestComboInfoPacket packet = new RequestComboInfoPacket();
		
		byte header = RequestComboInfoPacket.CLIENT_PACKET_REQUEST_COMBO_INFO;
		
		// packet too short
		packet.setData(new byte[] { header });
		assertEquals(BadPacket.class, packet.getResponse().getClass());

		// bad flavor ids
		packet.setData(new byte[] { header, 99, 3});
		assertEquals(ErrorMessagePacket.class, packet.getResponse().getClass());
		
		// success
		packet.setData(new byte[] { header, 1, 2});
		assertEquals(ComboInfoPacket.class, packet.getResponse().getClass());
		
		
	}

}
