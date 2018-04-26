package dnd_penandpaperkl;

import java.util.*;


//Version 0.1.5
//Last edit Rasmus d.1/15/18
//Lines edited: /update fungere nu for GM

public class PlayerConnection implements Runnable {
	
	//defining parent
	public static PlayerClient parent;
	public PlayerConnection (PlayerClient p) {
		parent = p;
	}		
	


	public void run() {
		try {
			//creates scanner for reading
			Scanner in = new Scanner(PlayerClient.link.getInputStream());
			String response = null;
			boolean connected = false;
			boolean waitingForAccept = true;
			while(waitingForAccept) {//wait for accept before changing to connection running
				System.out.println("Waiting for accept");
				response=in.nextLine();
				if (response.startsWith("accept")) {
					waitingForAccept = false;
					connected = true;
					parent.card.next(parent);//changes to logged in screen
				}
			}
			// loop to read chat from DM
			while(connected) {
				System.out.println("Connected to server");
				String message = in.nextLine();
				//reading commands from DM
				if (message.startsWith("/addDMChat")) {//add DM chat
					while (!(message.startsWith("/endOfMsg"))) {
						String message1 = message.replaceAll("/addDMChat ", "");
						parent.DMchat.append(message1+"\r\n");
						parent.DMchat.setCaretPosition(parent.DMchat.getDocument().getLength());
						message = in.nextLine();
					}
				}else if(message.startsWith("/setDMChat")) {//set DM chat
					System.out.println(message);
					String message2 = message.replaceAll("/setDMChat ", "");
					parent.DMchat.setText(message2+"\r\n");
					message = in.nextLine();
				
					System.out.println(message);
					while (!(message.startsWith("/endOfMsg"))) {
						parent.DMchat.append(message+"\r\n");
						message = in.nextLine();
						System.out.println(message);
					}
					message = "";
				}else if(message.startsWith("/update")){//sends sheet to DM
					//exact same code as sendCharSheet button except addition of parent
					String charSheetS = "/charSheet ";

					charSheetS = charSheetS + " /next " 
							+ parent.card1.sheetPlayerF.getText()+" /next " 
							+ parent.card1.sheetCharacterNameF.getText()+" /next "
							+ parent.card1.sheetClassAndLevelF.getText()+" /next "
							+ parent.card1.sheetRaceF.getText()+" /next "
							+ parent.card1.sheetAlignmentF.getText()+" /next "
							+ parent.card1.sheetDeityF.getText()+" /next "
							+ parent.card1.sheetSizeF.getText()+" /next "
							+ parent.card1.sheetAgeF.getText()+" /next "
							+ parent.card1.sheetGenderF.getText()+" /next "
							+ parent.card1.sheetHeightF.getText()+" /next "
							+ parent.card1.sheetWeightF.getText()+" /next "
							+ parent.card1.sheetEyesF.getText()+" /next "
							+ parent.card1.sheetHairF.getText()+" /next "
							+ parent.card1.sheetSkinF.getText();


					charSheetS = charSheetS + " /next " 
							+ parent.card2.ACtotalF.getText() +" /next "
							+ parent.card2.ACarmorF.getText() +" /next "
							+ parent.card2.ACshieldF.getText() +" /next "
							+ parent.card2.ACdexF.getText() +" /next "
							+ parent.card2.ACsizeF.getText() +" /next "
							+ parent.card2.ACnaturalF.getText() +" /next "
							+ parent.card2.ACdeflectionF.getText() +" /next "
							+ parent.card2.ACmiscF.getText() +" /next "
							+ parent.card2.sheetBaseAttackBonusF.getText() +" /next "
							+ parent.card2.sheetSpellResistanceF.getText() +" /next "
							+ parent.card2.sheetSpeedF.getText() +" /next "
							+ parent.card2.sheetInitiativeF.getText() +" /next "
							+ parent.card2.sheetTouchF.getText() +" /next "
							+ parent.card2.sheetFlatFootF.getText() +" /next "
							+ parent.card2.sheetGrappleTotF.getText() +" /next "
							+ parent.card2.sheetGrappleBaseAttBonusF.getText() +" /next "
							+ parent.card2.sheetGrappleStrengthModF.getText() +" /next "
							+ parent.card2.sheetGrappleSizeModF.getText() +" /next "
							+ parent.card2.sheetGrappleMiscModF.getText() +" /next "
							+ parent.card2.HitPointsTotF.getText() +" /next "
							+ parent.card2.WoundsF.getText() +" /next "
							+ parent.card2.nonlethalF.getText();


					for (int i = 0 ; i < parent.card2.at.length; i++) {
						charSheetS = charSheetS+ " /next " +parent.card2.at[i].getText();
					}

					for (int i = 0 ; i < parent.card2.stF.length; i++) {
						charSheetS = charSheetS + " /next " + parent.card2.stF[i].getText();
					}

					charSheetS = charSheetS + " /next " + parent.n;

					for (int i = 0 ; i < 3; i++) {
						for (int j = 0 ; j < 8; j++) {
							charSheetS = charSheetS + " /next " + parent.card3.attF[i][j].getText();
						}
					}

					charSheetS = charSheetS + " /next " + parent.m;

					for (int i = 0 ; i < 9; i++) {
						for (int j = 0 ; j < 6; j++) {
							charSheetS = charSheetS + " /next " + parent.card4.skillF[i][j].getText();
						}
					}
					charSheetS = charSheetS + "\r\n";
					System.out.println(charSheetS);

					PlayerClient.pwout.print(charSheetS);
					PlayerClient.pwout.flush();					
					
				}	
				else
					parent.chat.append(message+"\r\n");
					parent.chat.setCaretPosition(parent.chat.getDocument().getLength());
				}
			in.close();
		}catch(Exception e){
			System.out.println("Connection error");//if something goes wrong, writes connection error
		}
	}
}