package com.wayne.cookoutapp.server.net.packet.client;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.wayne.cookoutapp.server.CookOutAppServer;
import com.wayne.cookoutapp.server.db.Database;
import com.wayne.cookoutapp.server.net.Server;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;
import com.wayne.cookoutapp.server.net.packet.server.OKPacket;

public class RateComboPacketTest {

	@BeforeClass
	public static void setUp() throws Exception {
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
		RateComboPacket rcp = new RateComboPacket();
		
		byte header = RateComboPacket.CLIENT_PACKET_SEND_COMBO_RATING;
		
		// bad length
		rcp.setData(new byte[] { header });
		assertTrue(rcp.getResponse() instanceof BadPacket);
		
		// flavors equal
		rcp.setData(new byte[] { header, 1, 1, 2 });
		assertTrue(rcp.getResponse() instanceof ErrorMessagePacket);
		
		// flavors dont exist
		rcp.setData(new byte[] { header, 1, 6, 2 });
		assertTrue(rcp.getResponse() instanceof ErrorMessagePacket);
		
		// rating out of range
		rcp.setData(new byte[] { header, 1, 2, 10 });
		assertTrue(rcp.getResponse() instanceof ErrorMessagePacket);
		
		// everything ok!
		rcp.setData(new byte[] { header, 1, 2, 5 });
		assertTrue(rcp.getResponse() instanceof OKPacket);
		
		
		
	}

}
