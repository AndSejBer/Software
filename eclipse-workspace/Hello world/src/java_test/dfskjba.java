package java_test;

public class dfskjba {
	public static void main(String[] args) {
		int [][] array = new int [10][5];
		int counter = 0;
		for ( int i = 0; i < array . length ; i ++) {
			counter += 10;
			for ( int j = 0; j < array [ i ]. length ; j ++) {
				array [ i ][ j ] = counter ;
				counter ++;
			}
		}
		for (int i = 0; i<array.length;i++) {
			for (int j = 0; j<array[i].length;j++ ) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}
