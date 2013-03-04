package open42;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import open42.game.Game;

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
		testGame = new Game(4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testShuffleDrawAndReset() {
		assertEquals(testGame.players.size(), 4);

		ArrayList<Domino> dominos = testGame.shuffleDominos();
		assertEquals(dominos.size(), Game.DOMINO_COUNT);

		// Check that hands are empty
		for (Player p : testGame.players) {
			assertEquals(0, p.getHand().size());
		}

		testGame.drawHands();

		assertEquals(0, dominos.size());

		for (Player p : testGame.players) {
			assertEquals(7, p.getHand().size());
		}

		testGame.resetDominos();

		// Check that hands are empty
		for (Player p : testGame.players) {
			assertEquals(0, p.getHand().size());
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

		testGame.setBid(new Bid(36, 3));
		assertTrue(testGame.getBid().getBidPoints() == 36);
		assertTrue(testGame.getBid().getBidCondition() == BidCondition.Straight);

		testGame.setBid(new Bid(29, 6));
		assertEquals(testGame.getBid().getBidPoints(), 29);

		testGame.setBid(new Bid(168, 2));
		assertEquals(testGame.getBid().getBidPoints(), 168);

		testGame.setBid(new Bid(169, 0));
		assertEquals(testGame.getBid().getBidPoints(), 168);

		testGame.setBid(new Bid(83, 7));
		assertEquals(testGame.getBid().getBidPoints(), 42);
	}

}
