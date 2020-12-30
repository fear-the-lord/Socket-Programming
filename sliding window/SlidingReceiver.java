// RECEIVER PROGRAM

// Import the necessary dependencies
import java.net.*;
import java.io.*;
class SlidingReceiver
{
	public static void main(String a[])throws Exception
	{
		// Connect to the sender
		Socket s = new Socket(InetAddress.getLocalHost(),10);
		DataInputStream in = new DataInputStream(s.getInputStream());
		PrintStream p = new PrintStream(s.getOutputStream());
		int i = 0, rptr = -1, nf, rws = 8;
		String rbuf[] = new String[8];
		String ch; 
		System.out.println();
		do
		{	
			// Receive the number of frames that the sender wants to send
			nf=Integer.parseInt(in.readLine());
			// Check if the number of frames is less than the buffer length
			if(nf <= rws-1)
			{
				for(i = 1; i <= nf; i++)
				{
					// Perform modulus to round the frame size to the buffer size
					rptr = ++rptr % 8;
					// Accept one frame at a time from the sender
					rbuf[rptr] = in.readLine();
					// Display the received frame
					System.out.println("The received Frame " + rptr +" is : "+ rbuf[rptr]);
				}
				// Reduce the buffer size by the current frame size
				rws -= nf;
				// After receiving all the frames, send an acknowledgement
				System.out.println("\nAcknowledgment sent!\n");
				p.println(rptr + 1); 
				rws += nf; 
			}
			else
				// If the frame size is greater than the buffer size, then the frame cannot be transmitted, and the process halts
				break;
			// Accept the choice of sending more frames from the client
			ch = in.readLine();
		}
		while(ch.equals("yes"));
	}
}