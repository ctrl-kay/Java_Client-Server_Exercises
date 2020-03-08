// QuoteServerThread.java
// - Using UDP with DatagramSocket

import java.io.*;
import java.net.*;
import java.util.*;


public class QuoteServerThread extends Thread {

    protected DatagramSocket dsocket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    final DataInputStream fromClient = new DataInputStream()
    private static ServerSocket server;    

    public QuoteServerThread() throws IOException {
	this("QuoteServerThread");
    }

    public QuoteServerThread(String name) throws IOException {
        super(name);
        dsocket = new DatagramSocket(4445);

        try {
            in = new BufferedReader(new FileReader("one-liners.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }
    }

    public void run() {

        server = new ServerSocket(4445);
        Socket client =  null;

        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];
                client = server.accept();

                Scanner input = new Scanner(client.getInputStream());
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                int n = input.nextInt();



                    // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                dsocket.receive(packet);

                    // figure out response
                String dString = null;
                if (in == null)
                    dString = new Date().toString();
                else
                    dString = getNextQuote();
                buf = dString.getBytes();

		    // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                dsocket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
		moreQuotes = false;
            }
        }
        dsocket.close();
    }

    protected String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
		moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }
}
