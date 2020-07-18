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

import java.io.FileReader;
import java.io.FileWriter;

public class Server {
	private static ArrayList<Player> players; //current players queue-ing up for a game
											  //The order the players were pushed on will be the turn order in game.
	private static ArrayList<Player> info;	  //list of players saved into the database to be used for verifying Login Information
	
	private static Deck dealer;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	
    	players = new ArrayList<Player>(); //initialization
    	info = new ArrayList<Player>(); //initialization
		dealer = new Deck();
    	loadData();
		saveData();
    	
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
				while (true){
		        
					//Wait for client to send player object over outputStream and verify with info Object.
					Player newPlayer = (Player) objectInputStream.readObject();
					System.out.println(newPlayer.getCommand());

			
					//check for existing player, if valid and password checks out 
					//update player class with old money value etc send back to client
					if(newPlayer.getCommand().equalsIgnoreCase("login")) {
						System.out.println("IN LOGIN");
						if (verifyLogin(newPlayer)) {
							System.out.println("Verified user");
							players.add(newPlayer); //add to the queue of current players
							newPlayer.verified = true; //set player.verified to true to signal to client that 
													//password is correct
							System.out.println("writing to client");
							objectOutput.writeObject(newPlayer); //send back to client 
						}
						else {
							objectOutput.writeObject(newPlayer);
						}
					}
					
								
					

					//test code down here for verifying commands. 
					if(newPlayer.getCommand().equalsIgnoreCase("quit")) {
						players.remove(newPlayer);
						if(players.size()==0) {
							//socket.close();
							System.exit(0);
						}
					}
					else {
						waitLobby();
					}

					if(newPlayer.getCommand().equalsIgnoreCase("play")) {
						//String numPlayers = players.size();
						objectOutput.writeObject(newPlayer);
						objectOutput.writeObject(info); //send back to client 
					}

					if(newPlayer.getCommand().equalsIgnoreCase("deal")) {
						for(int i=0;i < info.size(); i++) {
							if(info.get(i).getID().equalsIgnoreCase(newPlayer.getID())){
								Card newCard = dealer.getCard();
								newPlayer.addCard(newCard);
								info.get(i).setHand(newPlayer.getHand());
								System.out.println("NEW CARD = " + newCard.getValue());

								objectOutput.writeObject(newPlayer);
							}

		        		}
		        	}
					
				}

			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				try {
					System.out.println("closing socket");
					//socket.close();
					//System.exit(0);
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
	    	Timer animationTimer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					try {
						objectOutput.writeObject(players);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	    	});
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
		try{
			File file = new File("../playerData");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				String[] parsedLine = line.split(" ");
				Player newPlayer = new Player(parsedLine[0], parsedLine[1]);
				newPlayer.setMoney(Double.parseDouble(parsedLine[2]));
				info.add(newPlayer);
			}
			fr.close();
		}
		catch(IOException e)  
		{  
			e.printStackTrace();  
		}  
	
		System.out.println("CREATED: " + info.size());
    	//create player 
    	//push to info
    	
    	//read line, .split(",")
    }
    
    public static void saveData() {
		try{
			File file = new File("../playerData2");
			FileWriter fw = new FileWriter(file);
			for (int i = 0; i < info.size(); i++) {
				fw.write(info.get(i).toString()+"\n");
			}
			fw.close();
		}
		catch(IOException e)  
		{  
			e.printStackTrace();  
		}  
		System.out.println("created the file");
    }
}