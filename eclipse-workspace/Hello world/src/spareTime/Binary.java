package spareTime;

import java.util.*;

public class Binary {
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
			//For wrong full inputs outputs the error and terminates the program
			System.out.print("You can only input the numbers 1 or 2 here");
			in.close();
			return;
		}
		//For "From binary" this part will run
		if (c==1) {
			System.out.println("Your number cannot be longer than 19 digits");
			System.out.print("Input your binary number: ");
			//The input is received as a string and will not give an exception ever, it is then tested if 
			//the string is a number see method isNotNum
			String input = in.next();
			boolean notNum = isNotANum(input);
			if(notNum == true) {
				in.close();
				return;
			}
			//After it is known that the string is a number, it is tested if it is too long for the 
			//current setup, see isNumTooLong method
			boolean tooLong = isNumTooLongA(input, c);
			if (tooLong == true) {
				in.close();
				return;
			}
			// It is tested if the number is indeed a binary number, if not the program is terminated, see isBinary method
			long number = Long.parseLong(input);
			boolean isBinaryNum = isBinary(number);
			if (isBinaryNum == false) {
				System.out.print("Your number is not a binary number");
				in.close();
				return;
			}
			System.out.print("Your number in decimal is: " + Long.parseLong(input,2));
		} else if (c==2) {
			System.out.println("Your number cannot be longer than 9 digits");
			System.out.print("Input your decimal number: ");
			String input = in.next();
			boolean notNum = isNotANum(input);
			if(notNum == true) {
				in.close();
				return;
			}
			boolean tooLong = isNumTooLongA(input, c);
			if (tooLong == true) {
				in.close();
				return;
			}
			int number = Integer.parseInt(input);
			System.out.print("Your number in binary is: " + Integer.toBinaryString(number));
		} else {
			System.out.println("The number inputted is not in the range specified, please go back and input a valid number for binary or decimal");			
		}
		in.close();
	}
	public static boolean isBinary(long number) {
		//This method converts the number to a string and then removes all 1s and 0s, if there is anything left it is not a binary number
		long copy = number;
		String s = "" +copy;
		s = s.replace("1", "");
        s = s.replace("0", "");
        if(s.isEmpty() == true) {
        	return true;
        } else {
        	return false;
        }
	}
	public static boolean isNumTooLongA(String s, int c) {
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