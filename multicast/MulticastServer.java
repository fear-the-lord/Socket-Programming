// SERVER PROGRAM
    
// Import all the necessary dependencies
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class MulticastServer {

   // Create a function that sends the message to the client
   public static void sendMessage(String message, String ipAddress, int port) throws IOException {
      // Create a datagram socket
      DatagramSocket socket = new DatagramSocket();
      // Get the IP address of the client
      InetAddress group = InetAddress.getByName(ipAddress);
      // Create a byte array to store the messages
      byte[] msg = message.getBytes();
      // Form the datagram packet
      DatagramPacket packet = new DatagramPacket(msg, msg.length, group, port);
      // Send the datagram packet
      socket.send(packet);
      // Close the socket
      socket.close();
   }

   public static void main(String[] args) throws IOException {
      Scanner sc = new Scanner(System.in);
      int port = 4321;
      String ip = "230.0.0.0";
      String message = "";
      // Run the loop until the user types the exit message
      while(!(message.equals("OK"))) {
         System.out.println("Enter the message of type OK to exit");
         // Get the message to be sent from the user
         message = sc.nextLine();
         // Call the function
         sendMessage(message, ip, port);
      }
   }
}