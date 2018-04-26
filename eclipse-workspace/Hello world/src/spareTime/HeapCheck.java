package spareTime;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HeapCheck {

	public static void main(String[] args) {
		int n = 5;
		int[] A = new int[n];
		//for (int i = 0; i < n; i++) {
		//	A[i] = ThreadLocalRandom.current().nextInt(1, 2*n);
		//}
		//Arrays.sort(A);
		A[0] = 10;
		A[1] = 3;
		A[2] = 8;
		A[3] = 2;
		A[4] = 1;
		
		
		
		System.out.print("[");
		for (int i = 0; i < n-1; i++) {
			System.out.print(A[i] + ", ");
		}
		System.out.println(A[n-1] + " ]");

		boolean isHeap = true;

		int offset = 1;
		for (int i = 0; i < n; i++) {
			try {
				if(A[i] < A[i+offset] || A[i] < A[i+offset +1]) {
					isHeap = false;
					break;
				}
			} catch (Exception e) {

			}
			offset++;
		}

		System.out.println(isHeap);
	}
}
