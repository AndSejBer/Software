
import java.util.ArrayList;

public class Airport {
	//We initialize an ArrayList to keep track of the planes currently on the airport
	//And we make an int Index that keeps track of the id numbers on them
	private ArrayList<Plane> planes = new ArrayList<Plane>();
	private int index = 0;

	public Airport() {

	}

	//Lands a plane if index is 0 (first plane to land on the airport), or if the id is new.
	public void land(Plane nplane) {
		//We found out that the for loop or if statement wouldn't work if if was the first plane landing
		//so we simply test if any planes have landed before it.
		if(index == 0) {
		} else {
			//If it's not the first plane, we run through all the planes we have
			//and check their ID numbers
			for (Plane plane : planes) {
				if ((plane.getId() == nplane.getId())) {
					//If they match the method terminates and nothing happens
					//(The plane doesn't land)
					return;
				}
			}
		}
		//If it lands, we give it a new ID over zero, and add it to the landed planes array
		nplane.id = index + 1;
		planes.add(nplane);
		index++;
	}
	
	//Removes a plane from the airport if it's landed
	public void start(int nid) {
		for (Plane plane : planes) {
			if (plane.id == nid) {
				planes.remove(plane);
				return;
			}
		}
	}
	//Prints all the planes, one plane per line
	public String toString() {
		String msg = "";
		for (Plane plane : planes) {
			msg += plane.toString() + "\r\n";
		}
		return msg;
	}
}
