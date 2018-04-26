package excercise2;

import java.awt.Point;

public class Flower extends Plant{
	//See Moss for detailed comments, as it will be the same.
	public Flower(Point pos) {
		super.position = pos;
		super.color = PeberholmConstantsAndUtilities.FLOWER_COLOR;
	}

	public Plant[] spreadSeeds() {
		int snum = PeberholmConstantsAndUtilities.FLOWER_SEED_NO;
		int rang = PeberholmConstantsAndUtilities.FLOWER_RANGE;
		Plant nPlants[] = new Plant[snum];
		for (int i = 0; i < snum; i++) {
			int rx = PeberholmConstantsAndUtilities.getRandomIntBetween(-rang, rang);
			int ry = PeberholmConstantsAndUtilities.getRandomIntBetween(-rang, rang);
			Point npl = new Point (0, 0); 
			npl.x = position.x + rx;
			npl.y= position.y + ry;
			nPlants[i] = new Flower(npl);
		}
		return nPlants;
	}
	
	public String toString() {
		return super.toString() + "Flower";
	}
	
}
