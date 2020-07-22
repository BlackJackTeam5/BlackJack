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
import java.lang.management.PlatformLoggingMXBean;

public class Server {
	private static ArrayList<Player> players; //current players queue-ing up for a game
											  //The order the players were pushed on will be the turn order in game.
	private static ArrayList<Player> info;	  //list of players saved into the database to be used for verifying Login Information
	private static ArrayList<Player> loggedInPlayers;//List of logged in players to send back to client for gooey. 
	private static Deck dealer;
	
	private static ArrayList<Player> dealers;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	
		loggedInPlayers = new ArrayList<Player>();
    	players = new ArrayList<Player>(); //initialization
		info = new ArrayList<Player>(); //initialization
		dealers = new ArrayList<Player>(); //initialization
		dealer = new Deck();

		Player newDealer = new Player("Dealer", "");
		while (newDealer.getHand().calcTotal() < 21){
			Card newCard = dealer.getCard();
			newDealer.addCard(newCard);
		}
		// Card newCard1 = dealer.getCard();
		// newDealer.addCard(newCard);
		// newDealer.addCard(newCard1);
		dealers.add(newDealer);
		
		//System.out.println("Dealer's card values are " + newCard.getValue() + ", " + newCard1.getValue() + ".");

		loadData();
		
		saveData(); // needs to be placed in the right place
    	
		//Server initialization 
		ServerSocket ss = null;
    	try {
    		ss = new ServerSocket(6667);
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
			try {
				ss.close();
			}
			catch (Exception e){
			}
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
    		//Player dealer = new Player("dealer", "null"); 
    		//players.add(dealer);
    	}
    	
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				
		        System.out.println("Connection from " + socket + "!");
		        objectInputStream = new ObjectInputStream(socket.getInputStream());
		        objectOutput = new ObjectOutputStream(socket.getOutputStream());
				// while (true){
		        
					//Wait for client to send player object over outputStream and verify with info Object.
					Player playerObj = (Player) objectInputStream.readObject();
					System.out.println(playerObj.getCommand());

			
					//check for existing player, if valid and password checks out 
					//update player class with old money value etc send back to client
					if(playerObj.getCommand().equalsIgnoreCase("login")) {
						System.out.println("IN LOGIN");
						if (verifyLogin(playerObj)) {
							System.out.println("Verified user");
							players.add(playerObj); //add to the queue of current players
							findPlayer(playerObj).setLoggedIn(true);//finds player in info and sets login flag
							playerObj.setVerified(true); //set player.verified to true to signal to client that 
													//password is correct
							findPlayer(playerObj).setVerified(true);
							System.out.println("writing to client " + socket);
							objectOutput.writeObject(playerObj); //send back to client 
						}
						else {
							objectOutput.writeObject(playerObj);
						}
					}
					
					if(playerObj.getCommand().equalsIgnoreCase("updatelobby")) {
						//String numPlayers = players.size();
						
						objectOutput.writeObject(players); //send back to client 
					}

								
					

					//test code down here for verifying commands. 
					if(playerObj.getCommand().equalsIgnoreCase("quit")) {
						players.remove(playerObj);
						if(players.size()==0) {
							//socket.close();
							System.exit(0);
						}
					}
					else {
						//waitLobby();
					}

					if(playerObj.getCommand().equalsIgnoreCase("play")) {
						//String numPlayers = players.size();
						findPlayer(playerObj).setCommand("You can hit/stay/fold");
						// objectOutput.writeObject(playerObj);
						//updateList(players);
						objectOutput.writeObject(dealers.get(0));
						objectOutput.writeObject(players); //send back to client 
					}

					if(playerObj.getCommand().equalsIgnoreCase("UpdateGame")) {
						//String numPlayers = players.size();
						updateList(players);
						objectOutput.writeObject(players); //send back to client 
					}

					if(playerObj.getCommand().equalsIgnoreCase("deal")) {
						
						if (playerObj.getTurn()) {
							Card newCard = dealer.getCard();
							playerObj.addCard(newCard);
							findPlayer(playerObj).addCard(newCard);;
							System.out.println(playerObj.getID() + " turn = " + findPlayer(playerObj).getTurn());
							// playerObj.setTurn(playerObj.getCont());
							// findPlayer(playerObj).setTurn(playerObj.getCont());

							if (findPlayer(playerObj).getHand().calcTotal() > 21)
							{
								playerObj.setCommand("You lost unless the dealer busts!");
								findPlayer(playerObj).setCommand("You lost unless the dealer busts!");
								playerObj.setTurn(false);
								findPlayer(playerObj).setTurn(false);
								System.out.println(findPlayer(playerObj).printHand() + " " + findPlayer(playerObj).getTurn());
								updateList(players);
								objectOutput.writeObject(players);
								// updateList(players);
							}
							else 
							{
								playerObj.setCommand("You can hit/stay/fold");
								findPlayer(playerObj).setCommand("You can hit/stay/fold");
								updateList(players);
								objectOutput.writeObject(players);
							}
							// System.out.println(findPlayer(playerObj).printHand() + " " + findPlayer(playerObj).getTurn());
							// objectOutput.writeObject(players);
						}

						else
						{
							playerObj.setCommand("Busted/You decided to stand");
							findPlayer(playerObj).setCommand("Busted/You decided to stand");
							updateList(players);
							objectOutput.writeObject(playerObj);
						}

						// 	for(int i=0;i < info.size(); i++) {
						// 		if(info.get(i).getID().equalsIgnoreCase(playerObj.getID())){
						// 			Card newCard = dealer.getCard();
						// 			playerObj.addCard(newCard);
						// 			info.get(i).setHand(playerObj.getHand());
						// 			System.out.println("NEW CARD = " + newCard.getValue());
						// 			if (!playerObj.getCont()) {
						// 				playerObj.setCommand("You lost!");
						// 				playerObj.setTurn(false);
						// 				loggedInPlayers.get(i).setTurn(false);					
						// 			}
						// 			else {
						// 				playerObj.setCommand("You can hit/stay/fold!");
						// 			}
						// 			objectOutput.writeObject(playerObj);
						// 			break;
						// 		}
	
						// 	}
						// }
						// else {
						// 	playerObj.setCommand("You already lost/Decided to stand!");
						// 	objectOutput.writeObject(playerObj);
						// }
					}

