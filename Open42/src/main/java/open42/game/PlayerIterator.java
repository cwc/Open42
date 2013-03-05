package open42.game;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import open42.player.Player;

/**
 * PlayerIterator iterates over an ordered list of players, starting with a
 * given player and looping around to the beginning of the list if necessary in
 * order to reach them all.
 */
public class PlayerIterator implements ListIterator<Player>, Iterable<Player> {
	private int currentIndex;
	private int iterations = 0;
	private List<Player> players;

	/**
	 * @param firstPlayer
	 * @param game
	 */
	PlayerIterator(List<Player> players, Player firstPlayer) {
		this.players = players;
		currentIndex = players.indexOf(firstPlayer);

		// It's expected that the first Player returned by next() will have the
		// same index as given in firstIndex. Rolling the iterator back once
		// accomplishes that.
		currentIndex = previousIndex();
	}

	@Override
	public void add(Player p) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasNext() {
		return iterations < players.size();
	}

	@Override
	public boolean hasPrevious() {
		return iterations > -players.size();
	}

	@Override
	public Player next() {
		currentIndex = nextIndex();
		iterations++;

		return players.get(currentIndex);
	}

	@Override
	public int nextIndex() {
		int i = currentIndex + 1;
		if (i >= players.size()) {
			i = 0;
		}

		return i;
	}

	@Override
	public Player previous() {
		currentIndex = previousIndex();
		iterations--;

		return players.get(currentIndex);
	}

	@Override
	public int previousIndex() {
		int i = currentIndex - 1;
		if (i < 0) {
			i = players.size() - 1;
		}

		return i;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(Player p) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Player> iterator() {
		return this;
	}
}