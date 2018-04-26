package date31_10_17_E1;

public class ContainerShip extends Ship {
	private String name;
	private int lenght, teu;
	
	public ContainerShip(String newName, int newLenght, int newTeu) {
		name=newName;
		lenght=newLenght;
		teu = newTeu;
	}
	
	public String toString() {
		return super.toString(name, lenght) + "  container carrier with " + teu + " TEU";
	}
}
