// CLIENT PROGRAM

// Import all the necessary dependencies
import java.io.*;
import java.net.*;

public class Client { 
	public static final int DEFAULT_PORT = 6789;
	public static void usage() {
		System.out.println("Usage: java C [<port>]");
		System.exit(0);
	}
	public static void main(String[] args) {
		int port = DEFAULT_PORT;
		Socket s = null;
		// Parse the port specification
		if ((args.length != 0) && (args.length != 1)) usage();
		if (args.length == 0) port = DEFAULT_PORT;
		else {
			try {
				port = Integer.parseInt(args[0]);
			}
			catch(NumberFormatException e) {
				usage();
			}
		}
		try {
			BufferedReader reader;
			PrintWriter writer;
			// Create a socket to communicate to the specified host and port
			s = new Socket("localhost", port);
			// Create streams for reading and writing
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			// Tell the client that the connection is successful
			System.out.println("Connected to " + s.getInetAddress() + ":" + s.getPort());
			String line;
			// Write a line to the server
			writer.println("Hello, Server");
			writer.flush();
			// Read the response (a line) from the server
			line = reader.readLine();
			// Write the line to console
			System.out.println("Server says: " + line);
			reader.close();
			writer.close();
		}
		catch (IOException e) {
			System.err.println(e);
		}
		// Closing the socket
		finally {
			try {
				if (s != null) s.close();
			}
			catch (IOException e2) { 
				e2.printStackTrace();
			}
		}
	}
}