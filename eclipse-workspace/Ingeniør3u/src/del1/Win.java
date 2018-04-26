package del1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Win extends JFrame {
	JTextArea txtarea;
	
	public Win() {
		// Set a BorderLayout on the main window
		getContentPane().setLayout(new BorderLayout());
		txtarea = new JTextArea();
		 getContentPane().add(txtarea, BorderLayout.CENTER);
	}
	
	public void wl(int i) {
		if (i == 1) {
			txtarea.setText("YOU WIN YE LUCKY CUNT");
		} else {
			txtarea.setText("YOU LOOSE.... WHAT ARE YE? A FREAKING NOOB??????");
		}
	}
}
