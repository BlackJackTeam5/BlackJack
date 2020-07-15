package blackjackself;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import blackjackself.Card.Rank;
import blackjackself.Card.Suit;

public class Card {
	
	private Rank rank;
	private Suit suit;
	public boolean isFaceUp;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		isFaceUp = false;
	}
	
	public String getSuit() {
		return suit.getSuit();
	}
	public int getRank() {
		return rank.getRank();
	}
	public String printRank() {
		return rank.getStringRank();
	}
	public void flipCard() {
		isFaceUp = !isFaceUp;
	}
	public String toString() {
		String str = "";
		if (isFaceUp) {
			str = this.rank.getStringRank() + " of " + this.suit.getSuit();
		}
		else {
			str = "Card is face down";
		}
			return str;
	}
	
	enum Suit {
		Diamnods("Diamonds"),
		Spades("Spades"),
		Hearts("Hearts"),
		Clubs("Clubs");
		
		private final String suitValue;
		
		Suit(final String suitValue){
			this.suitValue = suitValue;
		}
		
		public String getSuit() {
			return suitValue;
		}
	}
	
	enum Rank{
		Ace(1, "Ace"),
		Two(2, "Two"),
		Three(3, "Three"),
		Four(4, "Four"),
		Five(5, "Five"),
		Six(6, "Six"),
		Seven(7, "Seven"),
		Eight(8, "Eight"),
		Nine(9, "Nine"),
		Ten(10, "Ten"),
		Jack(10, "Jack"),
		Queen(10, "Queen"),
		King(10, "King");
		
		private final int rankValue;
		private final String rankString;
		
		Rank(final int rankValue, final String rankString){
			this.rankValue = rankValue;
			this.rankString = rankString;
		}
		
		public int getRank() {
			return rankValue;
		}
		public String getStringRank() {
			return rankString;
		}
	}
}
