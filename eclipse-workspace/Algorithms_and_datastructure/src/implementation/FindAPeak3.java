package implementation;

import java.io.*;
import java.util.*;

public class FindAPeak3 {
	// This method takes an array and the interval [i,j] it 
	// should 	search in, and it should then return the index
	// (0-indexed) of the top point found by Algorithm 3
	private int toppoint3(int[] a, int i, int j) {
		int x = (i+j)/2;
		if (x==0) {
			if (a[0] < a[1]) {
				return 1;
			} else {
				return 0;
			}
		} else if(x==j) {
			return j;
		}else if(a[x-1] <= a[x] && a[x] >= a[x+1]) {
			return x;
		} else {
			if (a[x-1] >= a[x]) {
				return toppoint3(a,i,x-1);
			} else if (a[x] <= a[x+1]) {
				return toppoint3(a,x+1,j);
			}
		}
		return -1;
	}


	// ##################################################
	// # You do not need to modify anything below here. #
	// ##################################################

	public static void main(String[] args) throws IOException {
		new FindAPeak3().run();
	}

	private void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		int[] a = new int[n];
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		} 

		System.out.println(toppoint3(a, 0, n-1));
	}
}
