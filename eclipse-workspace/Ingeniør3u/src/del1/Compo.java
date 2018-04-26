package del1;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Compo extends JPanel implements Runnable{

	public Useless parent;
	public int num = 0;
	public boolean done = true;

	public Compo(Useless p) {
		parent = p;
	}

	public void run() {
		//long timestart = System.currentTimeMillis();
		while (done) {
			drawStuff();
			num++;
			parent.txtA.setText(parent.boxes.getWidth()*(parent.boxes.getHeight()) + " ");
			parent.txtT.setText(num+" ");
			try {
				Thread.sleep(100);
			} catch (Exception e){

			}
			/*if (num == 10000) {
				long timeend = System.currentTimeMillis();
				long timetaken = timeend - timestart;
				System.out.println(timetaken + " milliseconds");
				done= false;
			}*/
		}
	}

	public void paintComponent(Graphics g) {
		num = 0; 
	}

	public void drawStuff() {
		if (isShowing()) {
			Graphics g = getGraphics();
			int x = ThreadLocalRandom.current().nextInt(0, parent.boxes.getWidth());
			int y = ThreadLocalRandom.current().nextInt(0, parent.boxes.getHeight());
			int sx = ThreadLocalRandom.current().nextInt(-x, parent.boxes.getWidth()-x);
			int sy = ThreadLocalRandom.current().nextInt(-y, parent.boxes.getHeight()-y);
			int r = ThreadLocalRandom.current().nextInt(0, 256);
			int gr = ThreadLocalRandom.current().nextInt(0, 256);
			int b = ThreadLocalRandom.current().nextInt(0, 256);

			g.setColor(new Color(r, gr, b));
			g.fillRect(x, y, sx, sy);
		} else {
			while (!(isShowing())) {
				try {
					Thread.sleep(10);
				} catch (Exception e1) {
					
				}
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(250,200);
	}
}
