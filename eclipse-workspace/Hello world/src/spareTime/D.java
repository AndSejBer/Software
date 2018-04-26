package spareTime;

import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in =new Scanner(System.in);
		System.out.print("Input your number: ");
		int a = in.nextInt();
		for (int i =1; i<=10; i++) {
			System.out.println(a + "x" + i + "=" + a * i);
		}
	}
}
