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
			reader = new BufferedReader(new
			InputStreamReader(client.getInputStream()));
			writer = new PrintWriter(new
			OutputStreamWriter(client.getOutputStream()));
			writer.println ("You are now connected to the Server.");
			writer.println("Type 'Server Exit' to disconnect!");
			writer.flush();
			for (;;) {
				// Read a line from the client
				String line = reader.readLine();
				// Send back ACK to the Client
				writer.println("Message Received!");
				writer.flush();
				System.out.println("Client says: " + line);
				if (line.equals("Server Exit")) {
				break;
			}
		}
		reader.close();
		writer.close();
		} finally {
			System.out.println ("Closing the connection");
			client.close ();
		}
	}
	static Socket accept (int port) throws IOException {
		System.out.println ("Starting on port " + port);
		ServerSocket server = new ServerSocket (port);
		System.out.println ("Waiting for Client");
		Socket client = server.accept ();
		System.out.println ("Accepted from " + client.getInetAddress ());
		server.close ();
		return client;
	}
}