package open42;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A Hand is a collection of Domino objects
 */
public class Hand extends ArrayList<Domino> {
	private static final long serialVersionUID = -1004042584344831119L;

	private int maxDominoes = 7;

	private Collection<Domino> doubles = new ArrayList<Domino>();

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
	 * to hearing an opponent's or partner's bids.
	 * 
	 * @return A Bid object describing the bid
	 */
	public Bid getBasicBid() {
		int bid = -1;
		BidCondition bidCondition = BidCondition.Straight;

		int suit = 0;
		int[] matches = new int[Domino.MAX_PIPS + 1]; // The number of dominos
														// we have of each suit

		for (suit = 0; suit < matches.length; suit++) { // Initialize the array
														// of suit counts
			matches[suit] = 0;
		}

		for (Domino domino : this) {
			if (domino == null) {
				break;
			}

			if (domino.isDouble()) {
				doubles.add(domino);
				matches[domino.bigEnd()]++;
			} else {
				matches[domino.bigEnd()]++;
				matches[domino.littleEnd()]++;
			}
		}

		// We now know the number of doubles and the number of each suit (i.e.
		// three 4s, two 6s, five blanks)

		// Determine a trump suit
		for (suit = 0; suit < matches.length; suit++) {
			if (matches[suit] > 6) {
				// Simple edge case
				bid = 84;
			} else if (matches[suit] > 4) {
				bid = 42;

				if (hasUnprotected(4, suit) || hasUnprotected(6, suit)
						|| hasUnprotected(5, suit)) {
					bid -= 11;
				}

				if (getNumUnprotectedFiveCounts(suit) >= 2) {
					bid -= 6;
				}
			} else if (matches[suit] > 2) {
				if (doubles.contains(new Domino(suit, suit))) {
					bid = 31;
				} else {
					bid = 30;
				}
			}

			if (bid > 30)
				break;
		}

		return new Bid(bid, suit, bidCondition);
	}

	/**
	 * Checks this hand for a given number of unprotected five count suits
	 * 
	 * @param num
	 *            The number of potentially lost five counts
	 * @param trump
	 *            The suit to consider as trump
	 * 
	 * @return the number of unprotected five counts in this hand
	 */
	public int getNumUnprotectedFiveCounts(int trump) {
		int i = 0;

		if (hasUnprotected(3, trump))
			i++;
		if (hasUnprotected(2, trump))
			i++;
		if (hasUnprotected(1, trump))
			i++;

		return i;
	}

	/**
	 * Checks this hand for an uncovered domino of the given suit (e.g. 4/0
	 * without 4/6 or 4/4, 5/2 without 5/5, 6/3 without 6/4 or 6/6)
	 * 
	 * @param suit
	 *            The suit to check
	 * @param trump
	 *            The suit being considered as trump
	 * 
	 * @return true if the suit is unprotected
	 */
	public boolean hasUnprotected(int suit, int trump) {
		if (suit == trump)
			return false;

		for (Domino domino : this) {
			if (domino.isSuit(trump))
				continue;

			if (domino.isSuit(suit) && (!hasDouble(suit))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks this hand for a double of the given suit
	 * 
	 * @param suit
	 *            The suit to check
	 * @return true if this hand contains the double of the given suit
	 */
	public boolean hasDouble(int suit) {
		return doubles.contains(new Domino(suit, suit));
	}

	/**
	 * Checks this hand for any domino that is also contained in the given
	 * collection
	 * 
	 * @param dominos
	 *            The collection to compare
	 * 
	 * @return true if this hand contains any domino in the given hand
	 */
	public boolean containsAny(Collection<Domino> dominos) {
		for (Domino domino : this) {
			if (dominos.contains(domino))
				return true;
		}

		return false;
	}
}
