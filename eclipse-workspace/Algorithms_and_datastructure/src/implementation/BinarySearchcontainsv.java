package implementation;

import java.io.*;
import java.util.*;

public class BinarySearchcontainsv{

	// This method takes an array of integers as input parameters
	// and a value val. It should return the index of val in the array.
	// If val does not occur it should return -1.
	
	/*private int find(int[] numbers, int val) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == val) {
				return i;
			}
		}
		return -1;
	}*/
	
	private int find(int[] numbers, int val, int i, int j) {
		if (numbers.length == 0) {
			return -1;
		}
		if (numbers[(i+j)/2] == val) {
			return (i+j)/2;
		}else if (i == (i+j)/2 || i == j) {
			return -1;
		}else if (numbers[(i+j)/2] > val) {
			return find(numbers, val, i, (i+j)/2);
		} else {
			return find(numbers, val, (i+j)/2, j);
		}
	}
	

	// ##################################################
	// # You do not need to modify anything below here. #
	// ##################################################

	public static void main(String[] args) throws IOException {
		new BinarySearchcontainsv().run();
	}

	private void run() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] numbers = readIntArray(in);
		int[] queries = readIntArray(in);

		for (int i = 0; i < queries.length; i++) {
			int solution = find(numbers, queries[i],0 , numbers.length);
			System.out.println(solution);
		}
	}

	private int[] readIntArray(BufferedReader in) throws IOException {
		int length = Integer.parseInt(in.readLine());
		int[] array = new int[length];
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 0; i < length; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		return array;
	}
}
