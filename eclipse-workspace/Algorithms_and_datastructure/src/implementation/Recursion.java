package implementation;

import java.util.Scanner;

public class Recursion {
	public static int n;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		System.out.print(f(n));
	}
	
	public static int f(int x) {
		if (x <= 2) {
			return x;
		} else {
			return 2*f(x-1)+f(x-2)-f(x-3);
		}
	}
}
