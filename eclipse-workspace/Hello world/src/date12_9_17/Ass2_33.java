package date12_9_17;

public class Ass2_33 {
	public static void main (String[] args) {
		for (int i = 1; i<5; i++) {
			int x = 1;
			for (int j = 9; j>=0; j--) {
				for(int d = 1; d<=10-x; d++){
					System.out.print(j);
				}
				x++;
			}
			System.out.println();
		}
	}
}
