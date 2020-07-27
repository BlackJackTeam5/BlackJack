package main;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.util.*;


public class BlackJackThreadedClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{

		try {
			Scanner scan = new Scanner(System.in);
			InetAddress host = InetAddress.getLocalHost(); 
			
			Socket socket = new Socket(host.getHostName(), 7777);
			System.out.println("Hello user");
			
			ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
			
			
			while(true) {
				System.out.println(objectInput.readObject());
				String messageToSend = scan.nextLine();
				
				objectOutput.writeObject(messageToSend);
		
				if(messageToSend.contentEquals("exit")) {
					System.out.println("Closing this connection: " + socket);
					socket.close();
					System.out.println("Connection closed");
					break;
				}
			}
			
			scan.close();
			objectInput.close();
			objectOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
