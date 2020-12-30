// CLIENT PROGRAM 

// Import the necessary dependencies
import java.net.*;
import java.util.*;
import java.io.IOException;
public class Client {
	static final int PORT = 7890;
	public static void main( String args[] ) throws Exception {
		Scanner sc = new Scanner(System.in);
		String theStringToSend = "";
		System.out.print("Enter the message to be sent: ");
		theStringToSend = sc.nextLine();
		byte[] theByteArray = new byte[theStringToSend.length()];
		theByteArray = theStringToSend.getBytes();
		// Get the IP address of our destination
		InetAddress theIPAddress = null;
		try {
			theIPAddress = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			System.out.println("Host not found: " + e);
			System.exit(1);
		}
		// Build the packet to be sent
		DatagramPacket thePacket = new DatagramPacket(theByteArray, theStringToSend.length(), theIPAddress, PORT);
		// Send the packet
		DatagramSocket theSocket = null;
		try {
			theSocket = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println("Underlying network software has failed: " + e);
			System.exit(1);
		}
		try {
			theSocket.send(thePacket);
		} catch (IOException e) {
			System.out.println("IO Exception: " + e);
		}
		theSocket.close();
	}
}