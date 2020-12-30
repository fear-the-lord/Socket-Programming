    // CLIENT PROGRAM

    // Import all the necessary dependencies
    import java.lang.System;
    import java.net.*;
    import java.io.*;
    
    public class Client {
        // Create a Socket object
        static Socket connection;
    
        public static void main(String a[]) throws SocketException {
            try {
                // Create a receiving array that will receive the messages from the server
                int v[] = new int[10];
                int n = 0;
                InetAddress addr = InetAddress.getByName("Localhost");
                System.out.println(addr);
                // Connect to the port 8011
                connection = new Socket(addr, 8011);
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                DataInputStream in = new DataInputStream(connection.getInputStream());
                // Read the number of frames to be sent by the server
                int p = in.read();
                System.out.println("No of frame is:" + p);
    
                // Read all the frames sent by the server
                for (int i = 0; i < p; i++) {
                    v[i] = in.read();
                }

                // Mark the 6th frame as lost
                v[5] = -1;
                for (int i = 0; i < p; i++)
                {   
                    // Display all the received frames
                    System.out.println("Received frame is: " + v[i]);
                }
                for (int i = 0; i < p; i++)
                    if (v[i] == -1) {
                        // Ask the server to resend the lost frame
                        System.out.println("Request to retransmit packet no " + (i+1) + " again!!");
                        n = i;
                        out.write(n);
                        out.flush();
                    }
                    System.out.println();
                    v[n] = in.read();
                    System.out.println("Received frame is: " + v[n]);
                System.out.println("quiting");
            } catch (Exception e) {
                System.out.println("Thank you for transmitting! Your connection has been reset!");
            }
        }
    }