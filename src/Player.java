

import java.io.Serializable;

public class Player implements Serializable{
	// private static final long serialVersionUID = 4L;
	private String id; 
	private String password; 
	private String command;
	private Hands hand;
	private double money;
	
	boolean turn; // indicates if the player has gone over 21/decided to stay
	boolean canContinue;
	boolean verified;

	public Player(){
		this.id = "";
		this.password = "";
		this.command = "";
	}

	public Player(String id, String password) {
		this.id = id;
		this.password = password;
		this.command = "";
		this.hand = new Hands();
		this.turn = true;
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
	
	public double getMoney() {
		return this.money;
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

	@Override
	public String toString() {
		return (this.id + " " + this.password + " " + this.money);
	}

	public void addCard(Card newCard) {
		hand.addCard(newCard);
	}

	public Hands getHand() {
		return this.hand;
	}

	public void setHand(Hands hand) {
		this.hand = hand;
	}

	public boolean getTurn() {
		return this.turn;
	}
// 	@Override
//   	public String toString() {
//     	return id;
//   	}
 }


/*
 * client hits deal, send message = "deal" 
 * player.command = deal
 * 
 */
