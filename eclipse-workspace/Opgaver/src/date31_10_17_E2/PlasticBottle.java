package date31_10_17_E2;

public class PlasticBottle extends Bottle{
	private int volume;
	private String contents, material;
	
	public PlasticBottle(int newVolume, String newContents, String newMaterial) {
		volume = newVolume;
		contents =newContents;
		material = newMaterial;
	}
	
	public String toString() {
		return "This " + material + " bottle has a volume of " + volume + "  and contains " + contents;
	}
}
