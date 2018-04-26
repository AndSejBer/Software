package date12_9_17;

public class Ass2_5 {
	public static void main (String[] args) {
		for (int i = 1; i<10; i += 2) {
			for (int j = 1; j<=5-((i-1)/2); j++) {
				System.out.print("-");
			}
			for (int j = 1; j<=i; j++) {
				System.out.print(i);
			}
			for (int j = 1; j<=5-((i-1)/2); j++) {
				System.out.print("-");
			}
			System.out.println();
		}
	}
}