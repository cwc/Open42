package open42;

import open42.game.Game;

/**
 * A bid encapsulates a point total and combination of trump suit and bid
 * condition. Some bid conditions may not require a trump suit (e.g. NoTrumps or
 * DoublesAsTrumps); in those cases, the value of the trump is equal to
 * Bid.SPECIAL.
 */
public class Bid {
	public static final int SPECIAL = -1;
	public static final Bid PASS = new Bid(-1, SPECIAL);

	private int bid;
	private BidCondition condition = BidCondition.Straight;
	private int trump = SPECIAL;

	public Bid(int bid, int trump) {
		setBid(bid);
		this.trump = trump;
	}

	public Bid(int bid, int trump, BidCondition condition) {
		setBid(bid);
		this.trump = trump;
		this.condition = condition;
	}

	public int getBidPoints() {
		return bid;
	}

	public BidCondition getBidCondition() {
		return condition;
	}

	private void setBid(int bid) {
		if (bid > Game.MAX_BID) {
			this.bid = (bid / Game.MAX_BID) * Game.MAX_BID;
		} else {
			this.bid = bid;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(Integer.toString(bid));

		if (trump == SPECIAL) {
			sb.append(", " + condition.toString());
		} else {
			sb.append(", " + trump + " as trump");
		}

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Integer) {
			// If it's an int, just check the points value
			return obj.equals(getBidPoints());
		} else if (obj instanceof Bid) {
			// If it's a bid, it must have the exact same parameters
			Bid otherBid = (Bid) obj;

			return (otherBid.getBidCondition() == getBidCondition() && otherBid
					.getBidPoints() == getBidPoints());
		}

		return super.equals(obj);
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
}
