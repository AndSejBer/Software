package exercise1;
// Program for testing if an int to a power is in a given interval
public class IntervalSearch {
	public static boolean intervalContains (int g1 , int g2 , int b ) {
		//Run through different powers and checks if it's in the given interval
		int max = g2;
		if (g2<g1) {
			max = g1;
		}
		for (int i = 0; Math.pow(b, i)<=max; i++) {
			//Checks if b^i fits in the interval [g1; g2]
			if(g1<=Math.pow(b, i)) {
				if(Math.pow(b, i)<=g2) {
					return true;
				}
			}
			//Checks if b^i fits in the interval [g2; g1]
			if(g2<=Math.pow(b, i)) {
				if(Math.pow(b, i)<=g1) {
					return true;
				}
			}
			//For the special case of b = 1, then the for loop wont close, and so this is checked
			if (b == 1) {
				return false;
			}
		}
		//If b^i doesn't fit in the given interval, the for loop closes and returns false
		return false;
	}
}
