package open42

import java.text.Bidi;

public class Open42 {
	/**
	 * @param args
	 */
	public static void main(def args) {
		// Set up game
		def my42Game = new game.FourtyTwoGame()
		def t1score = 0;
		def t2score = 0;
		def dealer = 0;
	
		def maxBid = Bid.PASS;	
		
		// Play hands until game is over
		while (t1score < 7 && t2score < 7) {
			// Set up this hand
			my42Game.shuffleDominos()
			my42Game.drawHands()
			
			// Solicit bids
			my42Game.players.each {
				def bid = it.getBid();
				
				if (bid > maxBid) {
					
				}
			}
			
			// Play each trick
		}
	}
}
