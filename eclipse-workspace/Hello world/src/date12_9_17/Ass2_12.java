package date12_9_17;

public class Ass2_12 {
	public static void main (String[] args ) {
		for (int i = -1; i>=-6 ; i--) {
			for (int j = 1; j<=6;j++) {
				int k = Math.abs(i+j);
				System.out.print(k+" ");
			}
			System.out.println();
		}
	}
}