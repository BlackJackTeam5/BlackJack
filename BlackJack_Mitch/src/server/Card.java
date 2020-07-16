package server;



public class Card {
	
	public final String suit; 
	public final int value; 
	/*
	 values:
	1:  "Ace"
	2: 	"2"
	3:  "3"
	4:  "4"
	5: 	"5"
	6: 	"6"
	7: 	"7"
	8: 	"8"
	9: 	"9"
	10:	"10"
	11:	"Jack"
	12:	"Queen"
	13:	"King"
	
	suits:
	0: spades
	1: hearts
	2: diamonds
	3: clubs
	 */
	
	public Card(int value, String suit) {
		this.suit = suit; 
		this.value = value;
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public int faceValue() {
		if (this.value >= 11){
			return 10;
		}
		else {
			return this.value;
		}
		
	}
	
}