					if(playerObj.getCommand().equalsIgnoreCase("stay")) {
						if (playerObj.getCont()) {
							for(int i=0;i < players.size(); i++) {
								if(players.get(i).getID().equalsIgnoreCase(playerObj.getID())){
									playerObj.setTurn(false);
									players.get(i).setTurn(false);
									findPlayer(playerObj).setTurn(false);
									playerObj.setCommand("You decided to stand");
									findPlayer(playerObj).setCommand("You decided to stand");
									objectOutput.writeObject(playerObj);
									break;
								}
	
							}
						}
						else {
							// playerObj.setTurn(playerObj.getCont());
							// findPlayer(playerObj).setTurn(playerObj.getCont());
							playerObj.setCommand("You decided to stand");
							findPlayer(playerObj).setCommand("You decided to stand");
							objectOutput.writeObject(playerObj);
						}
					}

					if (playerObj.getCommand().equalsIgnoreCase("result")) {
						if (dealers.get(0).getHand().calcTotal() > 21) {
							dealers.get(0).setCommand("YOU WON or broke even if you went over 21");
						}
						else if (playerObj.getHand().calcTotal() == 21) {
							dealers.get(0).setCommand("YOU WON");
						}
						else if (playerObj.getHand().calcTotal() <= 21) {
							if (playerObj.getHand().calcTotal() < dealers.get(0).getHand().calcTotal()) {
								dealers.get(0).setCommand("YOU LOST");
							}
							else {
								dealers.get(0).setCommand("YOU WIN");
							}
						}

						else {
							dealers.get(0).setCommand("YOU LOST");
						}
						objectOutput.writeObject(dealers);
					}
					
					
				// }

			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				try {
					System.out.println("closing socket");
					socket.close();
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
	// 	public static void waitLobby() {
	//     	//Establish timer to send players across socket to update GUI with 
	// 		//current players in lobby.
	// 		while(true) {
	// 			Timer updateClient = new Timer(1000, new ActionListener() {
	// 				@Override
	// 				public void actionPerformed(ActionEvent arg0) {
	// 					// TODO Auto-generated method stub
	// 					System.out.println("Sending player list to Client");
	// 					try {
	// 						objectOutput.writeObject(players);
	// 						players = (ArrayList<Player>)objectInputStream.readObject();
							
	// 						for(int i =0 ; i < players.size(); i++) {
	// 							if(players.get(i).getCommand().equalsIgnoreCase("Play")) {
	// 								runGame();
	// 							}
	// 						}
	// 					} catch (IOException e) {
	// 						// TODO Auto-generated catch block
	// 						e.printStackTrace();
	// 					} catch (ClassNotFoundException e) {
	// 						// TODO Auto-generated catch block
	// 						e.printStackTrace();
	// 					}
	// 				}
	// 	    	});
	// 			updateClient.start();
	// 		}
	//     }
	    
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
			File file = new File("playerData");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null) {
				String[] parsedLine = line.split(" ");
				Player playerObj = new Player(parsedLine[0], parsedLine[1]);
				playerObj.setMoney(Double.parseDouble(parsedLine[2]));
				info.add(playerObj);
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
			File file = new File("playerData");
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

	public static Player findPlayer(Player searchPlayer)
	{
		for(int i = 0; i < info.size(); i++)
		{
			if (searchPlayer.getID().equalsIgnoreCase(info.get(i).getID()))
			{
				return info.get(i);
			}
		}
		return (new Player());
	}

	public static void updateList(ArrayList<Player> l1)
	{
		//info is 3 big
		//player is 2
		
		for (int i = 0; i < info.size(); i++)
		{
			for(int j = 0; j < l1.size(); j++)
			{
				if(info.get(i).getID().equalsIgnoreCase(l1.get(j).getID()))
				{  
					l1.get(j).setCommand(info.get(i).getCommand());
                    l1.get(j).setHand(info.get(i).getHand());
					l1.get(j).setMoney(info.get(i).getMoney());
					l1.get(j).setTurn(info.get(i).getTurn());
					break;
				}
			}
		}
	}
	//info is just meant to hold what's in playerData.txt
	//on server exit, we just update info with players new money value 
}