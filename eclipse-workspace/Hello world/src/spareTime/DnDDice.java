package spareTime;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class DnDDice extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel DieSidesL, DieAmountL, CritFailMaxL, SuccesMinL, CritSuccesMinL, resultL, resultF;
	private JTextField DieSidesF, DieAmountF, CritFailMaxF, SuccesMinF, CritSuccesMinF;
	private JButton RollDie;

	public DnDDice() {
		DieSidesL = new JLabel("Amount of sides:");
		DieAmountL = new JLabel("Amount of dice:");
		CritFailMaxL = new JLabel("Max to be counted crit fail:");
		CritSuccesMinL = new JLabel("Min to get crit succes:");
		SuccesMinL = new JLabel("Min to get a succes:");
		resultL = new JLabel("Result of roll:");
		resultF = new JLabel("");

		DieSidesF = new JTextField(40);
		DieSidesF.setText("10");
		DieAmountF = new JTextField(40);
		DieAmountF.setText("10");
		CritFailMaxF = new JTextField(40);
		CritFailMaxF.setText("1");
		SuccesMinF = new JTextField(40);
		SuccesMinF.setText("7");
		CritSuccesMinF = new JTextField(40);
		CritSuccesMinF.setText("10");

		RollDie = new JButton("Roll the die!");
		RollDie.addActionListener(this);

		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.Y_AXIS));
		p1.add(CritFailMaxL);
		p1.add(CritFailMaxF);

		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		p2.add(CritSuccesMinL);
		p2.add(CritSuccesMinF);

		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		p3.add(SuccesMinL);
		p3.add(SuccesMinF);

		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4,BoxLayout.Y_AXIS));
		p4.add(DieSidesL);
		p4.add(DieSidesF);

		JPanel p5 = new JPanel();
		p5.setLayout(new BoxLayout(p5,BoxLayout.Y_AXIS));
		p5.add(DieAmountL);
		p5.add(DieAmountF);

		JPanel p7 = new JPanel();
		p7.setLayout(new BoxLayout(p7,BoxLayout.Y_AXIS));
		p7.add(resultL);
		p7.add(resultF);	

		JPanel p6 = new JPanel();
		p6.setLayout(new BoxLayout(p6,BoxLayout.X_AXIS));
		p6.add(p1);
		p6.add(p2);
		p6.add(p3);
		p6.add(p4);
		p6.add(p5);
		p6.add(RollDie);
		p6.add(p7);

		getContentPane().setLayout(new GridLayout(1,2));
		getContentPane().add(p6);
	}

	public static void main(String args[]) {		
		DnDDice DnDGui = new DnDDice();
		DnDGui.setTitle("DieRolling");
		DnDGui.setSize(1080, 800);
		DnDGui.setResizable(true);
		DnDGui.setVisible(true);      
	}

	public void actionPerformed(ActionEvent event) {
		int roll;
		int succeses = 0;
		
		for (int i = 0; i < Integer.parseInt(DieAmountF.getText()); i++) {
			roll = ThreadLocalRandom.current().nextInt(1, Integer.parseInt(DieSidesF.getText())+1);
			System.out.print(roll + " ");
			if (roll >= Integer.parseInt(SuccesMinF.getText())) {
				if (roll >= Integer.parseInt(CritSuccesMinF.getText())) {
					succeses = succeses +2;
				} else {
					succeses++;
				}
			} else if (roll <= Integer.parseInt(CritFailMaxF.getText())) {
				succeses--;
			}
		}
		
		resultF.setText("" + succeses);
		System.out.println();
	}
}
