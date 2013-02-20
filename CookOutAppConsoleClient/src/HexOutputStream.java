import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class HexOutputStream extends FilterOutputStream {

	public HexOutputStream(OutputStream out) {
		super(out);
	}
	
	public synchronized void write(int b) throws IOException {
		out.write(String.format("%02X ", (byte) b).getBytes());
	}
	
	public synchronized void write(byte[] b) throws IOException {
		write(b, 0, b.length);
	}
	
	public synchronized void write(byte[] b, int off, int len) throws IOException {
		for(int i = off; i < off + len; i++) {
			write(b[i]);
		}
	}

}
