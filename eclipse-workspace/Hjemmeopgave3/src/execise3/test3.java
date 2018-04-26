package execise3;

public class test3 {
    public static void main(String[] args) {
                Airport cph = new Airport();
         
                Plane p1 = new Plane("Cessna", "Skyhawk");
                PassengerPlane p2 = new PassengerPlane("Boing", "737", 241);
                FreightPlane p3 = new FreightPlane("Antonov", "124", 110);
                PassengerPlane p4 = new PassengerPlane("Boing", "737", 241);
                PassengerPlane p5 = new PassengerPlane("Trash", "123", 99);
                p5.setId(2);
         
                cph.land(p1);
                cph.land(p2);
                cph.land(p3);
                cph.land(p4);
                System.out.println(cph);
         
                cph.land(p5);
                System.out.println(cph);
         
                cph.start(2);
                System.out.println(cph);
         
                cph.start(0);
                System.out.println(cph);
                cph.start(5);
                System.out.println(cph);
                cph.start(1);
                System.out.println(cph);
                cph.start(3);
                System.out.println(cph);
                
         
    }
}