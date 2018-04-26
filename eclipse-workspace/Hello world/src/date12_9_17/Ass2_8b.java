package date12_9_17;

public class Ass2_8b {
	public static void main (String[] args) {
		double a = 0;
		for (int i = 0; i <2000; i++) {
			for (int j = 0; j<=2000; j++) {
				for (int k = 0; k<2000; k++)
					a++;
			}
		}
		System.out.print(a);
	}
}

