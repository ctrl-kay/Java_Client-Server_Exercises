import java.net.*;
import java.io.*;


public class DateClient
{
	// the default port
	public static final int PORT = 6013;
	// this could be replaced with an IP address or IP name
	public static final String host = "localhost";
	public static final int SIZE = 1000;

	public static void main(String[] args) throws java.io.IOException {
		InputStream is = null;
		BufferedInputStream fromServer = null; // use bufferreader to read output from server
		Socket server = null;
		byte[] messageByte =  new byte[SIZE];
		boolean end = false;

		try {
			// create socket and connect to default port
			server = new Socket(host, PORT);

			// read the date and close the socket
			fromServer = new BufferedInputStream(is); 
			//^^create input stream to read input stream from server
			is = new DataInputStream(server.getInputStream());
			String line = "";
			while (!end)
			{
				int bytesRead = is.read(messageByte);
				line += new String(messageByte, 0, bytesRead);
				if(line.length() == SIZE)
				{
					end = true;
				}
				System.out.print("FROM SERVER: " + line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		finally {
			// let's close streams and sockets
			if (fromServer!= null)
				fromServer.close();
			if (server != null)
				server.close();
		}
	}
}
