package execise3;

public class test {
	public static void main(String[] args) {
		Plane p1 = new Plane("Cessna","Skyhawk");
		Plane p2 = new Plane("Piper","Matix");
		Plane p3 = new Plane("Piper","Matix");
		String fuckAll = "lol";

		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		//p1.setId(10);
		//p2.setId(20);
		//p3.setId(30);
		System.out.println(p1.equals(fuckAll));
		System.out.println(p1.equals(p2));
		System.out.println(p1.equals(p3));
		System.out.println(p2.equals(p1));
		System.out.println(p2.equals(p2));
		System.out.println(p2.equals(p3));
		System.out.println(p3.equals(p1));
		System.out.println(p3.equals(p2));
		System.out.println(p3.equals(p3));

		p2.setId(27);
		System.out.println(p2.equals(p1));
		System.out.println(p2.equals(p2));
		System.out.println(p2.equals(p3));
	}
}

