package date31_10_17_E1;

public class Ship {
	private String name;
	private int lenght;
	
	public Ship(String newName, int newLenght) {
		name=newName;
		lenght=newLenght;
	}
	
	public Ship() {
		
	}
	
	public String toString() {
		return "Ship " + name + " l=" + lenght;
	}
	
	public String toString(String name, int lenght) {
		return "Ship " + name + " l=" + lenght;
	}
}
