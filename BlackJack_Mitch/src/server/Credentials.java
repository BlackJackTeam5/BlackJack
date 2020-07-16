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
	
	public void addOrModify(String loginID, String password, String balance) {
		int acctBalance;
		
		try {
			acctBalance = Integer.parseInt(balance);
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid aactBalance given.");
			return;
		}
		
		for(int i = 0; i < numPlayers; i++) {
			if(loginID.compareTo(players[i].getLoginID()) == 0) {
				players[i].setAcctBalance(acctBalance);
				//players[i].setPassword(password);
				return;
			}
		}
		
		Players newPlayer = new Players(loginID, password, acctBalance);
		
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

}
