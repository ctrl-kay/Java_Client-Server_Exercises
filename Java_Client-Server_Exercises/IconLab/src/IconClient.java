// A Java program for a Client 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class IconClient extends JFrame
{ 
	// initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null;
    
    // Set server and port variables
    private static final int PORT = 5000;
    private static InetAddress HOST = null;
    
    //GUI elements
    public JTextField textField;
    private JLabel label;
    private JButton button;
    
	//Method to establish a connection  
	public void connect(String input)
	{
    	try
        { 
            HOST = InetAddress.getByName("localhost"); 
    		socket = new Socket(HOST, PORT); //socket to server
            System.out.println("Client says: Connected to:" + HOST +"\n"); 
            
            //System.out.println("Please enter a number for the file: ");
            //Take input from terminal 
            //input  = new DataInputStream(System.in); 
  
            // send output to the server socket 
            out = new DataOutputStream(socket.getOutputStream()); 
            out.writeUTF(input);
            
            //Get image from server
            BufferedImage image = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
            //Write arguments: renderedimage,filetype, fileoutput
            ImageIO.write(image, "gif", new File("newbug" + input +".gif"));
            //Set image icon with the image from the server
            ImageIcon icon = new ImageIcon(image);
            
            //Display icon and message
            JLabel label = new JLabel( "Have A Good One!");
            label.setFont(new Font("Arial", Font.BOLD, 18));
            JOptionPane.showMessageDialog(null, label, "Display an Icon",3, icon);
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
	}
	
    
    //Run the GUI
    // constructor to put ip address and port 
    public IconClient() 
    { 
    	super("Cosc318 Lab2 - Kaylan Horne"); // title on dialog box
    	//Container for layout
    	Container container = getContentPane();
    	container.setBackground(Color.ORANGE);
    	container.setLayout(new FlowLayout( FlowLayout.CENTER));
    	


    	
    	
    	
    	//Create and add labe, textfield and button to the layout
    	label = new JLabel("Enter a number between 1 and 3: ");
    	container.add(label);
    	textField = new JTextField(12);
    	container.add(textField);
    	button = new JButton("Enter");
    	container.add(button);
    	
    	//Add event handler and listeners
    	ButtonHandler handler = new ButtonHandler();
    	textField.addActionListener(handler);
    	button.addActionListener(handler);
    	
    	//Set size and visibility of container
    	setSize(400,150);
    	setVisible(true);
    }
	  
    	//Handler class for events
    	final class ButtonHandler implements ActionListener
    	{
			@Override //method to handle events
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				String userInput = textField.getText();
				int input = Integer.parseInt(userInput);
				
				//JOptionPane is for a pop up message letting user know they have hit enter
				//JOptionPane.showMessageDialog( null, "You pressed: " + button.getText() );
				
				if(event.getSource() == textField || event.getSource() == button)
				{
					if(input < 1 || input > 3)
					{
						JOptionPane.showMessageDialog(null, "Oops! Please enter a value within 1 or 3.","Display an Icon", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,"You chose option " + textField.getText(),"Display an Icon", JOptionPane.INFORMATION_MESSAGE);
						//call on connect method
						connect(userInput);
					}
				}
			}		
    	}
    	
    public static void main(String args[]) 
    { 
  
        IconClient application = new IconClient(); //build the icon client app
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); //close program on clicking X 
    } 
} 