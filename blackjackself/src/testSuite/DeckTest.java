package testSuite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjackself.Card;
import blackjackself.Deck;
import blackjackself.Hands;

class DeckTest {

	@Test
	void testReshuffle() {
		//Hands hand1 = new Hands();
		//Hands hand2 = new Hands();
		Deck testDeck = new Deck();
		Deck testDeck2 = new Deck();
		testDeck2.reshuffle();
	/*
		
		hand1.addCard(testDeck.getCard());
		hand2.addCard(testDeck2.getCard());
		hand1.addCard(testDeck.getCard());
		hand2.addCard(testDeck2.getCard());
		
		System.out.println(hand1.calcTotal());
		System.out.println(hand2.calcTotal());
		
	*/
		assertNotEquals(testDeck.getDeck(), testDeck2.getDeck());
	}

}
