package exercise3;
import java.lang.Math;


public class MovingPoint {
	//We create our variables.
	double x;
	double y;
	double direction;
	double speed;
	
	//Constructs the MovingPoint object at origin and gives it initial starting values.
	public MovingPoint () {
		direction = 90;
		speed = 0;
		x = 0;
		y = 0;
	}
	//Here we make a constructor that allows the input of values without having to input them one by one.
	public MovingPoint (double xe , double ye , double directione , double speede ) {
		x = xe;
		y = ye;
		direction = directione;
		speed = speede;
	}
	//Here we give our point the ability to move, and to return the proper coordinates.
	public void move (double duration ) {
		x = x + speed * duration * Math.cos(Math.toRadians(direction));
		y = y + speed * duration * Math.sin(Math.toRadians(direction));
	}
	//Next we give our point the ability to turn
	public void turnBy (double angle ) {
		direction += angle;
		//We add an if statement for situations where our directions value becomes negative.
		if (direction < 0) {
			direction = 360+direction%360;
		}
		//Next we add an if statements for situations where our value for directions exceeds 360.
		if (direction >= 360)
			direction = direction%360;
			
	}
	//Here we make our point capable of changing speed (acceleration and deacceleration).
	public void accelerateBy (double change ) {
		speed += change;
		//We add an if statement to make sure our point dosn't exceed max speed.
		if (speed > 20)
			speed = 20;
		//We also add an if statement to make sure that our points speed dosn't become negative.
		else if (speed < 0)
			speed = 0;
	}
	//Here we have our return statement.
	public String toString () {
		return "[" + x + ";" + y + "]" + direction + " " + speed;
	}
}
