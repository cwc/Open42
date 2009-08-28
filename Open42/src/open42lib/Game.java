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
	 * TODO: Describe this method.
	 * 
	 * @param hand
	 * @return
	 */
	public int makeBid(Domino[] hand) {
		int bid = -1;
		
		ArrayList<Domino> doubles = new ArrayList<Domino>();	// The doubles in this hand	
		int[] suits = new int[Domino.MAX_PIPS + 1];			// The number of dominos we have of each suit
		
		for (int i = 0; i < suits.length; i++) {	// Initialize the array of suit counts
			suits[i] = 0;
		}
		
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].isDouble()) {
				doubles.add(hand[i]);
				suits[hand[i].bigEnd()]++;
			} else {
				suits[hand[i].bigEnd()]++;
				suits[hand[i].littleEnd()]++;
			}
		}
		
		// We now know the number of doubles and the number of each suit (i.e. three 4s, two 6s, five blanks)
		
		int maxSide = 0;	// Here we find out which suit we have the most of (this will be our most likely trump candidate)
		for (int i = 0; i < suits.length - 1; i++) {
			if (suits[i + 1] > suits[maxSide]) {
				maxSide = i;
			}
		}
		
		// Now we calculate our maximum bid
		if (doubles.contains(new Domino(maxSide, maxSide))) { // Do we have the double for our dominant suit?
			if (doubles.size() >= hand.length - suits[maxSide]) {
				// 4 doubles and 4 in our suit would be a potential lay down
				// Worst hand: (1/1, 3/3, 6/6, 4/4, 4/0, 4/2, 4/3)
				// Best hand: (4/4, 4/6, 4/5, 4/3, 5/5, 3/3, 2/2)
				// Worst hand could see opponent's 4/6 capping the 5/5... nasty 
				
				// 3 doubles and 4 in our suit is even less stellar
				// Worst hand: (1/1, 0/0, 0/5, 4/4, 4/0, 4/2, 4/3)
				// Best hand: (4/4, 4/6, 4/5, 4/3, 3/3, 0/0, 6/5)
				// Worst hand would probably lose at least 16. Getting the tenners would be a must.
				bid = 32;	
			} else {	// Default case (no bid)
			}
		} else { // We lack the double for the dominant suit
			
		}
		
		return bid;
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
