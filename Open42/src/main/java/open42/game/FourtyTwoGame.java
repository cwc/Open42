package open42.game;

import java.util.ArrayList;
import java.util.List;

import open42.player.Player;

public class FourtyTwoGame extends Game {
	private List<Player> teamOne;
	private List<Player> teamTwo;

	public FourtyTwoGame(List<Player> teamOne, List<Player> teamTwo) {
		super(new ArrayList<Player>());

		this.teamOne = teamOne;
		this.teamTwo = teamTwo;

		players.add(teamOne.get(0));
		players.add(teamTwo.get(0));
		players.add(teamOne.get(1));
		players.add(teamTwo.get(1));

		// Set up partners
		players.get(0).setPartner(players.get(2));
		players.get(1).setPartner(players.get(3));
		players.get(2).setPartner(players.get(0));
		players.get(3).setPartner(players.get(1));
	}

	public List<Player> getTeam(Player player) {
		if (teamOne.contains(player))
			return teamOne;
		if (teamTwo.contains(player))
			return teamTwo;

		return null;
	}

	public List<Player> getOppositeTeam(List<Player> team) {
		if (team.equals(teamOne))
			return teamTwo;

		if (team.equals(teamTwo))
			return teamOne;

		return null;
	}
}
