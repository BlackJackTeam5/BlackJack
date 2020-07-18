import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static ArrayList<Player> players; //current players queue-ing up for a game
											  //The order the players were pushed on will be the turn order in game.
	private static ArrayList<Player> info;	  //list of players saved into the database to be used for verifying Login Information
	
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	
    	players = new ArrayList<Player>(); //initialization
    	info = new ArrayList<Player>(); //initialization
    	loadData();
    	
    	//Server initialization 
    	try {
    		ServerSocket ss = new ServerSocket(6667);
    		ExecutorService pool = Executors.newFixedThreadPool(20); //allows for a number of threads
    		while(true)
    		{
    			
    			/*
    			Socket s = ss.accept();
    			objectInputStream objectInput = new ObjectIntpuStream(s.getInputStream());
    			try{
    				Object item = objectInput.read();
    			}
    			catch{
    				
    			}
    			
    			if item.getClass() == Player{
    				if(verifyLogin(item)){
    					waitLobby();
    				}
    			}
    			else{
    				runGame();
    			}
    			
    			*/
				System.out.println("ServerSocket awaiting connections...");
    			pool.execute(new BlackJack(ss.accept())); //executes the BlackJack class sending the socket that was obtained via ServerSocket
    		}
    	}
    	finally {
    		System.exit(0);
    	}
    }
    
    private static class BlackJack implements Runnable{

    	private Socket socket;
    	private static ObjectInputStream objectInputStream; 
    	private static ObjectOutputStream objectOutput;
    	
    	BlackJack(Socket socket){
    		this.socket = socket;
    		
    		//Do not know what turn order the dealer goes, add dealer to players object 
    		Player dealer = new Player("dealer", "null"); 
    		players.add(dealer);
    	}
    	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
		        System.out.println("Connection from " + socket + "!");
		        objectInputStream = new ObjectInputStream(socket.getInputStream());
		        objectOutput = new ObjectOutputStream(socket.getOutputStream());
		        
		        //Wait for client to send player object over outputStream and verify with info Object.
		        Player newPlayer = (Player) objectInputStream.readObject();
		        System.out.println(newPlayer.getID());
		        System.out.println(newPlayer.getCommand());

		  
		        //check for existing player, if valid and password checks out 
		        //update player class with old money value etc send back to client
		        if(verifyLogin(newPlayer)) {
		        	System.out.println("Verified user");
		        	players.add(newPlayer); //add to the queue of current players
		        	newPlayer.verified = true; //set player.verified to true to signal to client that 
		        							   //password is correct
		        	System.out.print("writing to client");
		        	objectOutput.writeObject(newPlayer); //send back to client 
		        }
		      		        
		        

		        //test code down here for verifying commands. 
		        if(newPlayer.getCommand().equalsIgnoreCase("quit")) {
		        	players.remove(newPlayer);
		        	if(players.size()==0) {
		        		socket.close();
		        		System.exit(0);
		        	}
		        }
		        else {
		        	System.out.println("Going to waiting Lobby");
		        	waitLobby();
		        }
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				try {
					System.out.println("closing socket");
					socket.close();
					System.exit(0);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally {
				
				}
			}
		}
		
		//function to verify player login and update info
		public static boolean verifyLogin(Player player1) {
		    	if(info.size()>0) {
		    		for(int i=0;i < info.size(); i++) {
		        		if(info.get(i).getID().equalsIgnoreCase(player1.getID())) {
		        			if(info.get(i).getPass().equalsIgnoreCase(player1.getPass())) {
		        				System.out.println("Returning true");
		        				player1.setMoney(info.get(i).getMoney());
		        				return true;
		        			}	
		        			else {
		        				System.out.println("Returning flase");
		        				return false;
		        			}
		        		}
		        	}
		    	}
		    	
		    	//if program has iterated through all of the player info in info Object, it is new user 
		    	//automatically add to info Object and return true;
		    	System.out.println("New user returning true");
		    	info.add(player1);
		    	return true;
		    }
		
		//code in progress
		public static void waitLobby() {
	    	//Establish timer to send players across socket to update GUI with 
			//current players in lobby.
			while(true) {
				Timer updateClient = new Timer(1000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						System.out.println("Sending player list to Client");
						try {
							objectOutput.writeObject(players);
							players = (ArrayList<Player>)objectInputStream.readObject();
							
							for(int i =0 ; i < players.size(); i++) {
								if(players.get(i).getCommand().equalsIgnoreCase("Play")) {
									runGame();
								}
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		    	});
				updateClient.start();
			}
	    }
	    
    }

    public static void runGame() {
    	System.out.println("hi game starts now");
    	printPlayers();
    	while(true) {
        	if(players.size() == 0) {
        		System.exit(0);
        	}
        	else {
        		
        	}
    	}
    }
    
    //for debugging purposes
    public static void printPlayers() {
    	for(int i =0; i < players.size();i++) {
    		System.out.println(players.get(i).getID() + " " + players.get(i).getPass() + " " + players.get(i).getCommand());
    	}
    }
    
    public static void loadData() {
    	//read file
    	//create player 
    	//push to info
    	
    	//read line, .split(",")
    }
    
    public static void saveData() {
    	
    }
}