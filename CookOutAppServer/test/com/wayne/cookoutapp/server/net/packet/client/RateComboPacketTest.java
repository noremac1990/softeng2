package com.wayne.cookoutapp.server.net.packet.client;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;

public class RateComboPacketTest {

	@Test
	public final void testGetResponse() throws Exception {
		RateComboPacket rcp = new RateComboPacket();
		Map<Integer, String> flavors = new HashMap<>();
		
		flavors.put(1, "vanilla");
		flavors.put(2, "chocolate");
		
		//stub(CookOutAppServer.getDatabase()).toReturn(mock(Database.class));
		
		byte header = RateComboPacket.CLIENT_PACKET_SEND_COMBO_RATING;
		
		// bad length
		rcp.setData(new byte[] { header });
		assert(rcp.getResponse() instanceof ErrorMessagePacket);
		
		// flavors equal
		rcp.setData(new byte[] { header, 1, 1, 2 });
		assert(rcp.getResponse() instanceof ErrorMessagePacket);
		
		// flavors dont exist
		rcp.setData(new byte[] { header, 1, 6, 2 });
		assert(rcp.getResponse() instanceof ErrorMessagePacket);
		
		// rating out of range
		rcp.setData(new byte[] { header, 1, 2, 10 });
		assert(rcp.getResponse() instanceof ErrorMessagePacket);
		
		
	}

}
