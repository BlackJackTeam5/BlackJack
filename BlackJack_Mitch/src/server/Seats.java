package server;

public class Seats {
	public Players seatedPlayer;
	boolean isTaken = false;

	public Seats(Players player) {
		this.seatedPlayer = player;
		this.isTaken = true;
	}
	
	public Players getSeatedPlayer() {
		return seatedPlayer;
	}
	
	public void setSeatedPlayer(Players seatedPlayer) {
		this.seatedPlayer = seatedPlayer;
	}
	
	
	public boolean isTaken() {
		return isTaken;
	}
	
	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	
	

}
