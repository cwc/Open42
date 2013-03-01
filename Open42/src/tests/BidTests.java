package tests;

import static org.junit.Assert.assertEquals;
import open42lib.Bid;
import open42lib.Domino;
import open42lib.Game;

import org.junit.Before;
import org.junit.Test;

public class BidTests {
	private Game testGame;

	@Before
	public void setUp() throws Exception {
		testGame = new Game();
	}

	@Test
	public final void testEmptyBid() {
		assertEquals(Bid.PASS, testGame.makeBid(testGame.hands.get(0))
				.getBidPoints());
	}

	@Test
	public final void bidTest1() {
		testGame.hands.get(0).add(new Domino(6, 6));
		testGame.hands.get(0).add(new Domino(6, 5));
		testGame.hands.get(0).add(new Domino(6, 4));
		testGame.hands.get(0).add(new Domino(6, 3));
		testGame.hands.get(0).add(new Domino(6, 2));
		testGame.hands.get(0).add(new Domino(6, 1));
		testGame.hands.get(0).add(new Domino(6, 0));

		assertEquals(84, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
	}

	@Test
	public final void bidTest2() {
		testGame.hands.get(0).add(new Domino(4, 4));
		testGame.hands.get(0).add(new Domino(4, 2));
		testGame.hands.get(0).add(new Domino(4, 1));
		testGame.hands.get(0).add(new Domino(4, 0));
		testGame.hands.get(0).add(new Domino(5, 5));
		testGame.hands.get(0).add(new Domino(3, 3));
		testGame.hands.get(0).add(new Domino(2, 2));

		assertEquals(31, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
	}

	@Test
	public final void bidTest3() {
		testGame.hands.get(0).add(new Domino(4, 4));
		testGame.hands.get(0).add(new Domino(4, 2));
		testGame.hands.get(0).add(new Domino(4, 1));
		testGame.hands.get(0).add(new Domino(4, 0));
		testGame.hands.get(0).add(new Domino(1, 1));
		testGame.hands.get(0).add(new Domino(3, 3));
		testGame.hands.get(0).add(new Domino(2, 2));

		assertEquals(31, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
	}

	@Test
	public final void bidTest4() {
		// [4/2, 6/2, 3/0, 5/2, 2/2, 2/1, 6/6] - 42

		testGame.hands.get(0).add(new Domino(4, 2));
		testGame.hands.get(0).add(new Domino(6, 2));
		testGame.hands.get(0).add(new Domino(3, 0));
		testGame.hands.get(0).add(new Domino(5, 2));
		testGame.hands.get(0).add(new Domino(2, 2));
		testGame.hands.get(0).add(new Domino(2, 1));
		testGame.hands.get(0).add(new Domino(6, 6));

		assertEquals(42, testGame.makeBid(testGame.hands.get(0)).getBidPoints());
	}

	@Test
	public final void bidTest5() {
		// [6/6, 0/0, 5/3, 6/2, 5/4, 3/1, 1/1] - pass

		testGame.hands.get(0).add(new Domino(6, 6));
		testGame.hands.get(0).add(new Domino(0, 0));
		testGame.hands.get(0).add(new Domino(5, 3));
		testGame.hands.get(0).add(new Domino(6, 2));
		testGame.hands.get(0).add(new Domino(5, 4));
		testGame.hands.get(0).add(new Domino(3, 1));
		testGame.hands.get(0).add(new Domino(1, 1));

		assertEquals(Bid.PASS, testGame.makeBid(testGame.hands.get(0))
				.getBidPoints());
	}

	@Test
	public final void bidTest6() {
		// [2/2, 5/3, 5/5, 4/1, 2/0, 3/3, 1/1] - TODO: 30, doubles as trumps

		testGame.hands.get(0).add(new Domino(2, 2));
		testGame.hands.get(0).add(new Domino(5, 3));
		testGame.hands.get(0).add(new Domino(5, 5));
		testGame.hands.get(0).add(new Domino(4, 1));
		testGame.hands.get(0).add(new Domino(2, 0));
		testGame.hands.get(0).add(new Domino(3, 3));
		testGame.hands.get(0).add(new Domino(1, 1));

		assertEquals(Bid.PASS, testGame.makeBid(testGame.hands.get(0))
				.getBidPoints());
	}
}
