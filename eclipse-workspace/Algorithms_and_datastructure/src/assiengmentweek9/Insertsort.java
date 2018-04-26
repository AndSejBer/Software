package assiengmentweek9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Insertsort {
	
	public static void main(String[] args) {
		//Create an array of length n with random numbers from 0 - 2n
		int n = 100000000;
		int[] N = new int[n];
		int startupArraySort[] = new int[1];
		startupArraySort[0] = 1;
		Arrays.sort(startupArraySort);
		int N1 [] = new int[n];
		int rand;
		
		for (int i = 0; i < n; i++) {
			rand=ThreadLocalRandom.current().nextInt(0,2*n);
			N[i] = rand;
			N1[i] = rand;
		}
		
		System.out.println("Made the arrays");
		
		//Timer
		long timeStart;
		long timeEnd;
		
		//Sort the array smallest to largest
		timeStart = System.nanoTime();
		for (int i = 1; i < n; i++) {
			for (int j = i-1; j >= 0; j--) {
				if (N[i] > N[j]) {
					N[j+1] = N[i];
					j=-1;
				} else if (j == 0) {
					N[0] = N[i];
				}
			}
		}
		timeEnd = System.nanoTime();
		
		System.out.println("Sorted insert");
		
		System.out.println("Time taken in nanoseconds: " + ((timeEnd-timeStart)));
		
		timeStart = System.nanoTime();
		Arrays.sort(N1);
		timeEnd = System.nanoTime();
		
		System.out.println("Sorted Arraysort");
		
		System.out.println("Time taken in nanoseconds for java default sort: " + ((timeEnd-timeStart)));
		
	}
	
	
}
