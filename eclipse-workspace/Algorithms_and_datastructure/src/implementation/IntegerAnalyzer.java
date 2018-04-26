package implementation;

import java.util.Arrays;
import java.util.Scanner;

public class IntegerAnalyzer {
	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int A[] = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = in.nextInt();
		}
		Arrays.sort(A);
		for(int i = 0; i<=((n-1)/2);i++) {
			int s = 0;
			for (int j = 0; j<=i; j++) {
				s = s + A[j] + A[n-j-1];
			}
			System.out.print(s + " ");
		}
	}
	
}
