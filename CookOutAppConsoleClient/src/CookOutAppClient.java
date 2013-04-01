import com.wayne.cookoutapp.client.net.ClientPacket;
import com.wayne.cookoutapp.client.net.ComboRating;
import com.wayne.cookoutapp.client.net.IdentPacket;
import com.wayne.cookoutapp.client.net.RateComboPacket;
import com.wayne.cookoutapp.client.net.RequestComboInfoPacket;
import com.wayne.cookoutapp.client.net.RequestFlavorsPacket;
import com.wayne.cookoutapp.client.net.RequestTopCombosPacket;
import com.wayne.cookoutapp.client.net.ServerRequest;


public class CookOutAppClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*IdentPacket packet = new IdentPacket((short) 0xFFFF);
		
		ServerRequest.request(packet);
		
		packet.handle();*/
		
		/*RateComboPacket p = new RateComboPacket((byte) 2, (byte) 2, (byte) 99);
		
		ServerRequest.request(p);
		
		p.handle();*/
		
		/*RequestFlavorsPacket p = new RequestFlavorsPacket();
		
		ServerRequest.request(p);
		
		System.err.println(p.handle().get((Integer) 5));*/
		
		/* RequestComboInfoPacket p = new RequestComboInfoPacket((byte) 99, (byte) 2);
		
		ServerRequest.request(p);
		
		p.handle(); */
		
		RequestTopCombosPacket p = new RequestTopCombosPacket();
		
		ServerRequest.request(p);
		
		
		for(ComboRating cr : p.handle()) {
			System.out.printf("%d %d %d %d%n", cr.getFlavor1(), cr.getFlavor2(), cr.getTotalRating(), cr.getTimesRated());
		}
		
	}

}
