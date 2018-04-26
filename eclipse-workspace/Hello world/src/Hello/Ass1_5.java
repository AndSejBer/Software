package Hello;

public class Ass1_5 {
	public static void main(String[] args) {
		top();
		walls();
		dtu();
		walls();
		top();
	}
	public static void top() {
		System.out.println("   /\\       /\\");
		System.out.println("  /  \\     /  \\");
		System.out.println(" /    \\   /    \\");
	}
	public static void walls() {
		System.out.println("+------+ +------+");
		System.out.println("I      I I      I");
		System.out.println("I      I I      I");
		System.out.println("+------+ +------+");
	}
	public static void dtu() {
		System.out.println("I D T UI I D T UI");
		System.out.println("ILyngbyI ILyngbyI");
	}
}