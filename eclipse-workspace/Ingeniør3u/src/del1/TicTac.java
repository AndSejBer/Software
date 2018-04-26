package del1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTac extends JFrame implements ActionListener {

	public static JButton b[] = new JButton [9];
	public static Socket link;
	public static JTextArea txtarea;
	public static PrintWriter out;

	public TicTac() {
		try {
			link = new Socket("itkomsrv.fotonik.dtu.dk", 1102);
		} catch (Exception e) {

		}
		getContentPane().setLayout(new BorderLayout());
		Dimension btnsize = new Dimension(100, 100);
		
		for (int i = 0; i <9; i++) {
			b[i] = new JButton(".");
			b[i].addActionListener(this);
			b[i].setMaximumSize(btnsize);
			b[i].setAlignmentX(Component.CENTER_ALIGNMENT);
		}

		JPanel p[] = new JPanel [3];

		int j = 0;
		for (int i = 0; i<3; i++) {
			p[i] =new JPanel();
			p[i].setLayout(new BoxLayout(p[i],BoxLayout.LINE_AXIS));
			for (int k = 0; k<3; k++) {
				p[i].add(b[j]);
				j++;
			}
		}

		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.PAGE_AXIS));
		p4.add(p[0]);
		p4.add(p[1]);
		p4.add(p[2]);

		txtarea = new JTextArea();

		getContentPane().add(txtarea, BorderLayout.NORTH);
		getContentPane().add(p4, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i<9; i++) {
			if (e.getSource() == b[i]) {
				out.print((i+1) + "\r\n");
				out.flush();
			}
		}
		txtarea.setText("Wait");
	}

	public static void main(String[] args) {
		TicTac board = new TicTac();
		board.setTitle("TicTacToe");
		board.setSize(300, 200); 
		board.setResizable(false);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);

		try {
			out = new PrintWriter(link.getOutputStream(), true);
			Scanner in = new Scanner(link.getInputStream());
			String response;
			do {
				response = in.nextLine();
				if (response.equals("YOUR TURN")) {
					txtarea.setText("Your turn");
				} else if (response.startsWith("BOARD")) {
					for (int j = 9; j < 18; j++) {
						String ch = Character.toString(response.charAt(j));
						b[j-9].setText(ch);
					}
				} else if (response.endsWith("WINS")) {
					if (response.startsWith("PLAYER")) {
						txtarea.setText("YOU WIN YE LUCKY CUNT");
					} else if (response.startsWith("NOBODY")) {
						txtarea.setText("NO ONE WON?!?!?!? WHAT ARE YE?\r\nA FREAKING NOOB??????");
					} else {
						txtarea.setText("YOU LOOSE.... WHAT ARE YE?\r\nA FREAKING NOOB??????");
					}
				}
			} while (!(response.endsWith("WINS")));
			in.close();
			out.close();
			link.close();
		} catch (IOException e) {

		}
	}
}