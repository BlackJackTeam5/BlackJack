

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
	
	public void addCard(Card card) 
	{
		hand.add(card);
		if(card.getValue() == 1) {
			has_ace = true;
		}
	}

	public boolean canContinue() {
		return calcTotal() <= 21;
	}
	
	public int calcTotal() {
		boolean used_ace = false;
		total_sum = 0;
		for(int i =0; i < hand.size(); i++) {
			total_sum += hand.get(i).getValue();
		
		}
		if(has_ace && total_sum < 12)
		{
			total_sum += 10;
		}
		return total_sum;
		
	
	
	}


}