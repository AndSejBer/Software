

public class PassengerPlane extends Plane{
	protected int seats;
	
	//The constructor uses the same variables as Plane, but adds int seats
	public PassengerPlane (String manufacturer, String type ,int seats) {
		super(manufacturer,type);
		this.seats = seats;
	}
	
	//We add '"seats: " + seats' to Plane's toString method
	public String toString() {
		return super.toString() + " seats:" + seats;
	}
}
