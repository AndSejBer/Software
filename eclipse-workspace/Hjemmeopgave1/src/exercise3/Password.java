package exercise3;

public class Password {
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
	
	//Checks if a given password contains things that are not alpha numerical
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