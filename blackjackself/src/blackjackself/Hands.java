package blackjackself;

import java.io.Serializable;
import java.util.ArrayList;

public class Hands implements Serializable{
	int total_cards;
	int total_sum;
	boolean has_ace;
	
	ArrayList<Card> hand = new ArrayList<Card>();
	
	public Hands() {
		total_cards =0;
		total_sum = 0;
		has_ace = false;
	}
	
	public void addCard(Card card) {//Card card) {
		hand.add(card);
		if(card.getValue() == 1) {
			has_ace = true;
		}
	}

	public boolean canContinue() {
		return calcTotal() <= 21;
	}
	
	public int calcTotal() {
		for(int i =0; i < total_cards; i++) {
			total_sum+= hand.get(i).faceValue();
		}
		if(has_ace && total_sum > 21) {
			return total_sum-10;
		}
		else {
			return total_sum;
		}
		
	}
}

