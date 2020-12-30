// SERVER PROGRAM

// Import the necessary dependencies
import java.net.*;
public class Server {
	static final int PORT = 7890;
	public static void main( String args[] ) throws Exception {
		String theReceiveString;
		byte[] theReceiveBuffer = new byte[2048];
		// Make a packet to receive into
		DatagramPacket theReceivePacket = new DatagramPacket(theReceiveBuffer, theReceiveBuffer.length);
		// Make a socket to listen on
		DatagramSocket theReceiveSocket = new DatagramSocket(PORT);
		// Receive a packet from the client
		theReceiveSocket.receive(theReceivePacket);
		// Convert the packet to a string
		theReceiveString = new String(theReceiveBuffer, 0, theReceivePacket.getLength());
		// Print out the string
		System.out.println(theReceiveString);
		// Close the socket
		theReceiveSocket.close();
	}
}