package date19_9_17;

import java.util.*;

public class Ass3_3 {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		printNumbers(x);
		in.close();
	}
	public static void printNumbers(int x) {
		for (int i = 1; i <=x; i++) {
			System.out.print("[" + i + "]");
		}
	}
}
