package server;

public class Dealer {
	public Hands hand;
	private Deck deck;
	public Dealer() {
		hand = new Hands();
	}
	
	public void dealerPlays(Deck deck) {
		//STAND AT 16, HITS ON SOFT 16
		int handTotal = hand.calcTotal(); 
		while(hand.canContinue()) {
		
		}
	}

}
