package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import open42lib.Bid;
import open42lib.BidCondition;
import open42lib.Domino;
import open42lib.Game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {
	Game testGame;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testGame = new Game();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public final void testMakeBid() {
		assertEquals(-1, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
		
		testGame.hands.get(0).add(new Domino(6, 6));
		testGame.hands.get(0).add(new Domino(6, 5));
		testGame.hands.get(0).add(new Domino(6, 4));
		testGame.hands.get(0).add(new Domino(6, 3));
		testGame.hands.get(0).add(new Domino(6, 2));
		testGame.hands.get(0).add(new Domino(6, 1));
		testGame.hands.get(0).add(new Domino(6, 0));
		
		assertEquals(84, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
		
		testGame = new Game();
		
		testGame.hands.get(0).add(new Domino(4, 4));
		testGame.hands.get(0).add(new Domino(4, 2));
		testGame.hands.get(0).add(new Domino(4, 1));
		testGame.hands.get(0).add(new Domino(4, 0));
		testGame.hands.get(0).add(new Domino(5, 5));
		testGame.hands.get(0).add(new Domino(3, 3));
		testGame.hands.get(0).add(new Domino(2, 2));
		
		assertEquals(32, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
		
		testGame = new Game();
		
		testGame.hands.get(0).add(new Domino(4, 4));
		testGame.hands.get(0).add(new Domino(4, 2));
		testGame.hands.get(0).add(new Domino(4, 1));
		testGame.hands.get(0).add(new Domino(4, 0));
		testGame.hands.get(0).add(new Domino(1, 1));
		testGame.hands.get(0).add(new Domino(3, 3));
		testGame.hands.get(0).add(new Domino(2, 2));
		
		assertEquals(31, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
		
		// [4/2, 6/2, 3/0, 5/2, 2/2, 2/1, 6/6] - 42
		
		testGame = new Game();
		
		testGame.hands.get(0).add(new Domino(4, 2));
		testGame.hands.get(0).add(new Domino(6, 2));
		testGame.hands.get(0).add(new Domino(3, 0));
		testGame.hands.get(0).add(new Domino(5, 2));
		testGame.hands.get(0).add(new Domino(2, 2));
		testGame.hands.get(0).add(new Domino(2, 1));
		testGame.hands.get(0).add(new Domino(6, 6));
		
		assertEquals(42, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
		
		testGame = new Game();
		
		testGame.hands.get(0).add(new Domino(0, 2));
		testGame.hands.get(0).add(new Domino(0, 3));
		testGame.hands.get(0).add(new Domino(0, 5));
		testGame.hands.get(0).add(new Domino(5, 3));
		testGame.hands.get(0).add(new Domino(3, 2));
		testGame.hands.get(0).add(new Domino(2, 1));
		testGame.hands.get(0).add(new Domino(6, 1));
		
		assertEquals(42, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
		assertEquals(BidCondition.LowDoublesHigh, testGame.makeBid(testGame.hands.get(0)).getBidCondition());
	}

	@Test
	public final void testShuffleDrawAndReset() {
		assertEquals(testGame.hands.size(), 4);

		ArrayList<Domino> dominos = testGame.shuffleDominos();
		assertEquals(dominos.size(), Game.DOMINO_COUNT);

		// Check that hands are empty
		for (List<Domino> hand : testGame.hands) {
			assertEquals(0, hand.size());
		}
		
		testGame.drawHands();
		
		assertEquals(0, dominos.size());
		
		for (List<Domino> hand : testGame.hands) {
			assertEquals(7, hand.size());
		}
		
		testGame.resetDominos();
		
		// Check that hands are empty
		for (List<Domino> hand : testGame.hands) {
			assertEquals(0, hand.size());
		}
	}

	@Test
	public final void testSetTrump() {
		assertEquals(testGame.getTrump(), -1);
		
		testGame.setTrump(3);
		assertEquals(testGame.getTrump(), 3);
		
		testGame.setTrump(-3);
		assertEquals(testGame.getTrump(), Domino.MIN_PIPS);
	
		testGame.setTrump(22);
		assertEquals(testGame.getTrump(), Domino.MAX_PIPS);
	}

	@Test
	public final void testSetBid() {
		assertEquals(testGame.getBid().getBidPoints(), -1);
	
		testGame.setBid(new Bid(36));
		assertTrue(testGame.getBid().getBidPoints() == 36);
		assertTrue(testGame.getBid().getBidCondition() == BidCondition.Straight);
		
		testGame.setBid(new Bid(29));
		assertEquals(testGame.getBid().getBidPoints(), 29);
	
		testGame.setBid(new Bid(168));
		assertEquals(testGame.getBid().getBidPoints(), 168);
		
		testGame.setBid(new Bid(169));
		assertEquals(testGame.getBid().getBidPoints(), 168);
		
		testGame.setBid(new Bid(83));
		assertEquals(testGame.getBid().getBidPoints(), 42);
	}

}
