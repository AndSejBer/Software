package exercise1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextAnalysis {

	private String[] differentWords;
	private File current;

	//Constructs the TextAnalysis object and limits the amount of different words and also preps the file.
	public TextAnalysis (String sourceFileName , int maxNoOfWords) {
		current = new File(sourceFileName);
		differentWords = new String[maxNoOfWords];
		//We fill the array to make sure there is no index out of bounds, as there can be no numerals in the words after splitting 0
		//is basically the same as null in this case
		Arrays.fill(differentWords,"0");
	}

	// All the methods of the object
	//Counts the amount of words by splitting the file into line and counting the words in the line
	public int wordCount() {
		//We create the counter and set it to zero.
		int wordCount=0;
		//We setup the scanner, which is a little cluttered because of the fact that we have to catch the
		//exception that Scanner might throw on receiving a file, but it still needs be initialized somewhere, for it to compile
		Scanner scanCurrent = new Scanner(System.in);
		try {
			scanCurrent = new Scanner(current);
		} catch (FileNotFoundException e){
			System.err.println("File not Found!" + e.getMessage());
		}
		//We go through the file line by line
		do {
			//We extract a line from the file
			String line = scanCurrent.nextLine();
			//We split the line into individual words, and save them in a string array with only letters 
			//as tokens, everything else is a space
			String[] words = line.split("[^a-zA-Z]+");
			int i = 0;
			//We go through every word
			do {
				//We check if the word is empty and if not we increment the counter
				String word=words[i];;
				if (!(word.isEmpty())) {
					wordCount++;
				}
				i++;
			} while (i < words.length);
		} while (scanCurrent.hasNext());
		scanCurrent.close();
		return wordCount;
	}

	//This counts the amount of different words in the text
	public int getNoOfDifferentWords() {
		//Again the scanner is annoying
		Scanner scanCurrent = new Scanner(System.in);
		try {
			scanCurrent = new Scanner(current);
		} catch (FileNotFoundException e){
			System.err.println("File not Found!" + e.getMessage());
		}
		//The counter is initialized and set to 0
		int diffWordsCounter = 0;
		//We look at the text line by line
		do {
			String line = scanCurrent.nextLine();
			//We split the line into words with only letters
			String[] words = line.split("[^a-zA-Z]+");
			int i = 0;
			do {
				//For every word we make it lowercase and checks if we have already seen it
				String word = words[i].toLowerCase();
				//We have made a method that checks a string against a string array and returns true if it's not in the array
				//See diffWord
				if(diffWord(word, differentWords, diffWordsCounter)) {
					//If it's a new word we insert it in the array and increment the counter
					differentWords[diffWordsCounter]=word;
					diffWordsCounter++;
				}
				i++;
			} while (i < words.length);
		} while (scanCurrent.hasNext());
		scanCurrent.close();
		return diffWordsCounter;
	}

	//This finds the amount of repetitions in a row
	public int getNoOfRepetitions() {
		//We initialize our counter and make the scanner
		int numrep = 0;
		Scanner scanCurrent = new Scanner(System.in);
		try {
			scanCurrent = new Scanner(current);
		} catch (FileNotFoundException e){
			System.err.println("File not Found!" + e.getMessage());
		}
		//We make a string to save the previous word we've used and set it to something the word will never become.
		String wordprev = "1234";
		//We split the text into lines
		do {
			String line = scanCurrent.nextLine();
			//We split the line into words with only letters
			String[] words = line.split("[^a-zA-Z]+");
			int i = 0;
			//For every word
			do {
				//We take the lower case word and check if it's the same as the previous word
				String word = words[i].toLowerCase();
				//If it's empty we change it up
				if (word.isEmpty()) {
					word ="12";
				}
				//If they are the same we increment the counter
				if(word.equals(wordprev)) {
					numrep++;
				}
				//If the word was not empty we set the previous word to the current and start over
				if (!(word.equals("12"))) {
					wordprev = word;
				}
				i++;
			} while (i < words.length);
		} while (scanCurrent.hasNext());
		scanCurrent.close();
		return numrep;
	}

	//End of object methods

	//This method checks if a string is included in a array
	private static boolean diffWord (String word, String[] diff, int diffCount) {
		int j = 0;
		//For as long as there is things in the array we want to check it against our word.
		do {
			//If the words matches the method returns false otherwise it continues
			if(word.equals(diff[j])) {
				return false;
			}
			j++;
			//If the current array string is 0, we know that there are no more words to check with, and
			//our word is new, and we return true for a new word
			if(diff[j].equals("0")) {
				return true;
			}
		}while (j<diffCount);
		//Redundant return for the compilers happiness
		return true;
	}
}
