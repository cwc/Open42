package open42;

public enum BidCondition {
	Straight, NoTrump, DoublesAsTrumps, LowDoublesLow, LowDoublesHigh, LowDoublesSeparate;

	public String toString() {
		switch (this) {
		case Straight:
			return "straight";
		case DoublesAsTrumps:
			return "doubles as trumps";
		case LowDoublesHigh:
			return "nello";
		case LowDoublesLow:
			return "nello, doubles low";
		case LowDoublesSeparate:
			return "nello, doubles in a suit of their own";
		case NoTrump:
			return "no trump";
		}

		return null; // Can't happen unless this enum is changed without
						// changing above switch block
	};
}
