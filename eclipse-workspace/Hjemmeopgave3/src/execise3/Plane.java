
public class Plane {

	private static final int DEFAULT_ID = 0;

	protected String manufacturer, type;
	int id;
	/**
	 * @param manufacturer
	 * @param type
	 * @param id
	 */
	public Plane(String manufacturer, String type) {
		this.manufacturer = manufacturer;
		this.type = type;
		this.id = DEFAULT_ID;
	}

	public void setId(int id){
		this.id = id;
	}
	
	//Self explanatory
	public String toString(){
		return "Plane" + id + " " + manufacturer + " " + type;
	}

	public boolean equals(Object o){
		//In case  The object o is not a type plane (for example a String), we put it in a
		//try catch block to ensure a stable program
		try {
			Plane pnew = (Plane) o;
			if (pnew.id == this.id) {
				return true;
			}
		}catch (Exception e) {

		}
		return false;
	}

	public int getId() {
		return id;
	}	

}
