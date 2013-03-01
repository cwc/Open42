package open42

import open42lib.Game

public class Open42{
	/**
	 * @param args
	 */
	public static void main(def args) {
		def my42Game = new Game()

		(1..5).each {
			println "This hand: "
			my42Game.shuffleDominos()
			my42Game.drawHands()
			my42Game.hands.each {
				println it.toString() + " - " + my42Game.makeBid(it)
			}
		}
	}
}
