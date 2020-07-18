

import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	/*
	 public static void main(String[] args) throws IOException, ClassNotFoundException {
	    	InetAddress ip = InetAddress.getLocalHost();
			Socket socket = new Socket(ip.getHostName(), 2222);
			
			ClientGUI gui = new ClientGUI();

	        // Connect to the ServerSocket at host:port

	        // Output stream socket.
	        OutputStream outputStream = socket.getOutputStream();

	        // Create object output stream from the output stream to send an object through it
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

	        // List of Message objects
	        Player newPlayer = new Player("Example", "HI");
	        newPlayer.setCommand("quit");
	        System.out.println("Sending Player Objects");
	        objectOutputStream.writeObject(newPlayer);
	        
	        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
	        newPlayer = (Player)objectInput.readObject();
	        
	        System.out.println(newPlayer.verified);

	        
	        
	        System.out.println("Closing socket");
	        socket.close();
	    }
	    */
}
