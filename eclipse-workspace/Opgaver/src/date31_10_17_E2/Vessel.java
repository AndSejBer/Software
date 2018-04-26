package date31_10_17_E2;

public class Vessel {
	private int volume;
	
	public Vessel () {
		
	}
	
	public Vessel(int newVolume) {
		volume=newVolume;
	}
	
	public String toString() {
		return "This vessel has a volume of " + volume;
	}
}
