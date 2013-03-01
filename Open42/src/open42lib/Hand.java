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
}
