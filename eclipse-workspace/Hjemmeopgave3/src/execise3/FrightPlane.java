package execise3;
public class FrightPlane extends Plane{
	protected int payload;
	
	public FrightPlane (String manufacturer, String type ,int payload) {
		super(manufacturer,type);
		this.payload = payload;
	}
	
	public String toString() {
		return super.toString() + " payload:" + payload;
	}
}
