package testSuite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjackself.Card;

class CardTest {

	@Test
	void testGetSuit() {
		Card test = new Card(5,0);
		assertEquals(0, test.getSuit());
		//fail("Not yet implemented");
	}

	@Test
	void testGetValue() {
		Card test = new Card(5,0);
		assertEquals(5, test.getValue());
		//fail("Not yet implemented");
	}

	@Test
	void testFaceValue() {
		Card test = new Card(12,0);
		assertEquals(10, test.faceValue());
		//fail("Not yet implemented");
	}

}
