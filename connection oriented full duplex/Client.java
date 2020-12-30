// CLIENT PROGRAM  

// Import the necessary dependencies
import java.io.*;
import java.net.*;
public class Client {
	public static final int DEFAULT_PORT = 6789;
	public static void usage() {
		System.out.println("Usage: java C1 [<port>]");
		System.exit(0);
	}
	public static void main(String[] args) {
		int port = DEFAULT_PORT;
		Socket s = null;
		int end = 0;
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
			PrintWriter writer;
			BufferedReader reader;
			BufferedReader kbd;
			// Create a socket to communicate to the specified host and port
			s = new Socket("localhost", port);
			// Create streams for reading and writing
			reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			OutputStream sout = s.getOutputStream();
			writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			// Create a stream for reading from keyboard
			kbd = new BufferedReader(new InputStreamReader(System.in));
			// Tell the client that the connection has been established with the server
			System.out.println("Connected to " + s.getInetAddress() + ":" + s.getPort());
			String line;
			// Read the first response (a line) from the server
			line = reader.readLine();
			// Write the line to console
			System.out.println(line);
			while (true) {
				// Print a prompt
				System.out.print("> ");
				System.out.flush();
				// Read a line from console, check for EOF
				line = kbd.readLine();
				if (line.equals("Server Exit")) 
					end = 1;
				// Send it to the server
				writer.println(line);
				writer.flush();
				// Read a line from the server
				line = reader.readLine();
				// Check if connection is closed, i.e., EOF
				if (line == null) {
					System.out.println("Connection closed by server.");
				break;
				}
				if (end == 1) 
					break;
				// Write the line to console
				System.out.println("Server says: " + line);
			}
			reader.close();
			writer.close();
		}
		catch (IOException e) {
			System.err.println(e);
		}
		// Close the socket
		finally {
			try {
				if (s != null) 
					s.close();
			}
			catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}