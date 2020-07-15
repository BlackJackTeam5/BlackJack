package blackjackself;

import java.util.Collections;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.Random;

import blackjackself.Card.Rank;
import blackjackself.Card.Suit;

public class Deck extends Hands {
	Random rand = new Random();
	
	public void populate() {
		for(Suit suit: Suit.values()) {
			for(Rank rank: Rank.values()) {
				Card card = new Card(rank, suit);
				this.add(card);
			}
		}
	}
	
	public void shuffle() {
		for(int i = cards.size()-1; i> 0; i--) {
			 int pick = rand.nextInt(i);
			 Card randCard = cards.get(pick);
			 Card lastCard = cards.get(i);
			 cards.set(i, randCard);
			 cards.set(pick, lastCard);
		}
	}
	
	public void deal(Hands[] hands, int perHand) {
		for(int i=0; i<perHand; i++) {
			for(int j=0; j<hands.length; j++) {
				this.give(cards.get(0), hands[j]);
			}
		}
	}
	public void deal(Hands hand, int perHand) {
		for(int i=0; i<perHand; i++) {
			this.give(cards.get(0), hand);
		}
	}
	public void flipCard(Card c) {
		c.flipCard();
	}
}