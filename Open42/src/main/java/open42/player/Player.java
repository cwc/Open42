package open42.player;

import java.util.ArrayList;
import java.util.List;

import open42.Bid;
import open42.Domino;
import open42.Hand;

public abstract class Player {
	protected Hand hand = new Hand();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @param bid
	 * @param trick
	 * @param dominoSet
	 * @return
	 */
	public abstract Domino playDomino(Bid bid, List<Domino> trick,
			ArrayList<Domino> dominoSet);
}
