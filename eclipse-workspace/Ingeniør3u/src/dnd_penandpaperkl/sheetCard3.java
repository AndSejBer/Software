package dnd_penandpaperkl;

import java.awt.*;

import javax.swing.*;

public class sheetCard3 extends JPanel{
	protected JPanel[] attP = new JPanel[3];
	protected JTextField[][] attF;
	protected JButton addAtt;

	public sheetCard3(PlayerClient parent) {
		addAtt = new JButton("Add Attack");
		addAtt.setAlignmentX(Component.CENTER_ALIGNMENT);

		attF = new JTextField[3][8];
		for (int i = 0 ; i < 3 ; i++){
			for (int j = 0 ; j < 8 ; j++) {
				attF[i][j]=new JTextField(40);
			}
		}

		JLabel[][] attL = new JLabel[3][8];
		for (int i= 0; i<3;i++){
			attL[i][0]=new JLabel("Attack");
			attL[i][1]=new JLabel("Attack Bonus");
			attL[i][2]=new JLabel("Damage");
			attL[i][3]=new JLabel("Critical");
			attL[i][4]=new JLabel("Range");
			attL[i][5]=new JLabel("Type");
			attL[i][6]=new JLabel("Notes");
			attL[i][7]=new JLabel("Ammunition");
		}

		JPanel[][] attUP = new JPanel[3][8];
		for (int i= 0; i<3;i++){
			for (int j = 0 ; j<8;j++) {
				attUP[i][j] = new JPanel();
				attUP[i][j].setLayout(new BoxLayout(attUP[i][j],BoxLayout.Y_AXIS));
				attUP[i][j].add(attL[i][j]);
				attUP[i][j].add(attF[i][j]);
			}
		}

		for (int i = 0; i<3 ;i++) {
			attP[i]= new JPanel();
			attP[i].setLayout(new GridLayout(4,2));
			for (int j = 0; j<8;j++) {
				attP[i].add(attUP[i][j]);
			}
		}

		this.setLayout(new GridLayout(0,1));
		this.add(addAtt);
	}
}
