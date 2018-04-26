package dnd_penandpaper;

import java.awt.*;
import javax.swing.*;

//Version 1.0.0
//Last edit 
//Lines edited:

public class sheetCard1 extends JPanel{
	protected JTextField sheetPlayerF,sheetCharacterNameF, sheetClassAndLevelF, sheetRaceF,sheetAlignmentF, sheetDeityF, sheetSizeF, sheetAgeF, sheetGenderF, sheetHeightF, sheetWeightF, sheetEyesF, sheetHairF, sheetSkinF;
	
	public sheetCard1(ADM parent) {
		JLabel sheetCharacterNameL = new JLabel("Character Name");
		sheetCharacterNameF = new JTextField(40);
		sheetCharacterNameF.setEditable(true);
		JPanel sheetCharacterNameP =new JPanel();
		sheetCharacterNameP.setLayout(new BoxLayout(sheetCharacterNameP,BoxLayout.Y_AXIS));
		sheetCharacterNameP.add(sheetCharacterNameL);
		sheetCharacterNameP.add(sheetCharacterNameF);

		JLabel sheetPlayerL = new JLabel("Player");
		sheetPlayerF = new JTextField(40);
		sheetPlayerF.setEditable(false);
		JPanel sheetPlayerP =new JPanel();
		sheetPlayerP.setLayout(new BoxLayout(sheetPlayerP,BoxLayout.Y_AXIS));
		sheetPlayerP.add(sheetPlayerL);	
		sheetPlayerP.add(sheetPlayerF);

		JLabel sheetClassAndLevelL = new JLabel("Class and Level");
		sheetClassAndLevelF = new JTextField(40);
		sheetClassAndLevelF.setEditable(true);
		JPanel sheetClassAndLevelP =new JPanel();
		sheetClassAndLevelP.setLayout(new BoxLayout(sheetClassAndLevelP,BoxLayout.Y_AXIS));
		sheetClassAndLevelP.add(sheetClassAndLevelL);	
		sheetClassAndLevelP.add(sheetClassAndLevelF);

		JLabel sheetRaceL = new JLabel("Race");
		sheetRaceF = new JTextField(40);
		sheetRaceF.setEditable(true);
		JPanel sheetRaceP =new JPanel();
		sheetRaceP.setLayout(new BoxLayout(sheetRaceP,BoxLayout.Y_AXIS));
		sheetRaceP.add(sheetRaceL);	
		sheetRaceP.add(sheetRaceF);

		JLabel sheetAlignmentL = new JLabel("Alignment");
		sheetAlignmentF = new JTextField(40);
		sheetAlignmentF.setEditable(true);
		JPanel sheetAlignmentP =new JPanel();
		sheetAlignmentP.setLayout(new BoxLayout(sheetAlignmentP,BoxLayout.Y_AXIS));
		sheetAlignmentP.add(sheetAlignmentL);	
		sheetAlignmentP.add(sheetAlignmentF);

		JLabel sheetDeityL = new JLabel("Deity");
		sheetDeityF = new JTextField(40);
		sheetDeityF.setEditable(true);
		JPanel sheetDeityP =new JPanel();
		sheetDeityP.setLayout(new BoxLayout(sheetDeityP,BoxLayout.Y_AXIS));
		sheetDeityP.add(sheetDeityL);	
		sheetDeityP.add(sheetDeityF);

		JLabel sheetSizeL = new JLabel("Size");
		sheetSizeF = new JTextField(40);
		sheetSizeF.setEditable(true);
		JPanel sheetSizeP =new JPanel();
		sheetSizeP.setLayout(new BoxLayout(sheetSizeP,BoxLayout.Y_AXIS));
		sheetSizeP.add(sheetSizeL);	
		sheetSizeP.add(sheetSizeF);

		JLabel sheetAgeL = new JLabel("Age");
		sheetAgeF = new JTextField(40);
		sheetAgeF.setEditable(true);
		JPanel sheetAgeP =new JPanel();
		sheetAgeP.setLayout(new BoxLayout(sheetAgeP,BoxLayout.Y_AXIS));
		sheetAgeP.add(sheetAgeL);	
		sheetAgeP.add(sheetAgeF);

		JLabel sheetGenderL = new JLabel("Gender");
		sheetGenderF = new JTextField(40);
		sheetGenderF.setEditable(true);
		JPanel sheetGenderP =new JPanel();
		sheetGenderP.setLayout(new BoxLayout(sheetGenderP,BoxLayout.Y_AXIS));
		sheetGenderP.add(sheetGenderL);	
		sheetGenderP.add(sheetGenderF);

		JLabel sheetHeightL = new JLabel("Height");
		sheetHeightF = new JTextField(40);
		sheetHeightF.setEditable(true);
		JPanel sheetHeightP =new JPanel();
		sheetHeightP.setLayout(new BoxLayout(sheetHeightP,BoxLayout.Y_AXIS));
		sheetHeightP.add(sheetHeightL);	
		sheetHeightP.add(sheetHeightF);

		JLabel sheetWeightL = new JLabel("Weight");
		sheetWeightF = new JTextField(40);
		sheetWeightF.setEditable(true);
		JPanel sheetWeightP =new JPanel();
		sheetWeightP.setLayout(new BoxLayout(sheetWeightP,BoxLayout.Y_AXIS));
		sheetWeightP.add(sheetWeightL);	
		sheetWeightP.add(sheetWeightF);

		JLabel sheetEyesL = new JLabel("Eyes");
		sheetEyesF = new JTextField(40);
		sheetEyesF.setEditable(true);
		JPanel sheetEyesP =new JPanel();
		sheetEyesP.setLayout(new BoxLayout(sheetEyesP,BoxLayout.Y_AXIS));
		sheetEyesP.add(sheetEyesL);	
		sheetEyesP.add(sheetEyesF);

		JLabel sheetHairL = new JLabel("Hair");
		sheetHairF = new JTextField(40);
		sheetHairF.setEditable(true);
		JPanel sheetHairP =new JPanel();
		sheetHairP.setLayout(new BoxLayout(sheetHairP,BoxLayout.Y_AXIS));
		sheetHairP.add(sheetHairL);	
		sheetHairP.add(sheetHairF);

		JLabel sheetSkinL = new JLabel("Skin");
		sheetSkinF = new JTextField(40);
		sheetSkinF.setEditable(true);
		JPanel sheetSkinP =new JPanel();
		sheetSkinP.setLayout(new BoxLayout(sheetSkinP,BoxLayout.Y_AXIS));
		sheetSkinP.add(sheetSkinL);	
		sheetSkinP.add(sheetSkinF);

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(sheetCharacterNameP);
		panel1.add(sheetClassAndLevelP);

		JPanel panel2_2 = new JPanel();
		panel2_2.setLayout(new GridLayout(1,3));
		panel2_2.add(sheetRaceP);
		panel2_2.add(sheetAlignmentP);
		panel2_2.add(sheetDeityP);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,1));
		panel2.add(sheetPlayerP);
		panel2.add(panel2_2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2,2));
		panel3.add(sheetSizeP);
		panel3.add(sheetAgeP);
		panel3.add(sheetGenderP);
		panel3.add(sheetHeightP);

		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridLayout(2,2));
		panel4.add(sheetWeightP);
		panel4.add(sheetEyesP);
		panel4.add(sheetHairP);
		panel4.add(sheetSkinP);

		JPanel samlet =new JPanel();
		samlet.setLayout(new GridLayout(2,2));
		samlet.add(panel1);
		samlet.add(panel2);
		samlet.add(panel3);
		samlet.add(panel4);

		this.setLayout(new GridLayout(1,1));
		this.add(samlet);
	}
}