package com.wayne.cookoutapp.server.net.packet.client;


import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.wayne.cookoutapp.server.CookOutApp;
import com.wayne.cookoutapp.server.net.packet.server.BadPacket;
import com.wayne.cookoutapp.server.net.packet.server.ErrorMessagePacket;
import com.wayne.cookoutapp.server.net.packet.server.FlavorsPacket;
import com.wayne.cookoutapp.server.net.packet.server.ServerPacket;

public class RequestFlavorsPacket extends ClientPacket {

	private static final Logger LOG = Logger.getLogger(RequestFlavorsPacket.class);
	
	@Override
	public ServerPacket getResponse() {
		
		if(data.length != 1)
			return new BadPacket();
		
		try {
			
			return new FlavorsPacket(CookOutApp.getDatabase().getFlavors());
			
		} catch (SQLException e) {
			LOG.error("Failed to get flavors from database", e);
			
			return new ErrorMessagePacket("Server SQL Error");
		}
	}

}
