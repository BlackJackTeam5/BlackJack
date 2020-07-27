package main;
import java.io.Serializable;

public class Player implements Serializable{
	private String id; 
	private String password; 
	private String command;
	private Hands hand = new Hands();
	private double money;
<<<<<<< Updated upstream
=======
	//private int tableNumber;
>>>>>>> Stashed changes
	
	boolean turn;	
	boolean canContinue;
	boolean verified;

	public Player(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getID() {
		return this.id;
	}
	
	public String getPass() {
		return this.password;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	public boolean getCont() {
		return this.hand.canContinue();
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public void verify(String password) {
		if(this.password.equalsIgnoreCase(password))
		{
			this.verified = true;
		}
		else {
			this.verified = false;
		}
	}
	
<<<<<<< Updated upstream
=======
	
	/* TABLE NUMBER WHEN WE GET THIS FAR
	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	*/

>>>>>>> Stashed changes
	public void setTurn(boolean value) {
		this.turn = value;
	}
}


/*
 * client hits deal, send message = "deal" 
 * player.command = deal
 * 
 */

