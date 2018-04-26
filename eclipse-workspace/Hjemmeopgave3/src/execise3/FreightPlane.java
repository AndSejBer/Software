

public class FreightPlane extends Plane{
	protected int payload;
	
	//The constructor uses the same variables as Plane, but adds int payload
	public FreightPlane (String manufacturer, String type ,int payload) {
		super(manufacturer,type);
		this.payload = payload;
	}
	
	//We add '"payload: " + payload' to Plane's toString method
	public String toString() {
		return super.toString() + " payload:" + payload;
	}
}
