package exercise2;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

//Set up a small game of catch
public class PredatorPrayTeleport {
	public static void runSimulation (int n , int s , int t ) {
		//Checks if the integer parameters are outside the expected values
		if (n <= 0 || s < 2 || t < 0) {
			System.out.println("n=" + n + " s=" + s + " t=" + t);
			System.out.println("Illegal Parameters!");
			return;
		}
		System.out.println("n=" + n + " s=" + s + " t=" + t);
		//Make the points that define the position of prey and predator, see makePoint method
		Point prey = makePoint(n);
		Point predator = makePoint(n);
		//Runs t interations/attempts of the chase
		for (int i = 0; i<=t; i++) {
			//Prints the positions of prey and predator
			System.out.print("[" + prey.x + ";" + prey.y + "] " );
			System.out.println("[" + predator.x + ";" + predator.y + "]");	
			//Checks if the prey has been caught, if so it yells Caught! and then terminates the program
			if (prey.x==predator.x) {
				if(prey.y==predator.y) {
					System.out.println("Catch!");
					return;
				}
			}
			//Gives new location to prey, see newPrey
			prey = newPrey(prey,s,n);
			//Gives new location to predator, see newPredator
			predator =newPredator(predator, prey, s);
		}
	}
	//Tries to get predator on top of prey
	public static Point newPredator (Point predator, Point prey, int s) {
		//Find the difference in x and y coordinates, between prey and predator
		int xReCal=prey.x - predator.x;
		int yReCal=prey.y - predator.y;
		//First for x
		//For a negative difference first check if difference is less than the allowed max movement, if so move that amount,
		//otherwise move s
		if (xReCal<0) {
			if (xReCal >=-s) {
				predator.x += xReCal;
			} else {
				predator.x += -s;
			}
		//For a positive difference first check if difference is less than the allowed max movement, if so move that amount,
		//otherwise move s
		} else if (xReCal>0) {
			if (xReCal <= s) {
				predator.x += xReCal;
			} else {
				predator.x += s;
			}
		//Notice that for xReCal = 0, nothing is done to x
		}
		//Same is done for y
		if (yReCal<0) {
			if (yReCal >=-s) {
				predator.y += yReCal;
			} else {
				predator.y += -s;
			}
		} else if (yReCal>0) {
			if (yReCal <= s) {
				predator.y += yReCal;
			} else {
				predator.y += s;
			}
		}
		//Returns the new position
		return predator;
	}
	
	//Adds a random number in the interval [-s;s] to both prey's x and y position
	public static Point newPrey (Point prey, int s, int n) {
		//This is made to prevent the prey from double teleporting
		int teleagain = 0;
		//Checks if x and y position is divisible by s, if so it teleports to a new random point
		if (prey.x % s == 0 && prey.y % s == 0 && teleagain == 0) {
			prey = makePoint(n);
			teleagain = 1;
			return prey;
		}
		int randomAdd = ThreadLocalRandom.current().nextInt(-s,s+1);
		prey.x += randomAdd;
		randomAdd = ThreadLocalRandom.current().nextInt(-s,s+1);
		prey.y += randomAdd;
		//Checks if prey has gone out of the board, and puts it back if so
			if (prey.x >=n) {
				prey.x = n-1;
			}else if (prey.x <0) {
				prey.x = 0;
			}
			if (prey.y >=n) {
				prey.y = n-1;
				//If the program is here, there is not a double teleport, and it can teleport again
				teleagain = 0;
			}else if (prey.y <0) {
				prey.y = 0;
				teleagain = 0;
			}
		return prey;
	}
	
	//This method creates a random point in the specified grid n*n
	public static Point makePoint (int n) {
		int x = ThreadLocalRandom.current().nextInt(0,n-1);
		int y = ThreadLocalRandom.current().nextInt(0,n-1);
		Point a = new Point(x,y);
		return a;
	}
}