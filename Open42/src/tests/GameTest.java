package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		assertEquals(-1, testGame.makeBid(testGame.hands[0]).getBidPoints());
		
		testGame.hands[0][0] = new Domino(6, 6);
		testGame.hands[0][1] = new Domino(6, 5);
		testGame.hands[0][2] = new Domino(6, 4);
		testGame.hands[0][3] = new Domino(6, 3);
		testGame.hands[0][4] = new Domino(6, 2);
		testGame.hands[0][5] = new Domino(6, 1);
		testGame.hands[0][6] = new Domino(6, 0);
		
		assertEquals(84, testGame.makeBid(testGame.hands[0]).getBidPoints());
		
		testGame.hands[0][0] = new Domino(4, 4);
		testGame.hands[0][1] = new Domino(4, 2);
		testGame.hands[0][2] = new Domino(4, 1);
		testGame.hands[0][3] = new Domino(4, 0);
		testGame.hands[0][4] = new Domino(5, 5);
		testGame.hands[0][5] = new Domino(3, 3);
		testGame.hands[0][6] = new Domino(2, 2);
		
		assertEquals(32, testGame.makeBid(testGame.hands[0]).getBidPoints());
		
		testGame.hands[0][0] = new Domino(4, 4);
		testGame.hands[0][1] = new Domino(4, 2);
		testGame.hands[0][2] = new Domino(4, 1);
		testGame.hands[0][3] = new Domino(4, 0);
		testGame.hands[0][4] = new Domino(1, 1);
		testGame.hands[0][5] = new Domino(3, 3);
		testGame.hands[0][6] = new Domino(2, 2);
		
		assertEquals(31, testGame.makeBid(testGame.hands[0]).getBidPoints());
		
		testGame.hands[0][0] = new Domino(0, 2);
		testGame.hands[0][1] = new Domino(0, 3);
		testGame.hands[0][2] = new Domino(0, 5);
		testGame.hands[0][3] = new Domino(5, 3);
		testGame.hands[0][4] = new Domino(3, 2);
		testGame.hands[0][5] = new Domino(3, 6);
		testGame.hands[0][6] = new Domino(6, 1);
		
		assertEquals(84, testGame.makeBid(testGame.hands[0]).getBidPoints());
		assertEquals(BidCondition.LowDoublesHigh, testGame.makeBid(testGame.hands[0]).getBidCondition());
	}

	@Test
	public final void testShuffleDrawAndReset() {
		assertEquals(testGame.hands.length, 4);
		assertEquals(testGame.hands[0].length, 7);

		ArrayList<Domino> dominos = testGame.shuffleDominos();
		assertEquals(dominos.size(), Game.DOMINO_COUNT);
		
		for (int i = 0; i < testGame.hands.length; i++) {
			for (int j = 0; j < testGame.hands[i].length; j++) {
				assertEquals(testGame.hands[i][j], null);
			}
		}
		
		testGame.drawHands();
		
		assertEquals(dominos.size(), 0);
		
		for (int i = 0; i < testGame.hands.length; i++) {
			for (int j = 0; j < testGame.hands[i].length; j++) {
				assertTrue(testGame.hands[i][j] instanceof Domino);
			}
		}
		
		testGame.resetDominos();
		
		for (int i = 0; i < testGame.hands.length; i++) {
			for (int j = 0; j < testGame.hands[i].length; j++) {
				assertEquals(testGame.hands[i][j], null);
			}
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
