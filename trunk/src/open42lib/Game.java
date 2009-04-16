/**
 * 
 */
package open42lib;

import java.util.ArrayList;

/**
 * @author cwc
 *
 */
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
	 * The maximum bid (higher bids must be a multiple of this i.e. 84, 168, etc.)
	 */
	public static final int MAX_BID = 42;

	/**
	 * A set of unique <code>Domino</code> objects representing every domino in the game
	 */
	private ArrayList<Domino> dominoSet = new ArrayList<Domino>(DOMINO_COUNT);
	
	/**
	 * An array of player hands
	 */
	public Domino[][] hands = new Domino[][] {
			new Domino[DOMINO_COUNT / 4],
			new Domino[DOMINO_COUNT / 4],
			new Domino[DOMINO_COUNT / 4],
			new Domino[DOMINO_COUNT / 4]
	};
	
	/**
	 * The pile to which dominos are added after each trick
	 */
	private ArrayList<Domino> pile = new ArrayList<Domino>();
	
	/**
	 * Stores the trump suit for the current hand
	 */
	private int trump = -1;
	
	/**
	 * Stores the bid for the current hand
	 */
	private int bid = -1;
	
	public Game() {
		// Populate the set of dominos
		for (int j = Domino.MIN_PIPS; j <= Domino.MAX_PIPS; j++) {
			for (int k = j; k <= Domino.MAX_PIPS; k++) {
				dominoSet.add(new Domino(j, k));
			}
		}
	}
	
	/**
	 * Divides dominos from the main set into everyone's hands
	 */
	public void drawHands() {
		for (int i = 0; i < hands.length; i++) {
			for (int j = 0; j < hands[i].length; j++) {
				hands[i][j] = dominoSet.remove(0);
			}
		}
	}
	
	/**
	 * Puts all dominos back into the main set
	 */
	public void resetDominos() {
		// First, empty everyone's hands
		for (int i = 0; i < hands.length; i++) {
			for (int j = 0; j < hands[i].length; j++) {
				if (hands[i][j] != null) {
					dominoSet.add(hands[i][j]);
					hands[i][j] = null;
				}
			}
		}
		
		// Then, collect the dominos from the pile
		while (pile.size() > 0) {
			dominoSet.add(pile.remove(0));
		}
	}
	
	/**
	 * Shuffles the dominos
	 * 
	 * TODO: Implement a better (more efficient and/or faster) sorting algorithm
	 * 
	 * @return <code>Game.dominoSet</code>, after being shuffled
	 */
	public ArrayList<Domino> shuffle() {
		if (dominoSet.size() < DOMINO_COUNT) {
			resetDominos();
		}
		
		ArrayList<Domino> shuffledSet = dominoSet;

		int[] order = new int[shuffledSet.size()];
		
		for (int i = 0; i < order.length; i++) {
				order[i] = (int)(Math.random() * 2800);
		};
		
		int temp;
		Domino tempD;
		for (int i = 0; i < order.length - 1; i++) {
			if (order[i] > order[i + 1]) {
				temp = order[i];
				order[i] = order[i + 1];
				order[i + 1] = temp;
				
				tempD = shuffledSet.get(i);
				shuffledSet.set(i, shuffledSet.get(i + 1)); 
				shuffledSet.set(i + 1, tempD);
				
				i = -1;
			}
		}
		
		return shuffledSet;
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

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		if (bid < MIN_BID) {
			this.bid = MIN_BID;
		} else if (bid > MAX_BID) {
			this.bid = (bid / MAX_BID) * MAX_BID;
		} else {
			this.bid = bid;
		}
	}
}
