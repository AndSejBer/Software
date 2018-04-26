package exercise2;

//package aflopg_17_2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TPViewer {

	static JFrame viewer;
	static JPanel panel;
	static int[][] array;
	static final int CELLSIZE = 20;


	public static void main(String[] args) {
		// CHANGE VALUES IN THE NEXT THREE LINES 
		int width = 44;
		int height = 20;
		int[] initial = { 17 };
		//-------------------------------------

		TrianglePattern tp = new TrianglePattern(width, height, initial);
		init();
		run(tp);
	}

	private static void init() {
		viewer = new JFrame();
		panel = new JPanel() {
			public void paintComponent(Graphics gr) {
				super.paintComponent(gr);
				this.repaint();
				int width = panel.getWidth();
				int height = panel.getHeight();
				int noofrows = array.length;
				int noofcols = array[0].length;
				int cellwidth = width / noofcols;
				int cellheight = height / noofrows;
				cellwidth = CELLSIZE;
				cellheight = CELLSIZE;
				gr.setColor(Color.BLACK);
				for (int r = 0; r < array.length; r++) {
					for (int c = 0; c < array[0].length; c++) {
						if (array[r][c] == 0) {
							gr.drawRect(c * cellwidth, r * cellheight,
									cellwidth, cellheight);
						} else {
							gr.fillRect(c * cellwidth, r * cellheight,
									cellwidth, cellheight);
						}

					}
				}
			}

		};

		panel.setBackground(Color.white);

		viewer.getContentPane().add(panel);

		viewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewer.setLocation(50, 50);
		viewer.setSize(1100, 600);

	}

	private static void run(TrianglePattern tp) {
		viewer.setVisible(true);
		array = new int[tp.getH()][tp.getN()];
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[0].length; c++) {
				array[r][c] = tp.getValueAt(r, c);

			}
		}

		panel.repaint();

	}

}
