package open42lib;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A Hand is a collection of Domino objects
 */
public class Hand extends ArrayList<Domino> {
	private static final long serialVersionUID = -1004042584344831119L;

	private int maxDominoes = 7;

	/**
	 * Creates a default hand that can contain up to seven dominoes
	 */
	public Hand() {
	}

	/**
	 * Creates a hand that can contain no more than the specified number of
	 * dominoes
	 * 
	 * @param maxDominoes
	 *            The maximum number of dominoes to allow in this hand. A
	 *            non-zero value means an unlimited hand size.
	 */
	public Hand(int maxDominoes) {
		this.maxDominoes = maxDominoes;
	}

	/**
	 * Sorts this hand
	 */
	public void sort() {

	}

	@Override
	public boolean add(Domino e) {
		if (size() == maxDominoes) {
			return false;
		}

		return super.add(e);
	}

	@Override
	public void add(int index, Domino element) {
		if (size() != maxDominoes) {
			super.add(index, element);
		}
	}

	@Override
	public boolean addAll(Collection<? extends Domino> c) {
		if (c.size() + this.size() > maxDominoes) {
			return false;
		}

		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Domino> c) {
		if (c.size() + this.size() > maxDominoes) {
			return false;
		}

		return super.addAll(index, c);
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size(); i++)
				if (get(i) == null)
					return i;
		} else {
			for (int i = 0; i < size(); i++)
				if (get(i).equals(o))
					return i;
		}
		return -1;
	}

	/**
	 * Calculates a simple bid based on how good the dominos in this hand
	 * appear.
	 * 
	 * This is akin to the sort of bid one might arrive at in their head, prior
	 * to hearing opponent's or partner's bids.
	 * 
	 * @return A Bid object describing the optimum bid
	 */
	public Bid getBasicBid() {
		int bid = -1;
		BidCondition bidCondition = BidCondition.Straight;

		Hand doubles = new Hand(); // The doubles in this hand
		int[] suits = new int[Domino.MAX_PIPS + 1]; // The number of dominos we
													// have of each suit

		for (int i = 0; i < suits.length; i++) { // Initialize the array of suit
													// counts
			suits[i] = 0;
		}

		for (Domino domino : this) {
			if (domino == null) {
				break;
			}

			if (domino.isDouble()) {
				doubles.add(domino);
				suits[domino.bigEnd()]++;
			} else {
				suits[domino.bigEnd()]++;
				suits[domino.littleEnd()]++;
			}
		}

		// We now know the number of doubles and the number of each suit (i.e.
		// three 4s, two 6s, five blanks)

		// Determine a trump suit
		for (int i = 0; i < suits.length; i++) {
			if (suits[i] > 6) {
				bid = 84;
			} else if (suits[i] > 4) {
				if (doubles.contains(new Domino(i, i))) {
					bid = 42;
				} else {
					bid = 35;
				}
			} else if (suits[i] > 2) {
				if (doubles.contains(new Domino(i, i))) {
					bid = 31;
				} else {
					bid = 30;
				}
			}

			if (bid > 30)
				break;
		}

		return new Bid(bid, bidCondition);
	}
}
