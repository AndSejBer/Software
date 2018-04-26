package implementation;

import java.io.*;
import java.util.*;

public class FindAPeak {
	// This method takes an array and should return the index
	// (0-indexed) of the top point found by Algorithm 1
	private int toppoint1(int[] a) {
		int x = 1;
		if (a[0] >= a[1]) {
			return 0;
		} else {
			while (x<a.length-1) {
				if(a[x-1] <= a[x] && a[x] >= a[x+1]) {
					return x;
				}else {
					x++;
				}
			}
		} 
		if (a[a.length-1] >= a[a.length-2]) {
			return a.length-1;
		}
		return x;
	}


	// ##################################################
	// # You do not need to modify anything below here. #
	// ##################################################

	public static void main(String[] args) throws IOException {
		new FindAPeak().run();
	}

	private void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		} 

		System.out.println(toppoint1(a));
	}
}
