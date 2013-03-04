package open42.player;

import open42.Bid;
import open42.Hand;

public abstract class Player {
	private Hand hand = new Hand();
	private Player partner;

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
}
