package dnd_penandpaperkl;

import java.awt.*;
import javax.swing.*;

public class sheetCard2 extends JPanel{
	protected JTextField[] at,stF;
	protected JTextField ACtotalF, ACarmorF, ACshieldF, ACdexF, ACsizeF, ACnaturalF, ACdeflectionF, ACmiscF, sheetBaseAttackBonusF, sheetSpellResistanceF, sheetSpeedF, sheetInitiativeF, sheetTouchF, sheetFlatFootF, sheetGrappleTotF, sheetGrappleBaseAttBonusF, sheetGrappleStrengthModF, sheetGrappleSizeModF, sheetGrappleMiscModF, HitPointsTotF, WoundsF, nonlethalF;

	
	public sheetCard2(PlayerClient parent) {
		JLabel AbilityName = new JLabel("Ability Name");
		JLabel AbilityScore = new JLabel("Ability Score");
		JLabel AbilityModifier = new JLabel("Ability Mod");
		JLabel TemporaryScore = new JLabel("Temp Score");
		JLabel TemporaryModifier = new JLabel("Temp Mod");
		JLabel STR = new JLabel("STR");
		JLabel DEX = new JLabel("DEX");
		JLabel CON = new JLabel("CON");
		JLabel INT = new JLabel("INT");
		JLabel WIS = new JLabel("WIS");
		JLabel CHA = new JLabel("CHA");
		at = new JTextField[24];
		for (int i= 0; i<at.length;i++){
			at[i]=new JTextField(40);
		}

		JPanel attributesP = new JPanel();
		attributesP.setLayout(new GridLayout(7,5));
		attributesP.add(AbilityName);
		attributesP.add(AbilityScore);
		attributesP.add(AbilityModifier);
		attributesP.add(TemporaryScore);
		attributesP.add(TemporaryModifier);
		attributesP.add(STR);
		for (int i= 0; i<4;i++){
			attributesP.add(at[i]);
		}
		attributesP.add(DEX);
		for (int i= 4; i<8;i++){
			attributesP.add(at[i]);
		}
		attributesP.add(CON);
		for (int i= 8; i<12;i++){
			attributesP.add(at[i]);
		}
		attributesP.add(INT);
		for (int i= 12; i<16;i++){
			attributesP.add(at[i]);
		}
		attributesP.add(WIS);
		for (int i= 16; i<20;i++){
			attributesP.add(at[i]);
		}
		attributesP.add(CHA);
		for (int i= 20; i<at.length;i++){
			attributesP.add(at[i]);
		}

		JLabel ACL = new JLabel("AC");
		JLabel ACtotalL = new JLabel("Total");
		JLabel AC10L = new JLabel("10");
		JLabel ACarmorL = new JLabel("Armor Bonus");
		JLabel ACshieldL = new JLabel("Shield Bonus");
		JLabel ACdexL = new JLabel("DEX Modifier");
		JLabel ACsizeL = new JLabel("Size Modifier");
		JLabel ACnaturalL = new JLabel("Natural Armor");
		JLabel ACdeflectionL = new JLabel("Deflection Modifier");
		JLabel ACmiscL = new JLabel("Misc Modifier");
		ACtotalF = new JTextField(40);
		ACarmorF = new JTextField(40);
		ACshieldF = new JTextField(40);
		ACdexF = new JTextField(40);
		ACsizeF = new JTextField(40);
		ACnaturalF = new JTextField(40);
		ACdeflectionF = new JTextField(40);
		ACmiscF = new JTextField(40);

		JPanel ACtotalP = new JPanel();
		ACtotalP.setLayout(new BoxLayout(ACtotalP,BoxLayout.Y_AXIS));
		ACtotalP.add(ACtotalL);
		ACtotalP.add(ACtotalF);

		JPanel ACarmorP = new JPanel();
		ACarmorP.setLayout(new BoxLayout(ACarmorP,BoxLayout.Y_AXIS));
		ACarmorP.add(ACarmorL);		
		ACarmorP.add(ACarmorF);

		JPanel ACshieldP = new JPanel();
		ACshieldP.setLayout(new BoxLayout(ACshieldP,BoxLayout.Y_AXIS));
		ACshieldP.add(ACshieldL);
		ACshieldP.add(ACshieldF);

		JPanel ACdexP = new JPanel();
		ACdexP.setLayout(new BoxLayout(ACdexP,BoxLayout.Y_AXIS));
		ACdexP.add(ACdexL);
		ACdexP.add(ACdexF);

		JPanel ACsizeP = new JPanel();
		ACsizeP.setLayout(new BoxLayout(ACsizeP,BoxLayout.Y_AXIS));
		ACsizeP.add(ACsizeL);
		ACsizeP.add(ACsizeF);

		JPanel ACnaturalP = new JPanel();
		ACnaturalP.setLayout(new BoxLayout(ACnaturalP,BoxLayout.Y_AXIS));
		ACnaturalP.add(ACnaturalL);
		ACnaturalP.add(ACnaturalF);

		JPanel ACdeflectionP = new JPanel();
		ACdeflectionP.setLayout(new BoxLayout(ACdeflectionP,BoxLayout.Y_AXIS));
		ACdeflectionP.add(ACdeflectionL);
		ACdeflectionP.add(ACdeflectionF);

		JPanel ACmiscP = new JPanel();
		ACmiscP.setLayout(new BoxLayout(ACmiscP,BoxLayout.Y_AXIS));
		ACmiscP.add(ACmiscL);
		ACmiscP.add(ACmiscF);

		JLabel[] equalsL = new JLabel[5];
		JLabel[] plusL =new JLabel[20];

		for (int i= 0; i<plusL.length;i++){
			plusL[i]=new JLabel("+");
		}

		for (int i= 0; i<equalsL.length;i++){
			equalsL[i]=new JLabel("=");
		}

		JPanel ACP = new JPanel();
		ACP.setLayout(new BoxLayout(ACP,BoxLayout.X_AXIS));
		ACP.add(ACL);
		ACP.add(ACtotalP);
		ACP.add(equalsL[0]);
		ACP.add(AC10L);
		ACP.add(plusL[0]);
		ACP.add(ACarmorP);
		ACP.add(plusL[1]);
		ACP.add(ACshieldP);
		ACP.add(plusL[2]);
		ACP.add(ACdexP);
		ACP.add(plusL[3]);
		ACP.add(ACsizeP);
		ACP.add(plusL[4]);
		ACP.add(ACnaturalP);
		ACP.add(plusL[5]);
		ACP.add(ACdeflectionP);
		ACP.add(plusL[6]);
		ACP.add(ACmiscP);

		//2.kvadrat
		JLabel sheetBaseAttackBonusL = new JLabel("Base Attack Bonus");
		sheetBaseAttackBonusF = new JTextField(40);
		sheetBaseAttackBonusF.setEditable(true);
		JPanel sheetBaseAttackBonusP = new JPanel();
		sheetBaseAttackBonusP.setLayout(new BoxLayout(sheetBaseAttackBonusP,BoxLayout.Y_AXIS));
		sheetBaseAttackBonusP.add(sheetBaseAttackBonusL);	
		sheetBaseAttackBonusP.add(sheetBaseAttackBonusF);


		JLabel sheetSpellResistanceL = new JLabel("Spell Resistance");
		sheetSpellResistanceF = new JTextField(40);
		sheetSpellResistanceF.setEditable(true);
		JPanel sheetSpellResistanceP = new JPanel();
		sheetSpellResistanceP.setLayout(new BoxLayout(sheetSpellResistanceP,BoxLayout.Y_AXIS));
		sheetSpellResistanceP.add(sheetSpellResistanceL);	
		sheetSpellResistanceP.add(sheetSpellResistanceF);	

		JLabel sheetSpeedL = new JLabel("Speed");
		sheetSpeedF = new JTextField(40);
		sheetSpeedF.setEditable(true);
		JPanel sheetSpeedP = new JPanel();
		sheetSpeedP.setLayout(new BoxLayout(sheetSpeedP,BoxLayout.Y_AXIS));
		sheetSpeedP.add(sheetSpeedL);	
		sheetSpeedP.add(sheetSpeedF);	

		JLabel sheetInitiativeL = new JLabel("Initiative (modifier)");
		sheetInitiativeF = new JTextField(40);
		sheetInitiativeF.setEditable(true);
		JPanel sheetInitiativeP = new JPanel();
		sheetInitiativeP.setLayout(new BoxLayout(sheetInitiativeP,BoxLayout.Y_AXIS));
		sheetInitiativeP.add(sheetInitiativeL);	
		sheetInitiativeP.add(sheetInitiativeF);	

		JLabel sheetTouchL = new JLabel("Touch (Armor Class)");
		sheetTouchF = new JTextField(40);
		sheetTouchF.setEditable(true);
		JPanel sheetTouchP = new JPanel();
		sheetTouchP.setLayout(new BoxLayout(sheetTouchP,BoxLayout.Y_AXIS));
		sheetTouchP.add(sheetTouchL);	
		sheetTouchP.add(sheetTouchF);	

		JLabel sheetFlatFootL = new JLabel("Flat Footed (Armor Class)");
		sheetFlatFootF = new JTextField(40);
		sheetFlatFootF.setEditable(true);
		JPanel sheetFlatFootP = new JPanel();
		sheetFlatFootP.setLayout(new BoxLayout(sheetFlatFootP,BoxLayout.Y_AXIS));
		sheetFlatFootP.add(sheetFlatFootL);	
		sheetFlatFootP.add(sheetFlatFootF);	

		JLabel sheetGrappleL = new JLabel("Grapple");
		JLabel sheetGrappleTotL = new JLabel("Total");
		JLabel sheetGrappleBaseAttBonusL = new JLabel("Base Attack Bonus");
		JLabel sheetGrappleStrengthModL = new JLabel("Strength");
		JLabel sheetGrappleSizeModL = new JLabel("Size");
		JLabel sheetGrappleMiscModL = new JLabel("Misc");




		sheetGrappleTotF = new JTextField(40);
		sheetGrappleTotF.setEditable(true);
		sheetGrappleBaseAttBonusF = new JTextField(40);
		sheetGrappleBaseAttBonusF.setEditable(true);
		sheetGrappleStrengthModF = new JTextField(40);
		sheetGrappleStrengthModF.setEditable(true);
		sheetGrappleSizeModF = new JTextField(40);
		sheetGrappleSizeModF.setEditable(true);
		sheetGrappleMiscModF = new JTextField(40);
		sheetGrappleMiscModF.setEditable(true);



		JPanel sheetAllGrappleP = new JPanel();
		sheetAllGrappleP.setLayout(new BoxLayout(sheetAllGrappleP,BoxLayout.X_AXIS));

		sheetAllGrappleP.add(sheetGrappleL);	

		JPanel GrappleP1 = new JPanel();
		GrappleP1.setLayout(new BoxLayout(GrappleP1,BoxLayout.Y_AXIS));
		GrappleP1.add(sheetGrappleTotL);	
		GrappleP1.add(sheetGrappleTotF);
		sheetAllGrappleP.add(GrappleP1);

		sheetAllGrappleP.add(plusL[7]);

		JPanel GrappleP2 = new JPanel();
		GrappleP2.setLayout(new BoxLayout(GrappleP2,BoxLayout.Y_AXIS));
		GrappleP2.add(sheetGrappleBaseAttBonusL);	
		GrappleP2.add(sheetGrappleBaseAttBonusF);
		sheetAllGrappleP.add(GrappleP2);

		sheetAllGrappleP.add(plusL[8]);

		JPanel GrappleP3 = new JPanel();
		GrappleP3.setLayout(new BoxLayout(GrappleP3,BoxLayout.Y_AXIS));
		GrappleP3.add(sheetGrappleStrengthModL);	
		GrappleP3.add(sheetGrappleStrengthModF);
		sheetAllGrappleP.add(GrappleP3);

		sheetAllGrappleP.add(plusL[9]);

		JPanel GrappleP4 = new JPanel();
		GrappleP4.setLayout(new BoxLayout(GrappleP4,BoxLayout.Y_AXIS));
		GrappleP4.add(sheetGrappleSizeModL);	
		GrappleP4.add(sheetGrappleSizeModF);
		sheetAllGrappleP.add(GrappleP4);

		sheetAllGrappleP.add(plusL[10]);

		JPanel GrappleP5 = new JPanel();
		GrappleP5.setLayout(new BoxLayout(GrappleP5,BoxLayout.Y_AXIS));
		GrappleP5.add(sheetGrappleMiscModL);	
		GrappleP5.add(sheetGrappleMiscModF);
		sheetAllGrappleP.add(GrappleP5);



		JPanel s2p1 = new JPanel();
		s2p1.setLayout(new GridLayout(1,2));
		s2p1.add(sheetBaseAttackBonusP);
		s2p1.add(sheetSpellResistanceP);


		JPanel s2p2 = new JPanel();
		s2p2.setLayout(new GridLayout(1,2));
		s2p2.add(sheetSpeedP);
		s2p2.add(sheetInitiativeP);		

		JPanel s2p3 = new JPanel();
		s2p3.setLayout(new GridLayout(1,2));
		s2p3.add(sheetTouchP);
		s2p3.add(sheetFlatFootP);

		JPanel s2P = new JPanel();
		s2P.setLayout(new GridLayout(4,1));
		s2P.add(s2p1);
		s2P.add(s2p2);
		s2P.add(s2p3);
		s2P.add(sheetAllGrappleP);


		stF = new JTextField[18];
		for (int i= 0; i<stF.length;i++){
			stF[i]=new JTextField(40);
		}

		JLabel[] stL = new JLabel[10];
		stL[0]= new JLabel("Saving Throws");
		stL[1]= new JLabel("Total =");
		stL[2]= new JLabel("Base Save +");
		stL[3]= new JLabel("Ability Modifier +");
		stL[4]= new JLabel("Magic Modifier +");
		stL[5]= new JLabel("Misc Modifier +");
		stL[6]= new JLabel("Temporary Modifier");
		stL[7]= new JLabel("Fortitude");
		stL[8]= new JLabel("Reflex");
		stL[9]= new JLabel("Will");

		JPanel stP = new JPanel();
		stP.setLayout(new GridLayout(4,7));
		stP.add(stL[0]);
		stP.add(stL[1]);
		stP.add(stL[2]);
		stP.add(stL[3]);
		stP.add(stL[4]);
		stP.add(stL[5]);
		stP.add(stL[6]);
		stP.add(stL[7]);
		stP.add(stF[0]);
		stP.add(stF[1]);
		stP.add(stF[2]);
		stP.add(stF[3]);
		stP.add(stF[4]);
		stP.add(stF[5]);
		stP.add(stL[8]);
		stP.add(stF[6]);
		stP.add(stF[7]);
		stP.add(stF[8]);
		stP.add(stF[9]);
		stP.add(stF[10]);
		stP.add(stF[11]);
		stP.add(stL[9]);
		stP.add(stF[12]);
		stP.add(stF[13]);
		stP.add(stF[14]);
		stP.add(stF[15]);
		stP.add(stF[16]);
		stP.add(stF[17]);


		JLabel HitPointsL = new JLabel ("Hit Points");

		JLabel HitPointsTotL = new JLabel ("Total");
		HitPointsTotF = new JTextField(40);
		HitPointsTotF.setEditable(true);

		JPanel HitPointsTotP = new JPanel ();
		HitPointsTotP.setLayout(new BoxLayout(HitPointsTotP,BoxLayout.Y_AXIS));
		HitPointsTotP.add(HitPointsTotL);
		HitPointsTotP.add(HitPointsTotF);

		JLabel WoundsL = new JLabel ("Wounds/Current HP");
		WoundsF = new JTextField (40);
		WoundsF.setEditable(true);

		JPanel WoundsP = new JPanel ();
		WoundsP.setLayout(new BoxLayout(WoundsP,BoxLayout.Y_AXIS));
		WoundsP.add(WoundsL);
		WoundsP.add(WoundsF);

		JLabel nonlethalL = new JLabel ("Nonlethal Damage");
		nonlethalF = new JTextField (40);
		nonlethalF.setEditable(true);

		JPanel nonlethalP = new JPanel ();
		nonlethalP.setLayout(new BoxLayout(nonlethalP,BoxLayout.Y_AXIS));
		nonlethalP.add(nonlethalL);
		nonlethalP.add(nonlethalF);


		JPanel HPP = new JPanel();
		HPP.setLayout(new GridLayout (1,4));
		HPP.add(HitPointsL);
		HPP.add(HitPointsTotP);
		HPP.add(WoundsP);
		HPP.add(nonlethalP);
		JPanel ACP_HPP_P = new JPanel();
		ACP_HPP_P.setLayout(new GridLayout(2,1));
		ACP_HPP_P.add(ACP);
		ACP_HPP_P.add(HPP);

		JPanel topP = new JPanel();
		topP.setLayout(new GridLayout(1,2));
		topP.add(attributesP);
		topP.add(s2P);

		JPanel bottomP = new JPanel();
		bottomP.setLayout(new GridLayout(2,1));
		bottomP.add(ACP_HPP_P);
		bottomP.add(stP);

		this.setLayout(new GridLayout(2,1));
		this.add(topP);
		this.add(bottomP);
	}
}
