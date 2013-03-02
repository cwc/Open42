package open42;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import open42.Bid;
import open42.BidCondition;
import open42.Domino;
import open42.Game;

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
