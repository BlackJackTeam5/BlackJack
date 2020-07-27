package server;

import java.io.*;
import java.util.Arrays;

public class Credentials {
	private int numPlayers;
	private Players[] players;
	
	private String serverFile = "BlackJackCredentials.txt";
	
	public Credentials() {
		numPlayers = 0;
		players = new Players[5];
	}
	
<<<<<<< Updated upstream
	public void addOrModify(String loginID, String password, String balance) {
		int acctBalance;
		
=======
	
	public Players retrievePlayer(String loginID, String password) {
		Players player = null;
		
		for(int i = 0; i < numPlayers; i++) {
			if(loginID.compareTo(players[i].getLoginID()) == 0) {
				if(password.compareTo(players[i].getPassword()) == 0) {
					player = players[i];
				}
			}
		}
		return player;
	}


	public void addOrModify(String loginID, String password, String balance) {
/*		
>>>>>>> Stashed changes
		try {
			acctBalance = Integer.parseInt(balance);
		}
		catch (NumberFormatException e) {
<<<<<<< Updated upstream
			System.out.println("Invalid aactBalance given.");
			return;
		}
		
		for(int i = 0; i < numPlayers; i++) {
			if(loginID.compareTo(players[i].getLoginID()) == 0) {
				players[i].setAcctBalance(acctBalance);
=======
			System.out.println("Invalid acctBalance given.");
			return;
		}
*/
		
		for(int i = 0; i < numPlayers; i++) {
			if(loginID.compareTo(players[i].getLoginID()) == 0) {
				players[i].setAcctBalance(balance);
>>>>>>> Stashed changes
				//players[i].setPassword(password);
				return;
			}
		}
		
<<<<<<< Updated upstream
		Players newPlayer = new Players(loginID, password, acctBalance);
=======
		Players newPlayer = new Players(loginID, password, balance);
>>>>>>> Stashed changes
		
		if(numPlayers == players.length) {
			players = Arrays.copyOf(players, players.length * 2);
		}
		
		for(int i = 0; i < numPlayers; i++) {
			if(loginID.compareTo(players[i].getLoginID()) < 0) {
				for(int j = numPlayers; j > i; j--) {
					players[j] = players[j - i];
				}
				players[i] = newPlayer;
				numPlayers++;
				return;
			}
		}
		
		players[numPlayers] = newPlayer;
		numPlayers++;
		return;
	}
	
<<<<<<< Updated upstream
=======
	public String getAcctBalance(String loginID, String password) {
		//ITERATE THROUGH ARRAY AND FIND EXACT MATCH
		String acctBalance = "";
		for(int i = 0; i < numPlayers; i++) {
			if(loginID.compareTo(players[i].getLoginID()) == 0) {
				if(password.compareTo(players[i].getPassword()) == 0) {
					acctBalance = players[i].getAcctBalance();
				}
			}
		}
		return acctBalance;
	}
	
>>>>>>> Stashed changes
	public void loadServer() {
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(serverFile));
			String fileInput;
			try {
				while((fileInput = buffReader.readLine()) != null) {
					String[] attributes = new String[4];
					
					if(fileInput.split("/").length != 3) {
						System.out.println("Filew has an entry with invalid attributes.");
						break;
					}
					attributes = fileInput.split("/");
					addOrModify(attributes[0], attributes[1], attributes[2]);
					
				}
			} catch (IOException e) {
				System.out.println("IOException thrown.");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("FilewNotFoundException thrwon.");
			e.printStackTrace();
			File newFile = new File(serverFile);
			try {
				if(newFile.createNewFile()) {
					System.out.println("File created: " + newFile.getName());
				}
			} catch (IOException e1) {
				System.out.println("File creation error.");
				e1.printStackTrace();
			}
		}
	}
	
	public void save() {
		String outputString = "";
		for(int i = 0; i < numPlayers - 1; i++) {
			outputString += players[i].toString() + "\n";
		}
		
		outputString += players[numPlayers - 1].toString();
		
		try {
			FileWriter fileWriter = new FileWriter(serverFile);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			writer.write(outputString);
			writer.close();
		} catch (IOException e) {
			System.out.println("File Write error.");
			e.printStackTrace();
		}
	}
<<<<<<< Updated upstream
=======
	
	
	
	
	
	public int getNumPlayers() {
		return numPlayers;
	}



	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

>>>>>>> Stashed changes

}
