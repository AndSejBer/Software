package date19_9_17;

public class Factorial {
	public static int factorial (int n) {
		int result = 1;
		for (int i = 1; i<=n; i++) {
			result *= i;
		}
		return result;
	}
}
