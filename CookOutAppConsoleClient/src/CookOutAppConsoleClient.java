import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;


public class CookOutAppConsoleClient {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();
		
		socket.connect(new InetSocketAddress("localhost", 1939));
		
		socket.getOutputStream().write(new byte[] { 0x04, 39, 0x10, 0 });
		
		byte[] data = new byte[1024];
		
		int size = socket.getInputStream().read(data);
		
		
		HexOutputStream hexOut = new HexOutputStream(System.out);
		hexOut.write(Arrays.copyOf(data, size));
		hexOut.flush();
		
		hexOut.close();
		
		socket.close();

	}

}
