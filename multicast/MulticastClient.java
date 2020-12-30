// CLIENT PROGRAM

// Import all the necessary dependencies
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClient implements Runnable {

      public static void main(String[] args) {
         // Creating a new thread
         Thread t = new Thread(new MulticastClient());
         t.start();
      }

      public void receiveMessage(String ip, int port) throws IOException {
         // Create a new buffer array
         int port1 = 4321;
         String ip1 = "230.0.0.0";
         byte[] buffer = new byte[1024];
         MulticastSocket socket=new MulticastSocket(port1);
         InetAddress group=InetAddress.getByName(ip1);
         socket.joinGroup(group);
         while(true){
            // Wait for the message from the server
            System.out.println("Waiting for multicast message...");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            // Receive the datagram packet from the server
            socket.receive(packet);
            // Extract the message from the datagram packet
            String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
            // Display the message
            System.out.println("[Multicast UDP message received]>> "+msg);
            // Check if the message is an exit message
            if("OK".equals(msg)) {
               // Break the connection
               System.out.println("No more message. Exiting : "+msg);
               break;
            }
         }
         socket.leaveGroup(group);
         socket.close();
      }

      @Override
      public void run(){
      try {
         // Call the function
         receiveMessage("230.0.0.0", 4321);
      }catch(IOException ex){
         ex.printStackTrace();
      }
   }
}