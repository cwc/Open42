package open42

import java.text.Bidi;

public class Open42 {
	def static team1 = [new AIPlayer("AI1"), new AIPlayer("AI2")]
	def static team2 = [new AIPlayer("AI3"), new AIPlayer("AI4")]
	def static score = [(team1) : 0, (team2) : 0]
	
	public static boolean noWinner() {
		return score[team1] < 7 && score[team2] < 7
	}
	
	/**
	 * @param args
	 */
	public static void main(def args) {
		// Set up game
		def my42Game = new game.FourtyTwoGame(team1, team2)

		// Play hands until game is over
		while (noWinner()) {
			def iter = my42Game.getTableIterator()
			iter.each {
				// Set up this hand
				def nextBidder = it
				
				my42Game.shuffleDominos()
				my42Game.drawHands()

				def currentBid = Bid.PASS;
				def highBidder
				def highTeam
				def currentPlayer

				def handPoints = [(team1) : 0, (team2) : 0]

				// Solicit bids
				my42Game.getTableIterator(nextBidder).each {
					def bid = it.getBid()

					if (bid.getBidPoints() > currentBid.getBidPoints()) {
						currentBid = bid
						highBidder = it
						highTeam = my42Game.getTeam(it)
					}
				}

				currentPlayer = highBidder
				println currentPlayer.toString() + " is highest bidder with " + currentBid

				// Play each trick
				(1..7).each {
					def result = my42Game.playTrick(currentPlayer, currentBid)
					currentPlayer = result.getWinner()

					handPoints[my42Game.getTeam(currentPlayer)] += result.getPoints()

					println result
				}

				// Determine score
				def marks = Math.max((int)(currentBid.getBidPoints() / 42), 1)
				if (handPoints[highTeam] >= currentBid.getBidPoints()) {
					score[highTeam] += marks
					
					println highTeam.toString() + " wins hand with " + handPoints[highTeam]
				} else {
					score[my42Game.getOppositeTeam(highTeam)] += marks
					
					println my42Game.getOppositeTeam(highTeam).toString() + " wins hand with " + handPoints[my42Game.getOppositeTeam(highTeam)]
				}
				
				if (!noWinner()) {
					// HACK Force inner loop to complete
					while (iter.hasNext()) iter.next()
				}
			}
		}

		println "Final score: " + score[team1] + " - " + score[team2]
	}
}
