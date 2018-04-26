package assingments;

import java.util.Scanner;

public class Part2 {
	public static void main(String[] args) {
		// Get an Array of length n
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}

		//Find smallest missing number
		int found = 1;
		for (int j = 0; j < n; j++) {
			if(found == A[j]) {
				found++;
				for (int i = 0; i < n; i++) {
					if (found == A[i]) {
						found ++;
					}
				}
			}
		}
		System.out.println(found);
	}
}
