package com.wayne.cookoutapp.server.util;

public class ByteToHexString {
	public static String convert(byte[] data) {
		String s = "";
		for(byte b : data) {
			s += String.format("%02X ", b);
		}
		return s;
	}
}
