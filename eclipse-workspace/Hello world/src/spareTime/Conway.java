package spareTime;

import java.util.concurrent.TimeUnit;

public class Conway {
	public static int sumAll(int...numbers){
		int result = 0;
		for(int i = 0 ; i < numbers.length; i++) {
			result += numbers[i];
		} 
		return result;
	}
	public static int getValue(int[][]arr,int i,int j) {		
		if (i < 0){
			return 0;
		}
		else if (j<0) {
			return 0;
		}
		else if (i > 9) {
			return 0;
		}
		else if (j>9) {
			return 0;
		}
		else
			return arr[i][j];
	}
	//sumAll(getValue(a,i-1,j-1),getValue(a,i,j-1),getValue(a,i+1,j-1),getValue(a,i-1,j),getValue(a,i-1,j+1),getValue(a,i,j+1),getValue(a,i+1,j+1),getValue(a,i+1,j))
	//sumAll(a[i-1][j-1],a[i][j-1],a[i+1][j-1],a[i-1][j],a[i-1][j+1],a[i][j+1],a[i+1][j+1],a[i+1][j])
	public static int[][] tick(int[][]a) {
		int[][]b=new int[a.length][a[0].length];
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				if (a[i][j]==0) {
					if (sumAll(getValue(a,i-1,j-1),getValue(a,i,j-1),getValue(a,i+1,j-1),getValue(a,i-1,j),getValue(a,i-1,j+1),getValue(a,i,j+1),getValue(a,i+1,j+1),getValue(a,i+1,j))==3) {
						b[i][j]=1;
					}	
					else {
						b[i][j]=0;
					}
				}
				else if (sumAll(getValue(a,i-1,j-1),getValue(a,i,j-1),getValue(a,i+1,j-1),getValue(a,i-1,j),getValue(a,i-1,j+1),getValue(a,i,j+1),getValue(a,i+1,j+1),getValue(a,i+1,j))==2) {
					b[i][j]=1;
				}
				else if (sumAll(getValue(a,i-1,j-1),getValue(a,i,j-1),getValue(a,i+1,j-1),getValue(a,i-1,j),getValue(a,i-1,j+1),getValue(a,i,j+1),getValue(a,i+1,j+1),getValue(a,i+1,j))==3) {
					b[i][j]=1;
				}
				else 
					b[i][j]=0;
			}			
		}
		return b;
	}	            
	public static void printArray(int[][]arr) {
		for (int q = 0; q < arr.length; q++) {
			System.out.println();
			for (int w=0;w<arr[0].length; w++) {
				if (arr[q][w]==0) {
					System.out.print("O");
				}
				else 
					System.out.print("X");
			}
		}
	}
	public static void main(String[] args) {
		int[][] startState = new int[][]{
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		};			  
		int[][] gol= startState;
		for (int it = 0; it < 20; it++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException ex) {}
			finally {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Iteration:");
				System.out.println(it);
				printArray(gol);
				int[][]newArray = new int[10][10];
				newArray=tick(gol);
				gol=newArray;
			}
		}
	}
}