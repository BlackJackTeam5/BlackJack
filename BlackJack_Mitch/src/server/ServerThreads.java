package server;

import java.io.*;
import java.net.*;
import java.lang.*;

public class ServerThreads extends Thread{
	final ObjectInputStream objectInput;
	final ObjectOutputStream objectOutput;
	private Players player;
	final Socket clientSocket;
	final FloorBoss floorBoss;
	final Credentials credentials;
	
	public ServerThreads(Socket clientSocket, ObjectInputStream objectInput, ObjectOutputStream objectOutput, FloorBoss floorBoss, Credentials credentials) throws IOException {
		this.objectOutput = objectOutput;
		this.objectInput = objectInput;
		this.player = null;
		this.clientSocket = clientSocket;
		this.floorBoss = floorBoss;
		this.credentials = credentials;
		
	}
	
	public void run() {
		String messageReceived = null;
		String messageReturn = null;

		
		System.out.println("Thread running: " + clientSocket);
		
		try {
			objectOutput.writeObject("Enter login credentials");
			messageReceived = (String) objectInput.readObject();
			
			String[] attributes = new String[2];
			attributes = messageReceived.split("/");
			String login = attributes[0];
			String password = attributes[1];
			
			//VERIFY AND RETRIEVE ACCTBALANCE FROM CREDENTIALS
				//UPDATE CLIENT GUI
			this.player = credentials.retrievePlayer(login, password);
			objectOutput.writeObject(player.getAcctBalance());
			
			//messageReceived = (String) objectInput.readObject();
		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		//SEND PLAYER TO FLOORBOSS TO BE ASSIGNED A TABLE
		floorBoss.seatTable(player);
			//UPDATE PLAYER GUI WITH TABLE INFORMATION
		
		
		
		while(true) {
			try {
				//RETRIEVE COMMANDS FROM CLIENT
				//		SAMPLE THREAD ACTION
				 				
				
				objectOutput.writeObject("Welcome user. \nEnter 'login' to login or 'exit'");
				messageReceived = (String) objectInput.readObject();

				
				if(messageReceived.equals("exit")) {
					System.out.println("Client " + this.clientSocket + " sends exit");
					System.out.println("Closing connection.");
			   
					this.clientSocket.close();
					System.out.println("Connection closed");
					break;
			   }
			   
			   objectOutput.writeObject("Return: " + messageReceived);

			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}	
		}
		try {
			objectInput.close();
			objectOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
