package server;

import java.io.*;
import java.net.*;
import java.lang.*;

public class ThreadedServer {
	private static ServerSocket server;
	private static int port = 7777;
	private static Credentials credentials = new Credentials();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FloorBoss floorBoss = new FloorBoss();
		credentials.loadServer();
		
		server = new ServerSocket(port);
		
		
		
		while(true) {
			System.out.println("Waiting for client request");
			
			Socket socket = null; 
			
			try {
				socket = server.accept();
				System.out.println("A new client is connected: " + socket);
				
				ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
				
				
				System.out.println("Assigning new thread for this client.");
				
				Thread clientThread = new ServerThreads(socket, objectInput, objectOutput, floorBoss, credentials);
				System.out.println("Starting thread");
				
				clientThread.start();
				
				System.out.println("Thread started.");


			} catch (Exception e) {
				System.out.println("Unable to connect to client: " + socket);
				socket.close();
				e.printStackTrace();
			}
		}

	}

}



