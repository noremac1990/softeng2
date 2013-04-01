package com.wayne.cookoutapp.client.net;

import java.util.HashMap;
import java.util.Map;

public class RequestFlavorsPacket extends ClientPacket {

	public RequestFlavorsPacket() {
		data = new byte[] { CLIENT_PACKET_REQUEST_FLAVORS };
	}
	
	public Map<Integer, String> handle() throws PacketException {
		
		if(response[0] == SERVER_PACKET_HEADER_FAIL)
			throw new PacketException("Failed to get flavors list.");
		
		if(response[0] == SERVER_PACKET_HEADER_ERROR_MESSAGE)
			throw new PacketException(Packet.decodeError(response));
		
		Map<Integer, String> flavors = new HashMap<Integer, String>();
		int pos = 1;

		while (true) {
			String flavor = "";
			byte fid = response[pos++];
			if (fid == 0)
				break;

			while (response[pos] != 0)
				flavor += (char) response[pos++];

			pos++; // skip 00

			flavors.put(Integer.valueOf(fid), flavor);
		}
		
		
		return flavors;
	}
	
}
