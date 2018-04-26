package Hello;

public class Redundacy {
	public static void Wee() {
		System.out.printf("Wee ");
	}
	
	public static void Woo() {
		System.out.print("Woo ");
	}
	
	public static void main(String[] args) {
		Wee();
		Woo();
		Wee();
		Woo();
	}
}
