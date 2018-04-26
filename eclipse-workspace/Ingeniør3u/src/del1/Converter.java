package del1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Converter extends JFrame implements ActionListener {

	public JTextField binf, dicf, hexf;
	public JLabel binary, decimal, hexadecimal;
	public JButton conv1, conv2, conv3;

	public Converter() {
		// Set a BorderLayout on the main window
		getContentPane().setLayout(new BorderLayout());

		// Define a standard button size - 100 x 30 pixels
		Dimension btnsize = new Dimension(100, 30);

		binary = new JLabel("Binary");
		decimal = new JLabel("Decimal");
		hexadecimal = new JLabel("Hexadecimal");

		binf = new JTextField(20);
		dicf = new JTextField(20);
		hexf = new JTextField(20);

		conv1 = new JButton("Convert!");
		conv1.addActionListener(this);
		conv1.setMaximumSize(btnsize);
		conv1.setAlignmentX(Component.CENTER_ALIGNMENT);

		conv2 = new JButton("Convert!");
		conv2.addActionListener(this);
		conv2.setMaximumSize(btnsize);
		conv2.setAlignmentX(Component.CENTER_ALIGNMENT);

		conv3 = new JButton("Convert!");
		conv3.addActionListener(this);
		conv3.setMaximumSize(btnsize);
		conv3.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Create a container (of type JPanel) to hold the first four buttons
		JPanel p1 = new JPanel();
		// Set a top-down box-layout on the container
		p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
		// Create a small filler (above the first button)
		p1.add(Box.createRigidArea(new Dimension(5, 5)));
		// Add first button
		p1.add(binary);
		// Create a small filler (between 1st and 2nd button)
		p1.add(Box.createRigidArea(new Dimension(5, 5)));
		// Add second button
		p1.add(binf);
		// Filler
		p1.add(Box.createRigidArea(new Dimension(5, 5)));
		// Add third button
		p1.add(conv1);

		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));	
		p2.add(Box.createRigidArea(new Dimension(5, 5)));
		p2.add(decimal);
		p2.add(Box.createRigidArea(new Dimension(5, 5)));
		p2.add(dicf);
		p2.add(Box.createRigidArea(new Dimension(5, 5)));
		p2.add(conv2);

		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));	
		p3.add(Box.createRigidArea(new Dimension(5, 5)));
		p3.add(hexadecimal);
		p3.add(Box.createRigidArea(new Dimension(5, 5)));
		p3.add(hexf);
		p3.add(Box.createRigidArea(new Dimension(5, 5)));
		p3.add(conv3);

		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.PAGE_AXIS));	
		p4.add(Box.createRigidArea(new Dimension(110, 5)));
		p4.add(p1);
		p4.add(Box.createRigidArea(new Dimension(110, 5)));
		p4.add(p2);
		p4.add(Box.createRigidArea(new Dimension(110, 5)));
		p4.add(p3);

		// Add container (panel with buttons) to (left side of) main window
		getContentPane().add(p4, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Converter convert = new Converter();

		convert.setTitle("Convert ya shit numbers"); // Set title on window
		convert.setSize(500, 150);     // Set size
		convert.setResizable(true);    // Allow the window to be resized
		convert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		convert.setVisible(true);      // Make the window visible
	}

	// actionPerformed method to handle when the user presses any of the buttons
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == conv1) {
			String binnum = binf.getText();
			if (testNum(binnum,1)) {
				int idec = Integer.parseInt(binnum,2);
				String dec = "" + idec;
				dicf.setText(dec);
				String hex = Integer.toHexString(idec);
				hexf.setText(hex);
			} else {
				binf.setText("User is retarded");
			}
		} else if (e.getSource() == conv2) {
			String decnum = dicf.getText();
			if (testNum(decnum,1)) {
				int num = Integer.parseInt(decnum);
				String bin = Integer.toBinaryString(num);
				binf.setText(bin);
				String hex1 = Integer.toHexString(num);
				hexf.setText(hex1);
			} else {
				dicf.setText("User is retarded");
			}
		} else if (e.getSource() == conv3) {
			String decnum = hexf.getText();
			if (testNum(decnum,1)) {
				int num = Integer.parseInt(decnum,16);
				String bin = Integer.toBinaryString(num);
				binf.setText(bin);
				String dec2 = Integer.toString(num);
				dicf.setText(dec2);
			}else {
				hexf.setText("User is retarded");
			}
		}
	}

	private boolean testNum(String num, int type) {
		String s = num + "";
		if (type == 1 || type == 2) {
			long a;
			try {
				a = Long.parseLong(s);
			} catch (java.lang.NumberFormatException e) {
				return false;
			}
			if (type == 1) {
				return isBinary(a);
			}
			return true;
		}else {
			try {
				int a = Integer.parseInt(s,16);
			} catch (Exception e) {
				return false;
			}
			return true;
		}

	}
	
	public static boolean isBinary (long number) {
		//This method converts the number to a string and then removes all 1s and 0s, if there is anything left it is not a binary number
		String s = "" + number;
		s = s.replace("1", "");
		s = s.replace("0", "");
		return s.isEmpty();
	}

}
