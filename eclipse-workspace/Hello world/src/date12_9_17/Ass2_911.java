package date12_9_17;

public class Ass2_911 {
	public static void main (String[] args) {
		badExample();
	}
	public static void badExample() {
		System. out . println (" Write some numbers ...");
		for (int i = 11; i < 31 ; i += 3) {
			System.out.print(i + " ");
		}
		System. out . println ("\n... and backwards .");
		for (int i = 29; i >= 11; i -= 3) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
