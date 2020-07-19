package blackjackself;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlackJack {
	
	BlackJack(){
		
	}
/*
	private static ArrayList<Player> players;
	private static ArrayList<Player> info;
	
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	
    	players = new ArrayList<Player>();
    W
    	loadData();
    	
    	
        // Create a ServerSock on localhost:7777
    	try {
    		ServerSocket ss = new ServerSocket(2226);
    		ExecutorService pool = Executors.newFixedThreadPool(20);
    		while(true)
    		{
				System.out.println("ServerSocket awaiting connections...");
    			pool.execute(new BlackJack(ss.accept()));
    		}
    	}
    	finally {
    		System.exit(0);
    	}
    }
    
    private static class BlackJack implements Runnable{

    	private Socket socket;
    	
    	BlackJack(Socket socket){
    		this.socket = socket;
    	}
    	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
		        System.out.println("Connection from " + socket + "!");
		        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		        ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
		        
		        Player newPlayer = (Player) objectInputStream.readObject();
		        System.out.println(newPlayer.getID());

		        if(verifyLogin(newPlayer)) {
		        	players.add(newPlayer);
		        	newPlayer.verified = true;
		        	objectOutput.writeObject(newPlayer);
		        }
		      		        
		        //check for existing player, if valid and password checks out 
		        //update player class with old money value etc 
		        //send back to client

		        if(newPlayer.getCommand().equalsIgnoreCase("quit")) {
		        	players.remove(newPlayer);
		        	if(players.size()==0) {
		        		socket.close();
		        		System.exit(0);
		        	}
		        }
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				try {
					System.out.println("closing socket");
					socket.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally {
				
				}
			}
		}
    }
    
    
    public static boolean verifyLogin(Player player1) {
    	for(int i=0;i < info.size(); i++) {
    		if(info.get(i).getID().equalsIgnoreCase(player1.getID())) {
    			if(info.get(i).getPass().equalsIgnoreCase(player1.getPass())) {
    				return true;
    			}	
    			else {
    				return false;
    			}
    		}
    	}
    	player1.newUser = true;
    	return true;
    }

    public void runGame() {
    	if(players.size() == 0) {
    		System.exit(0);
    	}
    	else {
    		
    	}
    }
    
    public static void loadData() {
    	
 	}
 	*/
}