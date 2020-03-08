// A Java program for a Server 
import java.net.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*; 
  
public class IconThread extends Thread
{ 
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null;
    private static int PORT = 5000;
  
    // constructor for server and port 
    public IconThread() throws IOException 
    {
        super(); 
        // starts server and waits for a connection 
		server = new ServerSocket(PORT); 
        System.out.println("Server started"); 
    }
    //start method
    public void start(){
  
        System.out.println("Waiting for a client ..."); 
        try
        {
	        socket = server.accept(); 
	        System.out.println("Client accepted on port " + server.getLocalPort()); 
	  
	            // takes input from the client socket 
	        in = new DataInputStream(socket.getInputStream());
	        
	        //build image with input from client side
	        BufferedImage img = ImageIO.read(new File("bug" + in.readUTF() + ".gif"));
	        
	        //send the image back to the client with socket output steam
	        ImageIO.write(img, "gif", socket.getOutputStream());
	        
	        //close client socket
	        System.out.println("Closing Connection.");
	        socket.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
} 