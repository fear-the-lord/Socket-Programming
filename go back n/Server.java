    // SERVER PROGRAM
    
    // Import all the necessary dependencies
    import java.io.DataInputStream;
    import java.io.DataOutputStream;
    import java.io.IOException;
    import java.net.ServerSocket;
    import java.net.Socket;
    import java.net.SocketException;
    import java.util.*;
   
    public class Server {
        static ServerSocket Serversocket;
        static DataInputStream dis;
        static DataOutputStream dos;
   
        public static void main(String[] args) throws SocketException {
            Scanner sc = new Scanner(System.in);
   
        try {

            // Enter details of all the frames to be sent, from the user
            int a[] = new int[10];
            System.out.println("Enter " + a.length + " values one by one----> ");
            for(int i = 0; i < a.length; i++) {
                System.out.print("Enter Message " + (i + 1) + " :");
                a[i] = sc.nextInt();
            }

            // Connect to the port 8011
            Serversocket = new ServerSocket(8011);
            System.out.println("Waiting for connection");
            // Wait for the client to establish it's connection
            Socket client = Serversocket.accept();
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());
            System.out.println("The number of packets sent is:" + a.length);
            int y = a.length;
            dos.write(y);
            dos.flush();
            
            // Send the data to the client
            for (int i = 0; i < a.length; i++) {
                dos.write(a[i]);
                dos.flush();
            }
            
            // Get the frame number from the client that needs to be re-transmitted
            int k = dis.read();
   
            dos.write(a[k]);
            dos.flush();
   
            } catch (IOException e) {
                // Reset the connection in case of any exception
                System.out.println("Thank you for transmitting! Your connection has been reset!");
            } finally {
                try {
                    dis.close();
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }