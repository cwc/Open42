package open42;

import open42.player.Player;

public class AIPlayer extends Player {
	public AIPlayer(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see open42.player.Player#getBid()
	 */
	@Override
	public Bid getBid() {
		return getHand().getBasicBid();
	}
}
