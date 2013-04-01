import com.wayne.cookoutapp.client.net.ClientPacket;
import com.wayne.cookoutapp.client.net.IdentPacket;
import com.wayne.cookoutapp.client.net.RateComboPacket;
import com.wayne.cookoutapp.client.net.ServerRequest;


public class CookOutAppClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*IdentPacket packet = new IdentPacket((short) 0xFFFF);
		
		ServerRequest.request(packet);
		
		packet.handle();*/
		
		RateComboPacket p = new RateComboPacket((byte) 2, (byte) 2, (byte) 99);
		
		ServerRequest.request(p);
		
		p.handle();
		
	}

}
