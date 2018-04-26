package exercise3;

import java.util.Scanner;

public class AccessControl {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//To get a username it calls the method inputUsername
		String username = inputUsername(in);
		//To get a password it calls the method inputPassword
		String password1 = inputPassword(in);
		System.out.println("*** User " + username + " is registered.");
		int choices = 0;
		// As we want the program to continue (until the user exits) the entire actual program runs in a do while loop, that ends when the user stops it
		do {
			//We prompt the user for a login see method login
			if (login(username, password1, in)) {
				System.out.println("*** User " + username + " is Logged on.");
				do {
					//We give the small loggedOn intro
					loggedOn();
					choices = 0;
					//We want the user to pick one of 3 things, and keeps asking the user for a number until he picks a valid number
					while (!(choices == 1 || choices == 2 || choices ==3)) {
						System.out.print("Please select:");
						choices = in.nextInt();
						System.out.println();
					}
					//If he wants to change password we call the method inputPassword
					if (choices == 1) {
						password1 = inputPassword(in);
						System.out.println("*** Password changed for user " + username);
					}
				//This loops continues as long as the user does not input 2 or 3
				}while (!(choices == 2 || choices ==3));
				//If he inputs 2, he wants to log off and he is then logged off, and is prompted to log in again
				if (choices == 2) {
					System.out.println("*** User " + username + " is logged off");
				}
			//If he attempts a wrong password 3 times the program shuts down see login method
			} else {
				in.close();
				return;
			}
		//If the user picks 3 user is logged off and the program shuts down
		} while (choices != 3);
		System.out.println("*** User " + username + " is logged off");
		System.out.println("*** System shutting down.");
		in.close();
	}
	
	//This method prompts the user for a username and checks if it's valid
	public static String inputUsername(Scanner in) {
		String username = "0";
		char firstLetter = 0;
		do {
			System.out.print("Please enter a non-empty username:");
			username = in.nextLine();
			System.out.println();
			firstLetter = username.charAt(0);
			//Checks if there are any spaces in the name if so it sets firstLetter to a non letter, and so the loop continues
			if (username.contains(" ")) {
				firstLetter = 0;
			}
			//The loop stops when the firstLetter is a letter (notice that even if it is, firstLetter will be 0 if there is a space)
		} while (!(Character.isLetter(firstLetter)));
		return username;
	}
	
	//This method prompts the user for a new password (both first time and for changing password)
	public static String inputPassword(Scanner in) {
		//Sets 2 passwords that don't pass the requirements for a password and dont match each other, to make sure it runs the while loops
		String password = "";
		String password2 =" ";
		//This runs until you've repeated a password
		while (!(password.equals(password2))) {
			//This runs until you enter a valid password
			while (!(checkPWD(password))){
				System.out.print("Please enter a password    :");
				password = in.next();
				System.out.println();
			}
			System.out.print("Please repeat the password :");
			password2 = in.next();
			System.out.println();
			//If they don't match it will restart the progress
			if (!(password.equals(password2))) {
				password = "";
			}
		}
		return password;
	}
	
	//This method gets a user name from the user, and checks if it is the correct user name, if that is true it prompts for a password and checks that.
	public static boolean login(String username, String pass2,Scanner in) {
		//The variables for the user inputed user name and password are initialized to wrong full inputs
		String pass1 = "0";
		String user = "0";
		//The user only has 3 attempts when typing in the password hence:
		int attempts = 0;
		//As long as the user inputed user name is not the right one, it prompts for a user name
		while (!(user.equals(username))) {
			System.out.print("To logon enter username:");
			user = in.next();
			System.out.println();
		}
		/*As long as the password is not correct it prompts for a password, but everytime you get it wrong the attempts counter goes up,
		 *and when that hits 3, the program shuts down*/
		while (!(pass2.equals(pass1))) {
			System.out.print("To logon enter password:");
			pass1 = in.next();
			System.out.println();
			attempts ++;
			if (attempts >=3) {
				System.out.println("*** System shutting down.");
				return false;
			}
		}
		//If the program gets a correct user name and the corresponding password, this method returns true
		return true;
	}
	
	//This method prints out the small intro you get everytime you log on and finish changing passwords
	public static void loggedOn() {
		System.out.println("You now have the following choices:");
		System.out.println("  1 - Change Password");
		System.out.println("  2 - Log off");
		System.out.println("  3 - Shut down");
	}
	
	//Copy pasted from the Password class as we were unsure if code judge could handle a call to that class.
	public static boolean checkPWD (String password ) {
		//If password is less than 8 characters it is not permitted
		if(password.length() < 8) {
			return false;
		}
		//Checks if the password is alpha numerical, see method charCheck
		if (!(charCheck(password))){
			return false;
		}
		//We check the rest of the requirements in the method checkRest and return the result
		return checkRest(password);
	}
	
	public static boolean charCheck(String s) {
		//As we want to check every character we index the string
		int index = 0;
		//The char we are looking at now
		char alpha;
		//For as long as we still have letters to check
		while (index < s.length()) {
			//Take the current letter
			alpha = s.charAt(index);
			//Check if it is not either a letter or a number, if it's neither the password contains non alpha numerical symbols and it returns false
			if (!(Character.isLetter(alpha))) {
				if (!(Character.isDigit(alpha))) {
					return false;
				}
			}
			index ++;
		}
		return true;
	}
	
	public static boolean checkRest (String password) {
		//These are needed to check other requirements are met
		//The index of the password we are looking at
		int index = 0;
		//Counts the amount of letters
		int numLetters = 0;
		//Counts the amount of numbers
		int numNumbers = 0;
		//Changes to true if numLetters is more than or equal to 2
		boolean letters2 = false;
		//Changes to true if numNumbers is more than or equal to 2
		boolean numbers2 = false;
		//Changes to true if there is 1 uppercase
		boolean uppercase = false;
		//Changes to true if there is 1 lowercase
		boolean lowercase = false;
		//The character we are looking at
		char ch;
		/*For each letter in the password it is checked if it is a letter and if it is uppercase or lowercase, and if it is not a letter,
		 *it must be a number. The loop stops when index is more or equal to the length of the password, because the index starts at 0 but length 
		 *counts every char from 1*/
		while (index < password.length()) {
			ch = password.charAt(index);
			//Check if it is letter
			if(Character.isLetter(ch)) {
				numLetters ++;
				//Checks if it is uppercase or lowercase
				if (Character.isUpperCase(ch)) {
					uppercase = true;
				} else {
					lowercase = true;
				}
			} else {
				//If it is not a letter, it must be a number
				numNumbers ++;
			}
			//We check the next number
			index ++;
		}
		//Checks if there are 2 or more letters, if so letters2 is changed to true
		if (numLetters >= 2) {
			letters2 = true;
		}
		//Checks if there are 2 or more numbers, if so numbers2 is changed to true
		if(numNumbers >= 2) {
			numbers2 = true;
		}
		//If all the tests are true, then it's a valid password
		return numbers2 && letters2 && uppercase && lowercase;
	}
}