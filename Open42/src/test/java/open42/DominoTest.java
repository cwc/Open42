package open42;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DominoTest {
	Domino testDomino;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testDomino = new Domino(0, 0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testComparison() {
		assertEquals(new Domino(6, 6), new Domino(6, 6));

		assertTrue(new Domino(5, 3).equals("5/3"));
		assertFalse(new Domino(5, 3).equals("3/5"));

		assertEquals(new Domino(5, 3).toString(), "5/3");
	}

	@Test
	public final void testToString() {
		assertTrue("Initial setup domino is a blank-blank", testDomino
				.toString().equals("0/0"));

		testDomino.setSide0(4);
		testDomino.setSide1(3);

		assertTrue("Domino is now a four-three",
				testDomino.toString().equals("4/3"));
	}

	@Test
	public final void testSideMethods() {
		assertTrue(testDomino.littleEnd() == 0);
		assertTrue(testDomino.bigEnd() == 0);

		testDomino.setSide0(4);

		assertTrue(testDomino.littleEnd() == 0);
		assertTrue(testDomino.bigEnd() == 4);

		testDomino.setSide1(5);

		assertTrue(testDomino.littleEnd() == 4);
		assertTrue(testDomino.bigEnd() == 5);

		testDomino.setSide0(42);

		assertTrue(testDomino.bigEnd() == Domino.MAX_PIPS);
		assertTrue(testDomino.littleEnd() == 5);
	}

	@Test
	public final void testIsDouble() {
		assertTrue("Initial setup domino is a blank-blank",
				testDomino.isDouble());

		testDomino.setSide0(3);

		assertTrue(!testDomino.isDouble());

		testDomino.setSide1(3);

		assertTrue(testDomino.isDouble());

		testDomino.setSide0(1);
		testDomino.setSide1(1);

		assertTrue(testDomino.isDouble());
	}

	@Test
	public void testIsLargerThan() {
		assertEquals(true, new Domino(0, 0).isLargerThan(new Domino(2, 0), 0));

		assertEquals(true, new Domino(6, 5).isLargerThan(new Domino(6, 6), 5));
		assertEquals(false, new Domino(6, 5).isLargerThan(new Domino(6, 6), 6));
		assertEquals(false, new Domino(6, 5).isLargerThan(new Domino(6, 6), 0));
	}

	@Test
	public void testGetLargestDomino() {
		List<Domino> trick = new ArrayList<Domino>();
		int trump = 0;

		// [0/0, 6/5, 6/6, 2/0]
		trick.add(new Domino(0, 0));
		trick.add(new Domino(6, 5));
		trick.add(new Domino(6, 6));
		trick.add(new Domino(2, 0));

		assertEquals(trick.get(0), Domino.getLargestDomino(trick, trump, 0));

		trump = 1;
		assertEquals(trick.get(0), Domino.getLargestDomino(trick, trump, 0));

		trump = 6;
		assertEquals(trick.get(2), Domino.getLargestDomino(trick, trump, 0));

		trump = 5;
		assertEquals(trick.get(1), Domino.getLargestDomino(trick, trump, 0));

		trump = 2;
		assertEquals(trick.get(3), Domino.getLargestDomino(trick, trump, 0));
	}
}
