package open42;

import java.util.Collection;
import java.util.List;

public class Domino {
	public static final int MAX_PIPS = 6;
	public static final int MIN_PIPS = 0;

	public static final Collection<Domino> FIVE_COUNTS = new Hand();
	public static final Collection<Domino> TEN_COUNTS = new Hand();

	private int side0;
	private int side1;

	static {
		// Initialize constants
		FIVE_COUNTS.add(new Domino(3, 2));
		FIVE_COUNTS.add(new Domino(4, 1));
		FIVE_COUNTS.add(new Domino(0, 5));
		TEN_COUNTS.add(new Domino(6, 4));
		TEN_COUNTS.add(new Domino(5, 5));
	}

	public Domino(int side0, int side1) {
		this.setSide0(side0);
		this.setSide1(side1);
	}

	public String toString() {
		return bigEnd() + "/" + littleEnd();
	}

	public void setSide0(int value) {
		if (value > MAX_PIPS) {
			side0 = MAX_PIPS;
		} else if (value < MIN_PIPS) {
			side0 = MIN_PIPS;
		} else {
			side0 = value;
		}
	}

	public void setSide1(int value) {
		if (value > MAX_PIPS) {
			side1 = MAX_PIPS;
		} else if (value < MIN_PIPS) {
			side1 = MIN_PIPS;
		} else {
			side1 = value;
		}
	}

	public int bigEnd() {
		return Math.max(side0, side1);
	}

	public int littleEnd() {
		return Math.min(side0, side1);
	}

	public boolean isDouble() {
		return side0 == side1;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Domino) {
			Domino otherDomino = (Domino) o;

			return (this.littleEnd() == otherDomino.littleEnd() && this
					.bigEnd() == otherDomino.bigEnd());
		} else if (o instanceof String) {
			return this.toString().equals(o);
		} else {
			return false;
		}
	}

	/**
	 * Checks if the domino is of the given suit
	 * 
	 * @param suit
	 *            The suit to test
	 * 
	 * @return true if either end of the domino matches the given suit
	 */
	public boolean isSuit(int suit) {
		return side0 == suit || side1 == suit;
	}

	/**
	 * Checks whether this domino is larger than the other
	 * 
	 * @param other
	 *            the domino to compare
	 * @param suit
	 *            the suit the dominos are played as (e.g. 0/0 is smaller than
	 *            2/0 if suit == 2)
	 * 
	 * @return true if the two dominoes share a suit, and this one is larger
	 */
	public boolean isLargerThan(Domino other, int suit) {
		// Shortcut edge case
		if (other == null) {
			return true;
		}

		if (isSuit(suit) && !other.isSuit(suit)) {
			// The other domino doesn't match the given suit
			return true;
		}

		// Determine which side of the other domino we match
		if (isSuit(other.littleEnd())) {
			if (isDouble())
				return true;

			if (other.isDouble())
				return false;

			// Same suit as the other's little end
			return bigEnd() > other.bigEnd();
		} else if (isSuit(other.bigEnd())) {
			if (isDouble())
				return true;

			if (other.isDouble())
				return false;

			return littleEnd() > other.littleEnd();
		} else {
			// The two dominoes share no suit, so this domino can't be larger
			return false;
		}
	}

	/**
	 * @return true if this is a count domino
	 */
	public boolean isCount() {
		return Domino.FIVE_COUNTS.contains(this)
				|| Domino.TEN_COUNTS.contains(this);
	}

	/**
	 * @param trump
	 * @return
	 */
	public static Domino getLargestDomino(List<Domino> trick, int trump,
			int suit) {
		Domino biggestDomino = trick.get(0);
		for (Domino d : trick) {
			if (d.isSuit(trump)
					&& (!biggestDomino.isSuit(trump) || d.isLargerThan(
							biggestDomino, trump))) {
				biggestDomino = d;
			} else if (!biggestDomino.isSuit(trump)
					&& (d.isLargerThan(biggestDomino, suit))) {
				biggestDomino = d;
			}
		}
		return biggestDomino;
	}
}
