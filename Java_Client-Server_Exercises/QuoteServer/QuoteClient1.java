//QuoteClient.java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class QuoteClient1 {

    private static InetAddress host;
    public static final int PORT = 4445;

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
             System.out.println("Usage: java QuoteClient <hostname>");
             return;
        }
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("Host not found!");
            System.exit(1);
        }

        Socket server = null;
        try
        {
            server = new Socket(host, PORT);
            Scanner input = new Scanner(server.getInputStream());
            PrintWriter output = New PrintWriter(server.getOutputStream);
            Scanner num = new Scanner(System.in);
            System.out.println("Enter the number of quotes you want:");
            int a = Integer.parseInt(num.nextLine());
            output.println("Number of quotes: " + num);
        }



            // get a datagram socket
        DatagramSocket dsocket = new DatagramSocket();

            // send request
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName(args[0]);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        dsocket.send(packet);

            // get response
        packet = new DatagramPacket(buf, buf.length);
        dsocket.receive(packet);

		    // display response
        String received = new String(packet.getData());
        System.out.println("Quote of the Moment: " + received);

        dsocket.close();
    }
}
