package date31_10_17_E2;

public class Bottle extends Vessel {
	private int volume;
	private String content;
	
	public Bottle () {
		
	}
	
	public Bottle (int newVolume, String newContent) {
		volume = newVolume;
		content = newContent;
	}
	
	public String toString() {
		return "This bottle has a volume of " + volume + "  and contains " + content;
	}
}
