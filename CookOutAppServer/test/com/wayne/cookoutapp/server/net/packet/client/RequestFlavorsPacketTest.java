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
import com.wayne.cookoutapp.server.net.packet.server.FlavorsPacket;

public class RequestFlavorsPacketTest {

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
	public final void testGetResponse() {
		RequestFlavorsPacket packet = new RequestFlavorsPacket();
		
		packet.setData(new byte[] { RequestFlavorsPacket.CLIENT_PACKET_REQUEST_FLAVORS });
		
		assertEquals(FlavorsPacket.class, packet.getResponse().getClass());
		
		
	}

}
