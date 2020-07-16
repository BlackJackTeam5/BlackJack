

import java.io.Serializable;

public class Player implements Serializable{
	private String id; 
	private String password; 
	private String command;
	private Hands hand = new Hands();
	private double money;
	
	boolean turn;	
	boolean canContinue;
	boolean verified;

	public Player(String id, String password) {
		this.id = id;
		this.password = password;
		this.command = "";
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
	
	public String getCommand() {
		return this.command;
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
	
	public void setTurn(boolean value) {
		this.turn = value;
	}
}


/*
 * client hits deal, send message = "deal" 
 * player.command = deal
 * 
 */
