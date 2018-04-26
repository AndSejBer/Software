package assingments;

import java.util.Scanner;

public class Part3 {
	public static void main(String[] args) {
		// Get an Array of length n
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}

		//Find smallest missing number
		int sum = 0;
		for (int j = 0; j < n; j++) {
			sum += A[j];
		}
		int ans = (n+1)*(n+2)/2;
		System.out.println((ans - sum));
	}
}
