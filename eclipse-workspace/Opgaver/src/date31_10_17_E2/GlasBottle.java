package date31_10_17_E2;

public class GlasBottle extends Bottle{
	private int volume;
	private String color, contents;
	
	public GlasBottle(int newVolume, String newContents, String newColor) {
		volume = newVolume;
		contents = newContents;
		color = newColor;
	}
	
	public String toString() {
		return "This " + color + " glas bottle has a volume of " + volume + "  and contains " + contents;
	}
}
