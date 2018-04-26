package excercise2;

import java.awt.Point;

public class Bush extends Plant{
	//See Moss for detailed comments, as it will be the same.
	public Bush(Point pos) {
		super.position = pos;
		super.color = PeberholmConstantsAndUtilities.BUSH_COLOR;
	}

	public Plant[] spreadSeeds() {
		int snum = PeberholmConstantsAndUtilities.BUSH_SEED_NO;
		int rang = PeberholmConstantsAndUtilities.BUSH_RANGE;
		Plant nPlants[] = new Plant[snum];
		for (int i = 0; i < snum; i++) {
			int rx = PeberholmConstantsAndUtilities.getRandomIntBetween(-rang, rang);
			int ry = PeberholmConstantsAndUtilities.getRandomIntBetween(-rang, rang);
			Point npl = new Point (position.x + rx, position.y + ry); 
			nPlants[i] = new Bush(npl);
		}
		return nPlants;
	}
	
	public String toString() {
		return super.toString() + "Bush";
	}
}
