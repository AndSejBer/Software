package del1;

public class Prime implements Runnable {

	public static Useless parent;
	
	public Prime(Useless p) {
		parent = p;
	}
	
	public void run() {
		int count = 0;
		int num = 2;
		long starttime = System.currentTimeMillis();
		while (parent.boxes.done) {
			long current = System.currentTimeMillis();
			parent.time.setText((((current-starttime)/100)/10.0) + " ");
			parent.top.setText(count + " ");
			if (test(num)) {
				parent.txtarea.append(Integer.toString(num) + " ");
				count++;
				try {
					Thread.sleep(100);
				}catch (Exception e) {
					
				}
			}
			num++;
		}
		
	}
	
	private boolean test(int num) {
		for (int i = 2; i < num; i++) {
			if (num == i) {
				return true;
			}else if (num%i == 0) {
				return false;
			} 
		}
		return true;
	}
}
