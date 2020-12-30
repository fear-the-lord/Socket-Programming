// SERVER PROGRAM 

// Import all the necessary dependencies
import java.net.*;
import java.io.*;

public class Server {
	public final static int DEFAULT_PORT = 6789;
	public static void main (String args[]) throws IOException {
		Socket client;
		if (args.length != 1)
			client = accept (DEFAULT_PORT);
		else
			client = accept (Integer.parseInt (args[0]));
		try {
			PrintWriter writer;
			BufferedReader reader;
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			// Read a line
			String line = reader.readLine();
			System.out.println("Client says: " + line);
			// Write a line
			writer.println ("You have connected to the Server.");
			writer.flush();
			reader.close();
			writer.close();
		} finally { // Closing down the connection
			System.out.println ("Closing the Connection");
			client.close ();
		}
	}
	static Socket accept (int port) throws IOException {
		System.out.println ("Starting on port " + port);
		ServerSocket server = new ServerSocket (port);
		System.out.println ("Waiting for Client");
		Socket client = server.accept ();
		System.out.println ("Accepted from " + client.getInetAddress());
		server.close ();
		return client;
	}
}