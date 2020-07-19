import java.util.ArrayList;

public class Hands {
	int total_cards;
	int total_sum;
	boolean has_face;
	
	ArrayList<Card> hand = new ArrayList<Card>();
	
	public Hands() {
		total_cards =0;
		total_sum = 0;
		has_face = false;
	}
	
	public addCard() {//Card card) {
		
	}

	public boolean canContinue() {
		return calcTotal() <= 21;
	}
	
	public int calcTotal() {
		for(int i =0; i < hand.size(); i++) {
			total_sum+= Card.getValue();
		}
	}
}

