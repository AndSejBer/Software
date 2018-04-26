package assingments;

import java.util.Scanner;

public class Part1 {
	public static void main(String[] args) {
		// Get an Array of length n
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] A = new int[n];
		int[] A2 = new int[2*n];
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}
		
		//Find smallest missing number
		for (int i = 0; i < n; i++) {
			A2[A[i]-1] = A[i];
		}
		int found = 0;
		for (int i = 0; i < n+1; i++) {
			if (A2[i] == 0) {
				found = i+1;
			}
		}
		System.out.println(found);
	}
}