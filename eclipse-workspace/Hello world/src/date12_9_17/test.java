package date12_9_17;

public class test {
	public static void main(String[] args) { 
		printDesign(); 
		} 
	private static void printDesign() { 
		final int WIDTH = 11;
		int numDashes = (WIDTH - 1) / 2; 
		for (int dashes = numDashes; dashes > 0; dashes--) { 
			for (int k = 0; k < dashes; k++) 
				System.out.print("-"); 
			int number = WIDTH - 2 * dashes;
			for (int k = 0; k < number; k++) 
				System.out.print(number); 
			for (int k = 0; k < dashes; k++) 
				System.out.print("-"); 
			System.out.println();
			} 
		}
}