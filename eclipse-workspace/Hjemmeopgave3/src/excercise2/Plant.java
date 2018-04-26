package excercise2;

import java.awt.Color;
import java.awt.Point;

public abstract class Plant {
	protected Point position;
	protected Color color;

	public abstract Plant[] spreadSeeds(); 
	
	public Color getColor() {
		return color;
	}
	public Point getPosition() {
		return position;
	}
	
	public String toString() {
		return "position ["+position.x+";"+position.y+"]";
	}
}