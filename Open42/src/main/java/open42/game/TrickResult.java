package open42.game;

import java.util.List;

import open42.Domino;
import open42.player.Player;

/**
 * 
 */
public class TrickResult {

	private Player winner;
	private List<Domino> trick;

	/**
	 * @param winner
	 * @param trick
	 */
	public TrickResult(Player winner, List<Domino> trick) {
		this.winner = winner;
		this.trick = trick;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return trick.toString() + " - " + winner.toString();
	}

	public Player getWinner() {
		return this.winner;
	}

	public int getPoints() {
		int points = 1;

		for (Domino domino : Domino.TEN_COUNTS) {
			if (trick.contains(domino)) {
				points += 10;
			}
		}

		for (Domino domino : Domino.FIVE_COUNTS) {
			if (trick.contains(domino)) {
				points += 5;
			}
		}

		return points;
	}
}
