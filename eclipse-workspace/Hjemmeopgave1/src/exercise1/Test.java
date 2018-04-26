package exercise1;

public class Test {
	public static void main(String[] args) {
		System.out.println(IntervalSearch.intervalContains(13, 8, 3));
        System.out.println(IntervalSearch.intervalContains(5, 11, 4));
        System.out.println(IntervalSearch.intervalContains(17, 17, 17));
        System.out.println(IntervalSearch.intervalContains(17, 17, 5));
        System.out.println(IntervalSearch.intervalContains(12, 18, 12));
        System.out.println(IntervalSearch.intervalContains(18, 12, 12));
        System.out.println(IntervalSearch.intervalContains(18, 24, 12));
        System.out.println(IntervalSearch.intervalContains(18, 144, 12));
        System.out.println(IntervalSearch.intervalContains(18, 14403434, 12));
        System.out.println(IntervalSearch.intervalContains(5, 11, 1));
        System.out.println(IntervalSearch.intervalContains(1, 5, 1));
	}
}
