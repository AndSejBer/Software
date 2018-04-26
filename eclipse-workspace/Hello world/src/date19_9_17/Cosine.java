package date19_9_17;

public class Cosine {
	public static void main(String[] args) {
		System.out.println(Cosine.cosine(0.5,1));
	}
	public static double cosine (double x, int k) {
		double result =0;
		x = x*Math.PI;
		for (int i = 1; i <=k; i++) {
			int ii = 2*i;
			for (int j = 1; j<=2*i; j++) {
				ii = ii * j;
			}
			double x2i = Math.pow(x, 2*i);
			double d = Math.pow((-1), i);
			double r = ((d*x2i)/(ii));
			result = result + r;
		}
		return result;
	}
}
