package del1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Useless extends JFrame implements ActionListener{
	
	protected JTextArea txtarea;
	private static Prime numbers;
	protected static Compo boxes;
	private JButton quit;
	protected JLabel to, area, txtT, txtA, runt, time, top, txtp;
	protected Font font;
	
	public Useless() {
		numbers = new Prime(this);
		boxes = new Compo(this);
		font = new Font("PLAIN", 0, 20);
		quit = new JButton("Quit");
		quit.setFont(font);
		txtT = new JLabel("");
		txtT.setFont(font);
		txtA = new JLabel("");
		txtA.setFont(font);
		to = new JLabel("Total squares: ");
		to.setFont(font);
		area = new JLabel("Area for squares: ");
		area.setFont(font);
		runt = new JLabel("Current run time: ");
		runt.setFont(font);
		time = new JLabel("");
		time.setFont(font);
		txtp = new JLabel("Total primes: ");
		txtp.setFont(font);
		top = new JLabel("");
		top.setFont(font);
		
		getContentPane().setLayout(new GridLayout(2,0));
		
		txtarea = new JTextArea();
        txtarea.setLineWrap(true);
        JScrollPane scrollpane = new JScrollPane(txtarea);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        txtarea.setFont(font);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
        p1.add(quit);
        p1.add(area);
        p1.add(txtA);
        p1.add(to);
        p1.add(txtT);
        p1.add(runt);
        p1.add(time);
        p1.add(txtp);
        p1.add(top);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
        p2.add(scrollpane);
        p2.add(p1);
        
        getContentPane().add(boxes);
        getContentPane().add(p2);
	}
	
	public static void main(String[] args) {
		Useless board = new Useless();
		board.setTitle("Some numbers and colour full boxes");
		board.setSize(1400, 700); 
		board.setResizable(true);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
		new Thread(numbers).start();
		new Thread(boxes).start();
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}	
}