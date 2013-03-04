package open42;

public class Player {
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
}
