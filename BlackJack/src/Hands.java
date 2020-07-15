package blackjackself;

import java.util.ArrayList;

import blackjackself.Card.Rank;

public class Hands {
	protected ArrayList<Card> cards;
	
	public Hands() {
		cards = new ArrayList<Card>();
	}
	public void clear() {
		cards.clear();
	}
	public void add(Card card) {
		cards.add(card);
	}
	
	public String showHand() {
		boolean allFaceUp = true;
		String str = "";
		for(Card c: cards) {
			str += c.toString() + "\n";
			if(!c.isFaceUp) {
				allFaceUp = false;
			}
		}
		if(allFaceUp) {
			str += "total = " + handTotal() + "\n";
		}
		return str;
	}
	
	public void flipCards() {
		for(Card c: cards) {
			c.flipCard();
		}
	}
	
	public boolean give(Card card, Hands otherHand) {
		if (!cards.contains(card)) {
			return false;
		}
		else {
			cards.remove(card);
			otherHand.add(card);
			return true;
		}
	}
	
	public int handTotal() {
		int total = 0;
		boolean hasAce = false;
		for(int i = 0; i < cards.size(); i++) {
			total += cards.get(i).getRank();
			if(cards.get(i).printRank() == "Ace") {
				hasAce = true;
			}
			if(hasAce && total <= 11) {
				total += 10;
			}
		}
		return total;
	}
	
	public String toString() {
		String str = "";
		for(Card card: cards) {
			str += card.toString() + "\n";
		}
		return str;
	}

}

