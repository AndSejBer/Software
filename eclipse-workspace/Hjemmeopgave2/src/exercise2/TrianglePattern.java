package exercise2;
import java.util.Arrays;


public class TrianglePattern {
	
		//Fields with a known height and width for a height x width-cell grid.
	    //An integer array initial that decides which cell in row 0 (top most row) that gets its value changed to 1/becomes black.
		//And a 2D integer array pattern, that will contain the whole height x width-cell grid with the right zeros and ones.
		int height, width;
		int[] initial;
		int[][] pattern;
		
		
		// Constructor
		public TrianglePattern(int n, int h, int[] Initial) {
			width = n;
			height = h;
			initial = Initial;
			//2D integer array is given its dimensions
			pattern = new int [height][width];
			//2D integer array pattern gets the right values by running the method MakePattern.
			MakePattern();
		}


		// A toString method.
		public String toString() {
			return "Width is: " + width + " Height is: " + height + " And the initial value is: " + initial;
		}
		

		//This method changes the value in the 2D integer array pattern to its right values (it basically inputs 1 and 0 into the cell grid)
		public void MakePattern() {
			//The first/topmost row 0 is made by a loop that changes the selected column to 1 in the first row.
			int a = 0;
			int b = 0;
			do {
				//An integer b is made to hold the first value in integer array initial.
				b = initial[a];
				//2D integer array pattern gets its valued changed to 1 at the selected column in row 0.
				pattern[0][b] = 1;
				a++;
				//This loop continues for all values in initial.
			} while(a < initial.length);
			
			//The rest of the grid is made here, and it runs from row 1 (2nd row) to the bottom
			int row = 0;
			do {
				//This loop checks every cell (except the first and last column) if it's supposed to be filled or not
				int col = 0;
				do {
					//The first if statement checks whether the cell above and to the left is white
					if (pattern[row][col] == 0) {
						//If the next 2 cells to the right are also white, then the currently checked cell becomes white.
						if(pattern[row][col + 1] == 0 && pattern[row][col + 2] == 0) {
							pattern[row + 1][col + 1] = 0;
						//Else is becomes black
						} else {
							pattern[row + 1][col + 1] = 1;
						}
					//if the cell is not white, it must be black
					} else {
						//And if the next 2 cells to the right are white, then the currently checked cell becomes black.
						if(pattern[row][col + 1] == 0 && pattern[row][col + 2] == 0) {
							pattern[row + 1][col + 1] = 1;
						//Else it becomes white
						} else {
							pattern[row + 1][col + 1] = 0;
						}
					}
					col++;
				} while(col + 1< width - 1);
				row++;
			} while(row < height - 1);
		}
		
		
		//Getters
		public int getN() {
			return width;
		}
		
		public int getH() {
			return height;
		}
		
		public int[] getInitial() {
			return initial;
		}
		
		public int getValueAt(int r, int c) {
			return pattern[r][c];
		}
	
}
