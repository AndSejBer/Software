package excercise2;

import java.awt.Point;

public class Moss extends Plant{
	//The constructor sets the variables of it's mother method Plant
	public Moss(Point pos) {
		super.position = pos;
		super.color = PeberholmConstantsAndUtilities.MOSS_COLOR;
	}
	
	//Moss spreads in this method
	public Plant[] spreadSeeds() {
		//It imports the amount of times it should do it and how far away it can do it.
		int snum = PeberholmConstantsAndUtilities.MOSS_SEED_NO;
		int rang = PeberholmConstantsAndUtilities.MOSS_RANGE;
		//We initialize an array to contain the new plant's positions
		Plant nPlants[] = new Plant[snum];
		//We then make all the required plants and save them in the array
		for (int i = 0; i < snum; i++) {
			//We utilize the given random number generator, and after studying it, we provide it with the minimum
			//and maximum range, note that we don't give it maximum + 1 as this is done in the method
			int rx = PeberholmConstantsAndUtilities.getRandomIntBetween(-rang, rang);
			int ry = PeberholmConstantsAndUtilities.getRandomIntBetween(-rang, rang);
			//We initialize the point for the new plant and give it, it's new coordinates
			Point npl = new Point (0, 0); 
			npl.x = position.x + rx;
			npl.y= position.y + ry;
			//We the save the plant in the array
			nPlants[i] = new Moss(npl);
		}
		return nPlants;
	}
	
	public String toString() {
		return super.toString() + "Moss";
	}
}