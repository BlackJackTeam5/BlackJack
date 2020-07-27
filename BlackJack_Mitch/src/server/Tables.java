package server;

public class Tables {
	private Dealer dealer;
	private Deck deck;
	private int tableNumber;
<<<<<<< Updated upstream
	private int numPlayers;
	private Players[] seats;
=======
	private int numPlayers = 0;
	private Seats[] seats;
>>>>>>> Stashed changes
	boolean isFull = false;
	boolean hasPlayers = false;
	
	
	//private Game game;
	
	
<<<<<<< Updated upstream
	public Tables(int tableNumber) {
		dealer = new Dealer();
		deck = new Deck();
		seats = new Players[5];
		this.tableNumber = tableNumber;
		this.numPlayers = 0;
	}
	
	public void addPlayer(Players player) {
		this.seats[numPlayers] = player;
=======
	public Tables(int tableNumber, Players player) {
		dealer = new Dealer();
		deck = new Deck();
		seats = new Seats[5];
		this.tableNumber = tableNumber;
		addPlayer(player);
	}
	
	public void addPlayer(Players player) {
		this.seats[numPlayers] = new Seats(player);
>>>>>>> Stashed changes
		this.numPlayers++;
		this.hasPlayers = true;
		if(numPlayers == 5)
			isFull = true;
	}
	
<<<<<<< Updated upstream
	
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
			
			
=======
	public void removePlayer(Players player) {
		for(int i = 0; i < numPlayers; i++) {
			if(seats[i].seatedPlayer.getLoginID() == player.getLoginID()) {
				seats[i] = null;
				
				//FILL IN GAPS IN TABLE
				for(int prev = i; prev < numPlayers; prev++) {
					int next = prev + 1;
					seats[prev] = seats[next];
				}
				numPlayers--;
				break;
			}
		}
		
		if(numPlayers == 0)
			hasPlayers = false;
	}
	
	
	public void startGame() {
		while(true) {	//run through game logic
			while(hasPlayers) {
				//THE DEAL
				for(int i = 0; i < 2; i++) {
					for(int j = 0; j < numPlayers; j++) {
						seats[j].seatedPlayer.hand.addCard(deck.getCard());
					}
					dealer.hand.addCard(deck.getCard());
					
					//UPDATE TABLES GUI
						// SHOW ONLY ONE CARD FOR THE DEALER, SHOW BOTH FOR PLAYERS
	
				}
				
				//DEALER CHECKS FOR BLACKJACK
					/*
					 * IF(DEALER SHOWS FACE/ACE)
					 * 	IF(BLACKJACK)
					 * 		ROUND OVER, PLAYERS LOSE
					 * 		
					 */
				
				
				//PLAYING TURNS
				for(int i = 0; i < numPlayers; i++) {
					while(seats[i].seatedPlayer.hand.canContinue()) {
						//ASK THROUGH GUI WHETHER THEY WOULD LIKE TO STAND/STAY
					}
				}
				
				//UPDATE GUI WITH BOTH DEALER CARDS SHOWING
				
				//DEALER'S TURN
				dealer.dealerPlays(deck);
				
				//FINAL CHECKS
				/*
				 * COMPARE DEALERS CARD WITH PLAYERS HANDS
				 * 
				 */
				for(int i = 0; i < numPlayers; i++) {
					if(seats[i].isTaken) {
						if(seats[i].seatedPlayer.hand.calcTotal() < dealer.hand.calcTotal()) {
							//UPDATE CLIENT GUI WITH LOSS SCREEN //AND TAKE BET
						}
						else if(seats[i].seatedPlayer.hand.calcTotal() == dealer.hand.calcTotal()) {
							//UPDATE CLIENT GUI WITH DRAW SCREEN //AND PUSH BET
						}
						//ELSE
						//UPDATE CLIENT GUI WITH WIN SCREEN //AND MATCH BET
					}
				}
			
			}
>>>>>>> Stashed changes
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
