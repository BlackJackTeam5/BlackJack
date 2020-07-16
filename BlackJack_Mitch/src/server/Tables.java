package server;

public class Tables {
	private Dealer dealer;
	private Deck deck;
	private int tableNumber;
	private int numPlayers;
	private Players[] seats;
	boolean isFull = false;
	boolean hasPlayers = false;
	
	
	//private Game game;
	
	
	public Tables(int tableNumber) {
		dealer = new Dealer();
		deck = new Deck();
		seats = new Players[5];
		this.tableNumber = tableNumber;
		this.numPlayers = 0;
	}
	
	public void addPlayer(Players player) {
		this.seats[numPlayers] = player;
		this.numPlayers++;
		this.hasPlayers = true;
		if(numPlayers == 5)
			isFull = true;
	}
	
	
	public void startGame() {
		while(hasPlayers) {	//run through game logic
			//THE DEAL
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < numPlayers; j++) {
					seats[j].hand.addCard(deck.getCard());
				}
				dealer.hand.addCard(deck.getCard());
				
				//UPDATE TABLES GUI
				
				
			}
			
			//PLAYING TURNS
			for(int i = 0; i < numPlayers; i++) {
				while(seats[i].hand.canContinue()) {
					//ASK THROUGH GUI WHETHER THEY WOULD LIKE TO STAND/STAY
				}
			}
			
			
		}
		
	}

	
	
	

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	

}
