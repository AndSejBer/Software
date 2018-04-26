package date31_10_17_E1;

public class Tanker extends Ship{
	private String name;
	private int lenght, barrels;
	
	public Tanker(String newName, int newLenght, int newBarrels) {
		name=newName;
		lenght=newLenght;
		barrels = newBarrels;
	}
	
	public String toString() {
		return super.toString(name, lenght) + "  tanker with " + barrels + " barrels capacity";
	}
}
