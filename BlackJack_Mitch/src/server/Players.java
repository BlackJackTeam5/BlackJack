package server;

<<<<<<< Updated upstream
public class Players {
	private String loginID;
	private String password;
	private int acctBalance;
	public Hands hand = new Hands();
	
	public Players(String loginID, String password, int acctBalance) {
=======
import java.io.*;
import java.net.*;
import java.lang.*;


public class Players implements Serializable{
	private String loginID;
	private String password;
	private String acctBalance;
	public Hands hand = new Hands();
	
	public Players(String loginID, String password, String acctBalance) {
>>>>>>> Stashed changes
		this.loginID = loginID;
		this.password = password;
		this.acctBalance = acctBalance;
	}
	
	public String toString() {
		return this.getLoginID() + "/" + this.getPassword() + "/" + this.getAcctBalance();
	}
	
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
<<<<<<< Updated upstream
	public int getAcctBalance() {
		return acctBalance;
	}
	public void setAcctBalance(int acctBalance) {
=======
	public String getAcctBalance() {
		return acctBalance;
	}
	public void setAcctBalance(String acctBalance) {
>>>>>>> Stashed changes
		this.acctBalance = acctBalance;
	}

	public Hands getHand() {
		return hand;
	}

	public void setHand(Hands hand) {
		this.hand = hand;
	}
	

}
