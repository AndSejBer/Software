package date12_9_17;

public class Ass2_91 {
	public static void main (String[] args) {
		loop1();
		loop2();
		loop3();
		loop4();
	}
	public static void loop1() {
		int k= 27;
		for (; k + 100 > 2 * k; k++) {
			System.out.println("k= " + k);
		}
	}
	public static void loop2() {
		for (int k = 11; k != 100; k = k + 2) {
			System.out.println("k= " + k);
		}
	}
	public static void loop3() {
		for (int k = 1; k < 10; k++) {
			k=k/2;
			System.out.println("k= " + k);
		}
	}
	public static void loop4() {
		for (int k=1;;) {
			System.out.println("k= " + k);
		}
	}
}
