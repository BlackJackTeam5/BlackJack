package blackjackself;

import blackjackself.Card.Rank;
import blackjackself.Card.Suit;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//need to add flipCard() to Card class
		//deck
		Deck d1 = new Deck();
		d1.populate();
		d1.shuffle();
		
		Hands h1, h2, h3, dealer;
		h1 = new Hands();
		h2 = new Hands();
		h3 = new Hands();
		dealer = new Hands();
		Hands[] hands = {h1, h2,h3};
		
		d1.deal(hands, 2);
		d1.deal(dealer, 2);
		
		for(int i=0; i<hands.length; i++) {
			hands[i].flipCards();
			System.out.println(hands[i].showHand());
		}
		
		dealer.cards.get(0).flipCard();
		//dealer.cards.get(1).flipCard();
		System.out.println("\nDealers cards: \n" + dealer.showHand());
	}

}
