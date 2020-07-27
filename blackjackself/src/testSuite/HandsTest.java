package testSuite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjackself.Card;
import blackjackself.Deck;
import blackjackself.Hands;

class HandsTest {

	@Test
	void testAddCard() {
		Deck test = new Deck();
		Hands testHand = new Hands();
		Card testCard = test.getCard();
		testHand.addCard(testCard);
		
		assertTrue(testHand.hand.contains(testCard));
		
		//fail("Not yet implemented");
	}

	@Test
	void testCanContinue() {
		Hands testHand = new Hands();
		Card testCard1 = new Card(10, 2);
		Card testCard2 = new Card(5, 2);
		Card testCard3 = new Card(8, 2);
		
		testHand.addCard(testCard1);
		testHand.addCard(testCard2);
		testHand.addCard(testCard3);
		
		assertFalse(testHand.canContinue());
		
		//fail("Not yet implemented");
	}

	@Test
	void testCalcTotal() {
		Hands testHand = new Hands();
		Card testCard1 = new Card(10, 2);
		Card testCard2 = new Card(5, 2);
		Card testCard3 = new Card(8, 2);
		
		testHand.addCard(testCard1);
		testHand.addCard(testCard2);
		testHand.addCard(testCard3);
		
		assertEquals(23, testHand.calcTotal());
		//fail("Not yet implemented");
	}

}
