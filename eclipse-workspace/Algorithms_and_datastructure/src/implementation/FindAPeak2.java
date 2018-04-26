package implementation;

import java.io.*;
import java.util.*;

public class FindAPeak2 {
		// This method takes an array and should return the index
	 	// (0-indexed) of the top point found by Algorithm 2
		private int toppoint2(int[] a) {
			int max = 0;
			for (int i = 0; i < a.length; i++) {
				if(a[i] > a[max]) {
					max=i;
				}
			}
			return max;
		}


		// ##################################################
		// # You do not need to modify anything below here. #
		// ##################################################

		public static void main(String[] args) throws IOException {
			new FindAPeak2().run();
		}

		private void run() throws IOException {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
			int n = Integer.parseInt(in.readLine());
			int[] a = new int[n];
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			} 
			
			System.out.println(toppoint2(a));
		}
}
