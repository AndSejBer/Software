package dnd_penandpaperkl;

import java.awt.*;
import javax.swing.*;

public class sheetCard4 extends JPanel{
	protected JPanel[] skillP = new JPanel[6];
	protected JButton addSkill;
	protected JTextField[][] skillF;


	public sheetCard4(PlayerClient parent) {
		addSkill = new JButton("Add Skill");
		addSkill.setAlignmentX(Component.CENTER_ALIGNMENT);

		skillF = new JTextField[9][6];
		for (int i = 0 ; i < 9 ; i++){
			for (int j = 0 ; j < 6 ; j++) {
				skillF[i][j]=new JTextField(40);
			}
		}

		JTextField[] skillL = new JTextField[6];
		for (int i= 0; i<6;i++){
			skillL[i]=new JTextField(40);
			skillL[i].setEditable(false);
		}	
		skillL[0].setText("Skill Name");
		skillL[1].setText("Key Ability");
		skillL[2].setText("Skill Modifier");
		skillL[3].setText("Ability Modifier");
		skillL[4].setText("Ranks");
		skillL[5].setText("Misc Modifier");


		JPanel skillLP = new JPanel();
		for (int i=0 ; i<6 ;i++){
			skillLP.setLayout(new GridLayout(1,6));
			skillLP.add(skillL[i]);
		}

		skillP = new JPanel[9];
		for (int i = 0; i<9 ;i++) {
			skillP[i]= new JPanel();
			skillP[i].setLayout(new GridLayout(1,6));
			for (int j = 0; j<6;j++) {
				skillP[i].add(skillF[i][j]);
			}
		}
		//layout		
		this.setLayout(new GridLayout(0,1));
		this.add(addSkill);
		this.add(skillLP);

}
}