import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;


public class CookOutAppConsoleClient {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.print("Enter length of packet (0 to quit): ");
			int len = s.nextInt();
			
			if(len == 0)
				break;
			
			System.out.println("Type bytes of packet in hex, seperated by spaces (Ex: 1F 00 10 FF).");
			
			byte[] packet = new byte[len];
			
			for(int i = 0; i < len; i++) {
				String input = s.next();
				
				packet[i] = Byte.parseByte(input, 16);
			}
			
			Socket socket = new Socket();
			
			socket.connect(new InetSocketAddress("localhost", 1939));
			
			socket.getOutputStream().write(packet);
			
			byte[] data = new byte[1024];
			
			int size = socket.getInputStream().read(data);
			
			
			@SuppressWarnings("resource")
			HexOutputStream hexOut = new HexOutputStream(System.out);
			hexOut.write(Arrays.copyOf(data, size));
			hexOut.flush();
			
			socket.close();
			
			System.out.println();
			
		}
		
		

		/*
		int pos = 1;
		
		while(true) {
			pos++;
			if(data[pos] == 0)
				break;

			while(data[pos] != 0) {
				System.out.print((char) data[pos]);
				pos++;
			}
			pos++;
			System.out.println();
		}*/
	

	}

}
