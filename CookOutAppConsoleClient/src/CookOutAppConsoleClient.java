import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class CookOutAppConsoleClient {

	static final Scanner s = new Scanner(System.in);
	
	//static final String serverAddr = "localhost";
	
	static final String serverAddr = "ec2-54-234-213-211.compute-1.amazonaws.com";
	
	public static void main(String[] args) throws IOException {

		boolean running = true;

		while (running) {
			System.out.println("0.\tQuit");
			System.out.println("1.\tCustom packet");
			System.out.println("2.\tIdent packet");
			System.out.println("3.\tRequest flavors packet");
			System.out.println("4.\tRequest top combos packet");
			System.out.println("5.\tRequest combo info packet");
			System.out.println("6.\tRate combo packet");
			System.out.println("7.\tServer shutdown packet");

			System.out.print("Enter choice: ");
			
			int choice = s.nextInt();

			switch (choice) {
			case 0:
				running = false;
				break;
			case 1:
				sendData(customPacket());
				break;
			case 2:
				sendData(identPacket());
				break;
			case 3:
				sendData(new byte[] { 0x01 });
				break;
			case 4:
				sendData(new byte[] { 0x02 });
				break;
			case 5:
				sendData(comboInfoPacket());
				break;
			case 6:
				sendData(rateComboPacket());
				break;
			case 7:
				sendData(new byte[] { 0x7F });
				break;
			}
		}
		
	}
	
	
	private static byte[] rateComboPacket() {
		System.out.print("Flavor 1 (FID): ");
		byte f1 = s.nextByte();
		System.out.print("Flavor 2 (FID): ");
		byte f2 = s.nextByte();
		System.out.print("Rating (0-5): ");
		byte rating = s.nextByte();
		
		return new byte[] { 0x04, f1, f2, rating };
	}


	private static byte[] comboInfoPacket() {
		System.out.print("Flavor 1 (FID): ");
		byte f1 = s.nextByte();
		System.out.print("Flavor 2 (FID): ");
		byte f2 = s.nextByte();
		
		return new byte[] { 0x03, f1, f2 };
	}


	private static byte[] identPacket() throws IOException {
		System.out.print("Enter version: ");
		short version = s.nextShort();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream packet = new DataOutputStream(bos);
		
		packet.write(0x00);
		packet.writeShort(version);
		packet.flush();
		bos.flush();
		
		
		return bos.toByteArray();
	}


	public static byte[] customPacket() {
		System.out.print("Enter length of packet (0 to quit): ");
		int len = s.nextInt();

		System.out
				.println("Type bytes of packet in hex, seperated by spaces (Ex: 1F 00 10 FF).");

		byte[] packet = new byte[len];

		for (int i = 0; i < len; i++) {
			String input = s.next();

			packet[i] = Byte.parseByte(input, 16);
		}
		
		return packet;
	}
	
	public static void sendData(byte[] packet) throws IOException {
		Socket socket = new Socket();
		
		socket.connect(new InetSocketAddress(serverAddr, 1939));
		
		socket.getOutputStream().write(packet);
		
		byte[] data = new byte[1024];
		
		int size = socket.getInputStream().read(data);
		
		System.out.print("Received data: ");
		
		data = Arrays.copyOf(data, size);
		
		@SuppressWarnings("resource")
		HexOutputStream hexOut = new HexOutputStream(System.out);
		hexOut.write(data);
		hexOut.flush();
		
		socket.close();
		
		System.out.println();
		
		switch(data[0])
		{
		case 0x01:
			System.out.println("PACKET: OK packet.");
			break;
		case 0x00:
			System.out.println("PACKET: Fail packet.");
			break;
		case 0x02:
			System.out.println("PACKET: Flavor list packet.");
			parseFlavorsPacket(data);
			break;
		case 0x03:
			System.out.println("PACKET: Top combo list packet.");
			parseTopComboList(data);
			break;
		case 0x04:
			System.out.println("PACKET: Combo info packet.");
			parseComboInfoPacket(data);
			break;
		case (byte) 0xFE:
			System.out.println("PACKET: Error message packet.");
			parseErrorMessagePacket(data);
			break;
		case (byte) 0xFF:
			System.out.println("PACKET: Client sent bad packet.");
			break;
		}
		
	}


	private static void parseComboInfoPacket(byte[] data) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bis);
		
		dis.readByte();
		
		int totalRating = dis.readInt();
		int timesRated = dis.readInt();
		
		System.out.printf("times rated: %d total rating: %d rating: %f%n", timesRated,
				totalRating, (double) totalRating / timesRated);
		
	}


	private static void parseErrorMessagePacket(byte[] data) {
		int pos = 1;
		
		while(data[pos] != 0)
			System.out.print((char) data[pos++]);
		
		System.out.println();
	}


	private static void parseTopComboList(byte[] data) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bis);
		
		dis.readByte(); // skip header
		
		while(true) {
			byte f1 = dis.readByte();
			if(f1 == 0)
				break;
			
			byte f2 = dis.readByte();
			
			int timesRated = dis.readInt();
			int totalRating = dis.readInt();
			
			System.out.printf("f1: %d f2: %d times rated: %d total rating: %d actual rating: %f%n",
					f1, f2, timesRated, totalRating, (double) totalRating / timesRated);
		}
		
		
		
	}


	private static void parseFlavorsPacket(byte[] data) {
		int pos = 1;
		
		while(true) {
			byte fid = data[pos++];
			if(fid == 0)
				break;
			
			System.out.print(fid + " - ");
			
			while(data[pos] != 0)
				System.out.print((char) data[pos++]);

			pos++; // skip 00
			
			System.out.println();
		}
		
	}
}
