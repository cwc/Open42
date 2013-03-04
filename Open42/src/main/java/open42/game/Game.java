package open42.game;

import java.util.ArrayList;
import java.util.List;

import open42.Bid;
import open42.Domino;
import open42.Player;

public class Game {
	/**
	 * The number of dominos in a set
	 */
	public static final int DOMINO_COUNT = 28;

	/**
	 * The minimum bid
	 */
	public static final int MIN_BID = 30;

	/**
	 * The maximum bid (higher bids must be a multiple of this i.e. 84, 168,
	 * etc.)
	 */
	public static final int MAX_BID = 42;

	/**
	 * A set of unique <code>Domino</code> objects representing every domino in
	 * the game
	 */
	private ArrayList<Domino> dominoSet = new ArrayList<Domino>(DOMINO_COUNT);

	/**
	 * A list of players
	 */
	public List<Player> players = new ArrayList<Player>();

	/**
	 * Stores the trump suit for the current hand
	 */
	private int trump = -1;

	/**
	 * Stores the bid for the current hand
	 */
	private Bid bid = Bid.PASS;

	/**
	 * Starts a new game with the given number of players
	 * 
	 * @param numPlayers
	 */
	public Game(int numPlayers) {
		// Populate the set of dominos
		for (int j = Domino.MIN_PIPS; j <= Domino.MAX_PIPS; j++) {
			for (int k = j; k <= Domino.MAX_PIPS; k++) {
				dominoSet.add(new Domino(j, k));
			}
		}

		// Create players
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player());
		}
	}

	/**
	 * Divides dominos from the main set into everyone's hands
	 */
	public void drawHands() {
		for (Player p : players) {
			while (p.getHand().size() < 7) {
				p.getHand().add(dominoSet.remove(0));
			}
		}
	}

	/**
	 * Puts all dominos back into the main set
	 */
	public void resetDominos() {
		// Empty everyone's hands
		for (Player p : players) {
			while (p.getHand().size() > 0) {
				dominoSet.add(p.getHand().remove(0));
			}
		}
	}

	/**
	 * Shuffles the dominos
	 * 
	 * TODO: Implement a better (more efficient and/or faster) sorting algorithm
	 * 
	 * @return <code>Game.dominoSet</code>, after being shuffled
	 */
	public ArrayList<Domino> shuffleDominos() {
		if (dominoSet.size() < DOMINO_COUNT) {
			resetDominos();
		}

		int[] order = new int[dominoSet.size()];

		for (int i = 0; i < order.length; i++) {
			order[i] = (int) (Math.random() * 2800);
		}

		int temp;
		Domino tempD;
		for (int i = 0; i < order.length - 1; i++) {
			if (order[i] > order[i + 1]) {
				temp = order[i];
				order[i] = order[i + 1];
				order[i + 1] = temp;

				tempD = dominoSet.get(i);
				dominoSet.set(i, dominoSet.get(i + 1));
				dominoSet.set(i + 1, tempD);

				i = -1;
			}
		}

		return dominoSet;
	}

	public int getTrump() {
		return trump;
	}

	public void setTrump(int trump) {
		if (trump < Domino.MIN_PIPS) {
			this.trump = Domino.MIN_PIPS;
		} else if (trump > Domino.MAX_PIPS) {
			this.trump = Domino.MAX_PIPS;
		} else {
			this.trump = trump;
		}
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}
}
