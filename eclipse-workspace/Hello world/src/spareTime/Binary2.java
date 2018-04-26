package spareTime;

import java.util.*;

public class Binary2 {
	public static void main(String[] args) {
		//Scanner is imported to import inputs
		Scanner in = new Scanner(System.in);
		System.out.println("Select you choice of modifier by number: ");
		System.out.println("From binary: 1, from decimal: 2");
		System.out.print("Input your selected number: ");
		int c = 0;
		//First input is checked for wrong full inputs, and outputs error message, then terminates the program
		try {
			c = in.nextInt();
		} catch (InputMismatchException e) {
			//For inputs that are not numbers outputs the error and terminates the program
			System.out.print("You can only input the numbers 1 or 2 here");
			in.close();
			return;
		}
		//For inputs c != 1 or 2 outputs the error and terminates the program
		if (c!=1) {
			if (c!=2) {
				System.out.print("You can only input the numbers 1 or 2 here");
				in.close();
				return;
			}
		}
		//For "From binary" this part will run
		if (c==1) {
			System.out.println("Your number cannot be longer than 19 digits");
			System.out.print("Input your binary number: ");
			/*The input is received as a string and will not give an exception ever, it is then tested if 
			  the string is a number see method isNotNum
			  After it is known that the string is a number, it is tested if it is too long for the 
			  current setup, see isNumTooLong method
			  It is tested if the number is indeed a binary number, if not the program is terminated, see isBinary method
			  This is all done in the testNumber method that collects all the different tests */
			String input = in.next();
			boolean notNum = testNumber(input, c);
			if(notNum == false) {
				in.close();
				return;
			}
			System.out.print("Your number in decimal is: " + Long.parseLong(input,2));
			// For 'From decimal', this part will run
		} else if (c==2) {
			System.out.println("Your number cannot be longer than 9 digits");
			System.out.print("Input your decimal number: ");
			String input = in.next();
			// The test is run again to make sure this number also is as required
			boolean numTest = testNumber(input, c);
			if(numTest == false) {
				in.close();
				return;
			}
			// After the test, the string is converted to an int and then changed to a binary string and printed
			int number = Integer.parseInt(input);
			System.out.print("Your number in binary is: " + Integer.toBinaryString(number));
		} 
		in.close();
	}
	
	public static boolean testNumber(String number, int c) {
		String s = number + "";
		// Test if it's a number
		boolean a = isNotANum(s);
		if(a==true) {
			return false;
		}
		// Test if it's too long
		boolean b = isNumTooLong(s, c);
		if (b == true) {
			return false;
		}
		// Test if number is binary, but only if that is required
		long number1 = Long.parseLong(s);
		if (c==1) {
			boolean d = isBinary(number1);
			if (d == false) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isBinary (long number) {
		//This method converts the number to a string and then removes all 1s and 0s, if there is anything left it is not a binary number
		String s = "" +number;
		s = s.replace("1", "");
		s = s.replace("0", "");
		if(s.isEmpty() == false) {
			System.out.print("Your number is not a binary number");
			return false;
		}
		return true;
	}
	
	public static boolean isNumTooLong(String s, int c) {
		/* Because there is a different length for the two methods, there are two checks, one for c=1 and one for c=2.
		 * essentially it tries to parse it into the required length, and is it throws and exception the method returns true
		 * which causes the main method to terminate, while giving the error "Number too long"
		 */
		if (c == 1) {
			try {
				long test = Long.parseLong(s);
				return false;
			} catch (java.lang.NumberFormatException e) {
				System.out.print("Number too long");
				return true;
			}
		} else if (c == 2) {
			try {
				int test = Integer.parseInt(s);
				return false;
			}catch (java.lang.NumberFormatException e) {
				System.out.print("Number too long");
				return true;
			}
		} else
		return false;
	}
	
	public static boolean isNotANum(String number) {
		String s = number + "";
		/*This block tries to convert the string to a float, which it cannot if it is not a number, 
		 *This boolean method will return true after outputting the error message, in the main method 
		 *this true causes the program to terminate
		 */
		try {
		float a = Float.parseFloat(s);
		} catch (java.lang.NumberFormatException e) {
			System.out.print("Input " + s + " is not a number");
			return true;
		}
		return false;
	}
}