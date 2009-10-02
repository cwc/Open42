/**
 * 
 */
package open42lib;

/**
 * @author cwc
 *
 */
public class Bid {
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
}
