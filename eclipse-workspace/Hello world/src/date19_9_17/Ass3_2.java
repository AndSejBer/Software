package date19_9_17;

import java.util.Scanner;

public class Ass3_2 {


	/**
	 * @author Paul Fischer
	 *
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input start value");
	    int start = scanner.nextInt();
	    System.out.println("Input end value");
	    int end = scanner.nextInt();
	    int sum = computeSum(start,end);
	    System.out.println("Sum is of integers from "+start+" to "+
	    end+" is "+sum);
	    scanner.close();
	}
	   // Implement method  computeSum  here.
	public static int computeSum (int start, int end) {
		int result = start;
		for (int i = start+1; i <=end; i++) {
			result += i;
		}
		return result;
	}
}

