package open42;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import open42.game.FourtyTwoGame;
import open42.game.PlayerIterator;
import open42.player.AIPlayer;
import open42.player.Player;

import org.junit.Before;
import org.junit.Test;

public class FourtyTwoGameTest {
	private FourtyTwoGame testGame;
	private List<Player> players;
	private List<Player> teamOne;
	private List<Player> teamTwo;

	@Test
	public void testPartners() {
		assertTrue(players.get(0).getPartner().equals(players.get(2)));
		assertTrue(players.get(1).getPartner().equals(players.get(3)));
		assertTrue(players.get(2).getPartner().equals(players.get(0)));
		assertTrue(players.get(3).getPartner().equals(players.get(1)));
	}

	@Before
	public void setUp() {
		teamOne = new ArrayList<Player>();
		teamTwo = new ArrayList<Player>();

		teamOne.add(new AIPlayer("AI1"));
		teamOne.add(new AIPlayer("AI2"));
		teamTwo.add(new AIPlayer("AI3"));
		teamTwo.add(new AIPlayer("AI4"));

		testGame = new FourtyTwoGame(teamOne, teamTwo);
		players = testGame.players;
	}

	@Test
	public void testPlayerIterator() {
		// Ensure that the iterator for a 42 game interlaces the teams' players
		int i = 0;
		PlayerIterator iter = testGame.getTableIterator(players.get(i));

		Player p = iter.next();
		assertEquals(teamOne.get(0), p);

		p = iter.next();
		assertEquals(teamTwo.get(0), p);

		p = iter.next();
		assertEquals(teamOne.get(1), p);

		p = iter.next();
		assertEquals(teamTwo.get(1), p);
	}
}
