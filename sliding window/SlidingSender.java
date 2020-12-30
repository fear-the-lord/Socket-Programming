// SENDER PROGRAM

// Import the necessary dependencies
import java.net.*;
import java.io.*;
import java.rmi.*;

public class SlidingSender {
    public static void main(String a[]) throws Exception {
        // Create an instance of the server socket class
        ServerSocket ser = new ServerSocket(10);
        Socket s = ser.accept();
        DataInputStream in = new DataInputStream(System.in);
        DataInputStream in1 = new DataInputStream(s.getInputStream());
        // Create the buffer area
        String sbuff[] = new String[8];
        PrintStream p;
        int sptr = 0, sws = 8, nf, ano, i;
        String ch;
        do {
            p = new PrintStream(s.getOutputStream());
            // Accept the number of frames to be sent. 1 in case of One-bit sliding window protocol
            System.out.print("Enter the no. of frames : ");
            nf = Integer.parseInt(in.readLine());
            p.println(nf);
            if (nf <= sws - 1) {
                // Enter the messages one by one
                System.out.println("Enter " + nf + " Messages to be send\n");
                for (i = 1; i <= nf; i++) {
                    // Store the messages in the buffer
                    sbuff[sptr] = in.readLine();
                    p.println(sbuff[sptr]);
                    sptr = ++sptr % 8;
                }
                sws -= nf;
                System.out.print("Acknowledgment received");
                // Receive the acknowledgement number from the receiver
                ano = Integer.parseInt(in1.readLine());
                System.out.println(" for " + ano + " frames!");
                sws += nf;
            } else {
                System.out.println("The no. of frames exceeds window size");
                break;
            }
            System.out.print("\nDo you wants to send some more frames(yes/no) : ");
            ch = in.readLine();
            p.println(ch);
        } while (ch.equals("yes"));
        s.close();
    }
}