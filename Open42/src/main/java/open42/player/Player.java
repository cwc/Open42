package open42.player;

import java.util.ArrayList;
import java.util.List;

import open42.Bid;
import open42.Domino;
import open42.Hand;

public abstract class Player {
	private Hand hand = new Hand();
	private Player partner;
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public Player getPartner() {
		return partner;
	}

	public void setPartner(Player partner) {
		this.partner = partner;
	}

	public Hand getHand() {
		return hand;
	}

	public abstract Bid getBid();

	/**
	 * @param bid
	 * @param currentTrick
	 * @param currentHand
	 *            The dominoes that have been played so far in the current hand
	 * 
	 * @return the domino to play
	 */
	public Domino playDomino(Bid bid, List<Domino> currentTrick,
			ArrayList<Domino> currentHand) {
		// Shortcut logic if empty handed
		if (hand.size() <= 0)
			return null;

		int suitToPlay;
		if (currentTrick.size() == 0) {
			// We are leading the trick
			suitToPlay = bid.getTrump();
		} else {
			// Follow suit if possible
			if (currentTrick.get(0).isSuit(bid.getTrump())) {
				suitToPlay = bid.getTrump();
			} else {
				suitToPlay = currentTrick.get(0).bigEnd();
			}
		}

		// Play largest domino we have
		Domino biggestDomino = Domino.getLargestDomino(hand, bid.getTrump(),
				suitToPlay);
		if (biggestDomino != null)
			return biggestDomino;

		return hand.get((int) (Math.random() * hand.size()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}
