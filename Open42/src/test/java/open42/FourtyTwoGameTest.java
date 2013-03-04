package open42;

import static org.junit.Assert.assertTrue;
import open42.game.FourtyTwoGame;

import org.junit.Test;

public class FourtyTwoGameTest extends GameTest {
	@Test
	public void testPartners() {
		// Test that partners have been linked correctly
		new FourtyTwoGame(players);

		assertTrue(players.get(0).getPartner().equals(players.get(2)));
		assertTrue(players.get(1).getPartner().equals(players.get(3)));
		assertTrue(players.get(2).getPartner().equals(players.get(0)));
		assertTrue(players.get(3).getPartner().equals(players.get(1)));
	}
}
