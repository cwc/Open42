/**
 * 
 */
package open42lib;

/**
 * @author cwc
 *
 */
public class Domino {
	public static final int MAX_PIPS = 6;
	public static final int MIN_PIPS = 0;
	
	private int side0;
	private int side1;
	
	public Domino(int side0, int side1) {
		this.setSide0(side0);
		this.setSide1(side1);
	}
	
	public String toString() {
		return bigEnd() + "/" + littleEnd();
	}
	
	public void setSide0(int value) {
		if (value > MAX_PIPS) {
			side0 = MAX_PIPS;
		} else if (value < MIN_PIPS) {
			side0 = MIN_PIPS;
		} else {
			side0 = value;
		}
	}
	
	public void setSide1(int value) {
		if (value > MAX_PIPS) {
			side1 = MAX_PIPS;
		} else if (value < MIN_PIPS) {
			side1 = MIN_PIPS;
		} else {
			side1 = value;
		}
	}
	
	public int bigEnd() {
		return Math.max(side0, side1);
	}
	
	public int littleEnd() {
		return Math.min(side0, side1);
	}
	
	public boolean isDouble() {
		return side0 == side1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Domino) {
			Domino otherDomino = (Domino)o;
			
			return (this.littleEnd() == otherDomino.littleEnd() && this.bigEnd() == otherDomino.bigEnd());
		} else if (o instanceof String) {
			return this.toString().equals(o);
		} else {
			return false;
		}		
	}
}
