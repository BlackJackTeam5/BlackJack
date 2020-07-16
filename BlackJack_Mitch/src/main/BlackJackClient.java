package main;

import java.io.*;
import java.net.*;
import java.lang.*;

public class BlackJackClient {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream objectOutput = null;
		ObjectInputStream objectInput = null;
		
		for(int i = 0; i < 5; i++) {
			socket = new Socket(host.getHostName(), 5555);
			
			objectOutput = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Sending request to Socket Server.");
			
			if(i == 4)
				objectOutput.writeObject("exit");
			else
				objectOutput.writeObject("" + i);
			
			objectInput = new ObjectInputStream(socket.getInputStream());
			String message = (String) objectInput.readObject();
			System.out.println("Message: " + message);
			
			objectInput.close();
			objectOutput.close();
			Thread.sleep(100);;
			
		}

	}

}
