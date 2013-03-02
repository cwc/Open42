package open42;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BidTests {
	private Hand testHand;

	@Before
	public void setUp() throws Exception {
		testHand = new Hand();
	}

	@Test
	public final void testEmptyBid() {
		assertEquals(Bid.PASS, testHand.getBasicBid());
	}

	@Test
	public final void bidTest1() {
		testHand.add(new Domino(6, 6));
		testHand.add(new Domino(6, 5));
		testHand.add(new Domino(6, 4));
		testHand.add(new Domino(6, 3));
		testHand.add(new Domino(6, 2));
		testHand.add(new Domino(6, 1));
		testHand.add(new Domino(6, 0));

		assertEquals(84, testHand.getBasicBid().getBidPoints());
		assertEquals(6, testHand.getBasicBid().getTrump());
	}

	@Test
	public final void bidTest2() {
		testHand.add(new Domino(4, 4));
		testHand.add(new Domino(4, 2));
		testHand.add(new Domino(4, 1));
		testHand.add(new Domino(4, 0));
		testHand.add(new Domino(5, 5));
		testHand.add(new Domino(3, 3));
		testHand.add(new Domino(2, 2));

		assertEquals(31, testHand.getBasicBid().getBidPoints());
		assertEquals(4, testHand.getBasicBid().getTrump());
	}

	@Test
	public final void bidTest3() {
		testHand.add(new Domino(4, 4));
		testHand.add(new Domino(4, 2));
		testHand.add(new Domino(4, 1));
		testHand.add(new Domino(4, 0));
		testHand.add(new Domino(1, 1));
		testHand.add(new Domino(3, 3));
		testHand.add(new Domino(2, 2));

		assertEquals(31, testHand.getBasicBid().getBidPoints());
		assertEquals(4, testHand.getBasicBid().getTrump());
	}

	@Test
	public final void bidTest4() {
		// [4/2, 6/2, 3/0, 5/2, 2/2, 2/1, 6/6] - 42

		testHand.add(new Domino(4, 2));
		testHand.add(new Domino(6, 2));
		testHand.add(new Domino(3, 0));
		testHand.add(new Domino(5, 2));
		testHand.add(new Domino(2, 2));
		testHand.add(new Domino(2, 1));
		testHand.add(new Domino(6, 6));

		assertEquals(42, testHand.getBasicBid().getBidPoints());
		assertEquals(2, testHand.getBasicBid().getTrump());
	}

	@Test
	public final void bidTest5() {
		// [6/6, 0/0, 5/3, 6/2, 5/4, 3/1, 1/1] - pass

		testHand.add(new Domino(6, 6));
		testHand.add(new Domino(0, 0));
		testHand.add(new Domino(5, 3));
		testHand.add(new Domino(6, 2));
		testHand.add(new Domino(5, 4));
		testHand.add(new Domino(3, 1));
		testHand.add(new Domino(1, 1));

		assertEquals(Bid.PASS, testHand.getBasicBid());
	}

	@Test
	public final void bidTest6() {
		// [2/2, 5/3, 5/5, 4/1, 2/0, 3/3, 1/1] - TODO: 30, doubles as trumps

		testHand.add(new Domino(2, 2));
		testHand.add(new Domino(5, 3));
		testHand.add(new Domino(5, 5));
		testHand.add(new Domino(4, 1));
		testHand.add(new Domino(2, 0));
		testHand.add(new Domino(3, 3));
		testHand.add(new Domino(1, 1));

		assertEquals(Bid.PASS, testHand.getBasicBid());
		// TODO: assertEquals(Bid.SPECIAL, testHand.getBasicBid().getTrump());
	}

	@Test
	public final void bidTest7() {
		// [5/5, 5/4, 5/1, 6/5, 5/3, 3/1, 2/0] - 36

		testHand.add(new Domino(5, 5));
		testHand.add(new Domino(5, 4));
		testHand.add(new Domino(5, 1));
		testHand.add(new Domino(5, 6));
		testHand.add(new Domino(5, 3));
		testHand.add(new Domino(3, 1));
		testHand.add(new Domino(2, 0));

		assertEquals(36, testHand.getBasicBid().getBidPoints());
		assertEquals(5, testHand.getBasicBid().getTrump());
	}
}
