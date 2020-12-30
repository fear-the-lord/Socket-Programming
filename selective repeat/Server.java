// SERVER PROGRAM
    
// Import all the necessary dependencies
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server
{
	static ServerSocket Serversocket;
	static DataInputStream dis;
	static DataOutputStream dos;

	public static void main(String[] args) throws SocketException 
	{

		try 
		{
			// In this case we can enter the details manually without the user intervention
			int a[] = { 30, 40, 50, 60, 70, 80, 90, 100 };
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
			for (int i = 0; i < a.length; i++) 
			{
				dos.write(a[i]);
				dos.flush();
			}

			// Get the frame number(randomly generated) from the client that needs to be re-transmitted
			int k = dis.read();
			System.out.println("Resending frame number " + (k + 1));

			dos.write(a[k]);
			dos.flush();

		}
		catch (IOException e)
		{
			// Reset the connection in case of any exception
			System.out.println("Thank you for transmitting! Your connection has been reset!");
		}
		finally
		{
			try 
			{
				dis.close();
				dos.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}

		}
	}
}