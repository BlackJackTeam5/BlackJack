package testSuite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import blackjackself.Card;
import blackjackself.Hands;
import blackjackself.Player;

class PlayerTest {

	@Test
	void testPlayerStringString() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		assertEquals(testPlayer.getID(), "Mitchangoes");
		assertEquals(testPlayer.getPass(), "Password1");
		//fail("Not yet implemented");
	}

	@Test
	void testGetID() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		assertEquals(testPlayer.getID(), "Mitchangoes");
	//fail("Not yet implemented");
	}

	@Test
	void testGetPass() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		assertEquals(testPlayer.getPass(), "Password1");
		//fail("Not yet implemented");
	}

	@Test
	void testSetCommand() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		
		testPlayer.setCommand("HIT");

		assertEquals("HIT", testPlayer.getCommand());
		//fail("Not yet implemented");
	}

	@Test
	void testGetCommand() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		
		assertEquals("", testPlayer.getCommand());
		
		testPlayer.setCommand("HIT");
		
		assertEquals("HIT", testPlayer.getCommand());
		
		//fail("Not yet implemented");
	}

	@Test
	void testGetCont() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		Card testCard = new Card(10, 2);
		
		testPlayer.addCard(testCard);
		testPlayer.addCard(testCard);
		
		assertTrue(testPlayer.getCont());
		
		testPlayer.addCard(testCard);
		
		assertFalse(testPlayer.getCont());
		
		
		//fail("Not yet implemented");
	}

	@Test
	void testSetMoney() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setMoney(500);
		testPlayer.setMoney(1000.00);
		
		assertEquals(1000.00, testPlayer.getMoney());
		
		//fail("Not yet implemented");
	}

	@Test
	void testGetMoney() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setMoney(500.00);
		double testMoney = testPlayer.getMoney();
		
		assertEquals(500.00, testMoney);
		
		//fail("Not yet implemented");
	}

	@Test
	void testVerify() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		assertFalse(testPlayer.getVerified());
		
		testPlayer.verify("Password1");
		
		assertTrue(testPlayer.getVerified());
		
		//fail("Not yet implemented");
	}

	@Test
	void testSetTurn() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setTurn(true);
		
		assertTrue(testPlayer.getTurn());
		
		testPlayer.setTurn(false);
		assertFalse(testPlayer.getTurn());
		
		//fail("Not yet implemented");
	}

	@Test
	void testSetVerified() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setVerified(false);
		
		assertFalse(testPlayer.getVerified());
		
		testPlayer.setVerified(true);
		
		assertTrue(testPlayer.getVerified());
		
		//fail("Not yet implemented");
	}

	@Test
	void testToString() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		
		assertEquals("Mitchangoes Password1 0.0", testPlayer.toString());
		
		//fail("Not yet implemented");
	}

	@Test
	void testAddCard() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		Card testCard = new Card(5, 2);
		Hands testHand = new Hands();
		
		testPlayer.addCard(testCard);
		testPlayer.addCard(testCard);
		testPlayer.addCard(testCard);
		testPlayer.addCard(testCard);
		testPlayer.addCard(testCard);
		
		assertFalse(testPlayer.getCont());
		
		//fail("Not yet implemented");
	}

	@Test
	void testGetHand() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		Card testCard = new Card(5, 2);
		Hands testHand = new Hands();
		
		testHand.addCard(testCard);
		testPlayer.addCard(testCard);
		
		//fail("Not yet implemented");
	}

	@Test
	void testSetHand() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		Card testCard = new Card(5, 2);
		Hands testHand = new Hands();
		
		testPlayer.setHand(testHand);
		
		assertEquals(testHand, testPlayer.getHand());
		
		//fail("Not yet implemented");
	}

	@Test
	void testGetTurn() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setTurn(true);
		assertTrue(testPlayer.getTurn());
		testPlayer.setTurn(false);
		assertFalse(testPlayer.getTurn());
		
		//fail("Not yet implemented");
	}

	@Test
	void testGetLoggedIn() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setLoggedIn(false);
		assertFalse(testPlayer.getLoggedIn());
		
		testPlayer.setLoggedIn(true);
		assertTrue(testPlayer.getLoggedIn());
		
		//fail("Not yet implemented");
	}

	@Test
	void testSetLoggedIn() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setLoggedIn(false);
		assertFalse(testPlayer.getLoggedIn());
		
		testPlayer.setLoggedIn(true);
		assertTrue(testPlayer.getLoggedIn());
		
		//fail("Not yet implemented");
	}

	@Test
	void testGetVerified() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		testPlayer.setVerified(false);
		
		assertFalse(testPlayer.getVerified());
		
		testPlayer.setVerified(true);
		
		assertTrue(testPlayer.getVerified());
		
		//fail("Not yet implemented");
	}

	@Test
	void testPrintHand() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		Card testCard = new Card(5, 2);
		testPlayer.addCard(testCard);
		testPlayer.setCommand("HIT");
		
		assertEquals("Mitchangoes:  5D | HIT", testPlayer.printHand());
		
		//fail("Not yet implemented");
	}

	@Test
	void testDealerPrintHand() {
		Player testPlayer = new Player("Mitchangoes", "Password1");
		Card testCard = new Card(5, 2);
		testPlayer.addCard(testCard);
		
		assertEquals("Mitchangoes: 5D", testPlayer.dealerPrintHand());
		
		//fail("Not yet implemented");
	}

}
