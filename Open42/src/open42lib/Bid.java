package open42lib;

public class Bid {
	public static final Bid PASS = new Bid(-1);

	private int bid;
	private BidCondition condition = BidCondition.Straight;

	public Bid(int bid) {
		setBid(bid);
	}

	public Bid(int bid, BidCondition condition) {
		setBid(bid);
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
		return Integer.toString(bid);
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
}
