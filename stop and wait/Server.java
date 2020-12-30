// SERVER PROGRAM 

// Import all the necessary dependencies
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String args[]) {
        String h = "Serverhost";
        int q = 5000;
        try {         
            ServerSocket ss2;
            // Create a new socket
            ss2 = new ServerSocket(8000);
            Socket s1 = ss2.accept();
            DataInputStream dd1 = new DataInputStream(s1.getInputStream());
            // Read the number of frames to be sent from the client
            Integer i1 = dd1.read();
            for(int i = 1; i <= i1; i++)
            {
                ServerSocket ss1;
                ss1 = new ServerSocket(9000 + i);
                Socket s = ss1.accept();
                DataInputStream dd = new DataInputStream(s.getInputStream());
                // Read all the frames sent, one by one
                String sss1 = dd.readUTF();
                System.out.println("Message from Client: " + sss1);
                System.out.println("Frame " + i + " received");
                DataOutputStream d1 = new DataOutputStream(s.getOutputStream());
                // Send an acknowledgement for the frame received to the client
                d1.write(i);
                System.out.println("ACK sent for " + i); 
            }
        }
        catch(Exception ex) {
            System.out.println("Error" + ex);
        }
    }
}