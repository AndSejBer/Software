package implementation;

import java.util.*;

public class FindAPeak2d {
	private static int[][] a;
	private static int n;
	
	public static int[] findPeak() {
		int[] sol = new int[2];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i==0) {
					if (j==0) {
						if(a[0][0]>a[1][0] && a[0][0]>a[0][1]) {
							sol[0]=0;
							sol[1]=0;
							return sol;
						}
					} else {
						if(a[i][j] >= a[i][j-1] 
								&& a[i][j] >= a[i+1][j] 
								&& a[i][j] >= a[i][j+1]) {
							sol[0]=i;
							sol[1]=j;
							return sol;
						}
					}
				} else {
					if (j==0) {
						if(a[i][j] >= a[i-1][j] 
								&& a[i][j]>a[i+1][j] 
								&& a[i][j]>a[i][j+1]) {
							sol[0]=i;
							sol[1]=j;
							return sol;
						}
					} else {
						if(a[i][j] >= a[i][j-1]
								&& a[i][j] >= a[i-1][j] 
								&& a[i][j] >= a[i+1][j] 
								&& a[i][j] >= a[i][j+1]) {
							sol[0]=i;
							sol[1]=j;
							return sol;
						}
					}
				}
			}
		}
		sol[0]=-1;
		sol[1]=-1;
		return sol;
		
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n=in.nextInt();
		a=new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = in.nextInt();
			}
		}
		int[] result = findPeak();
		System.out.println(result[0] + ", " + result[1]);
	}
}
