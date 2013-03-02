package open42;

import static org.junit.Assert.*;

import open42.Domino;

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

}
