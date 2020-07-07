package blackjackself;

import java.io.Serializable;

public class player implements Serializable{
	private String id; 
	private String command;
	private Hands hand = new Hands();
	private double money;
	
	boolean canContinue;

	public player(String id) {
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
}
