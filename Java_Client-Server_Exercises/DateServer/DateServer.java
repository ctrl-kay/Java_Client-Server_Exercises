import java.net.*;
import java.io.*;


public class DateServer
{
	// the default port
	public static final int PORT = 6013;
 public static void main(String[] args) throws java.io.IOException {
	 
		Socket client = null; //set client socket to null
		ServerSocket sock = null; // server socket null
		byte [] bbuffer = new byte [1024]; //array of bytes created called bbuffer	
		String line; // string
		int numbytes = 0; //
		PrintWriter toClient = null; //object declared to send out a stream of data to client


		try {
		// create a server socket and bind it to default port
		sock = new ServerSocket(PORT); // create a server socket - PORT set to 6013 as a global variable

		
		while (true) {
			// await client connections
			client = sock.accept();  //listens until a connection is made
			toClient =  new PrintWriter(client.getOutputStream());
			// obtain the output stream and deliver the date
			java.util.Date theDate = new java.util.Date(); //snapshot of current date/time
			toClient.println(theDate.toString() + "\n"); //convert date object to string, write byte by byte to client
			toClient.flush();

		}
		} catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			// let's close streams and sockets
			if (toClient!= null)
				toClient.close();
			if (client != null)
				client.close();
			if (sock != null)
				sock.close();
		}
	}
}