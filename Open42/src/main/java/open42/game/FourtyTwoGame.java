package open42.game;

public class FourtyTwoGame extends Game {
	public FourtyTwoGame() {
		super(4);

		// Set up partners
		players.get(0).setPartner(players.get(2));
		players.get(1).setPartner(players.get(3));
		players.get(2).setPartner(players.get(0));
		players.get(3).setPartner(players.get(1));
	}
}
