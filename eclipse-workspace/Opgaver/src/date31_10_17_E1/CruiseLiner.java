package date31_10_17_E1;

public class CruiseLiner extends Ship {
	private String name;
	private int lenght, passengers;
	
	public CruiseLiner(String newName, int newLenght, int newPassengers) {
		name=newName;
		lenght=newLenght;
		passengers = newPassengers;
	}
	
	public String toString() {
		return super.toString(name, lenght) + "  cruise liner with " + passengers + " passenger capacity";
	}
}
