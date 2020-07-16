package server;

public class Players {
	private String loginID;
	private String password;
	private int acctBalance;
	public Hands hand = new Hands();
	
	public Players(String loginID, String password, int acctBalance) {
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
	public int getAcctBalance() {
		return acctBalance;
	}
	public void setAcctBalance(int acctBalance) {
		this.acctBalance = acctBalance;
	}

	public Hands getHand() {
		return hand;
	}

	public void setHand(Hands hand) {
		this.hand = hand;
	}
	

}
