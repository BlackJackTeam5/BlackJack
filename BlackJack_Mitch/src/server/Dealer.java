package server;

public class Dealer {
	public Hands hand;
	private Deck deck;
	public Dealer() {
		hand = new Hands();
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
	}
	
	public void dealerPlays(Deck deck) {
		//STAND AT 16, HITS ON SOFT 16
		int handTotal = hand.calcTotal(); 
<<<<<<< Updated upstream
		while(hand.canContinue()) {
		
		}
=======
		
		while(hand.canContinue()) {
			if(handTotal <= 16 && hand.has_ace) {
				//DEALER MUST HIT
				hand.addCard(deck.getCard());
				//UPDATE CLIENT GUI
			}
			else if(handTotal < 16) 
				hand.addCard(deck.getCard());
				//UPDATE CLIENT GUI
			else
				break;
		}
		//UPDATE CLIENT GUI 
>>>>>>> Stashed changes
	}

}
