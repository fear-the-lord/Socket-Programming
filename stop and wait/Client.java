// CLIENT PROGRAM 

// Import all the necessary dependencies
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        int p = 9000, q = 8000;
        String h = "localhost";
        try
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter number of frames : ");
            // Accept the number of frames to be sent from the user
            int number = sc.nextInt();
            if(number == 0)
                System.out.println("No frame is sent");
            else {           
                Socket s2;
                // Create a new socket with a given port address and IP
                s2 = new Socket(h,q);
                DataOutputStream d1 = new DataOutputStream(s2.getOutputStream());
                d1.write(number);
            }
            String str1;
            for (int i = 1; i <= number; i++)
            {                 
                System.out.print("Enter message : ");
                // Accept the message from the user
                String name = sc.next();
                System.out.println("Frame " + i +" is sent"); 
                Socket s1;
                s1 = new Socket(h, p + i);
                DataOutputStream d = new DataOutputStream(s1.getOutputStream());
                // Send the frame to the server
                d.writeUTF(name);
                DataInputStream dd = new DataInputStream(s1.getInputStream());
                // Receive the acknowledgement from the server
                Integer sss1 = dd.read();
                System.out.println("Ack for :" + sss1 + " is  received");
            }
        }
        catch(Exception ex) {
                    System.out.println("ERROR :" + ex);
        }
    } 
}