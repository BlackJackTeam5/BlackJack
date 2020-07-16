package server;

import java.io.*;
import java.net.*;
import java.lang.*;

public class Server {
	private static ServerSocket server;
	private static int port = 5555;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		server = new ServerSocket(port);
		
		
		while(true) {
			System.out.println("Waiting for client request");
			
			Socket socket = server.accept();
			
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			String message = (String) objectInput.readObject();
			System.out.println("Message received: " + message);
		
			
			ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
			objectOutput.writeObject("Hi client " + message);
			
			objectInput.close();
			objectOutput.close();
			socket.close();

			if(message.equalsIgnoreCase("exit"))
				break;			

		}
		
		System.out.println("Shutting down server.");
		
		server.close();
	}

}
