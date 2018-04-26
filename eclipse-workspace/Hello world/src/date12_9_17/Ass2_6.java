package date12_9_17;

public class Ass2_6 {
	public static void main (String[] args) {
		for (int k = 1; k<=22; k++) {
			System.out.print(" ");
		}
		head();
		System.out.println("*");
		for (int i = 1; i<=5; i++) {
			for (int k = 1; k<=26-i*5; k++) {
				System.out.print(" ");
			}
			body();
			for (int k = 1; k<=i*5; k++) {
				System.out.print(" ");
			}
			System.out.println("*");
			for (int k = 1; k<=26-i*5; k++) {
				System.out.print(" ");
			}
			legs();
			for (int k = 1; k<=i*5; k++) {
				System.out.print(" ");
			}
			System.out.println("*");
			if (i != 5) {
				for (int k = 1; k<=22-i*5; k++) {
					System.out.print(" ");
				}
				head();
				for (int k = 1; k<=i*5; k++) {
					System.out.print(" ");
				}
				System.out.println("*");
			}
		}
		System.out.print("********************************");
	}
	public static void head() {
		System.out.print("O  ******");
	}
	public static void body() {
		System.out.print("/|\\ *");
	}
	public static void legs() {
		System.out.print("/ \\ *");
	}
}