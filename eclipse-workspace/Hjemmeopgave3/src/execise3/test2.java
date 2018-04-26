package execise3;

public class test2 {
	public static void main(String[] args) {
		PassengerPlane p2 = new PassengerPlane("Boing","737",241);
		FreightPlane p3 = new FreightPlane("Antonov","124",110); 
		FreightPlane p1 = new FreightPlane("Boing","747F",112); 
		PassengerPlane p4 = new PassengerPlane("Airbus","321",192);
		PassengerPlane p5 = new PassengerPlane("Airbus","380",853); 
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p5);
	}
}
