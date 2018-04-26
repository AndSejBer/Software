package spareTime;

import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Input your first number: ");
		float a = in.nextFloat();
		System.out.print("Input your second number: ");
		float b = in.nextFloat();
		System.out.println("Select you choice of modifier by number: ");
		System.out.println("1: +, 2: -, 3: * and 4: /");
		System.out.print("Input your selected number: ");
		int c = in.nextInt();		
		if (c==1) {
			System.out.println("Your choice of modifier is +, the result is: " + (a+b));
		} else if (c==2) {
			System.out.println("Your choice of modifier is -, the result is: " + (a-b));
		} else if (c==3) {
			System.out.println("Your choice of modifier is *, the result is: " + (a*b));
		} else if (c==4) {
			System.out.println("Your choice of modifier is /, the result is: " + (a/b));
		} else {
			System.out.println("You have not inputted a number within the specified range, please go back and input a valid modifier");			
		}
	}
}