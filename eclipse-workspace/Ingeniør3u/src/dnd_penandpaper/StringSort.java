package dnd_penandpaper;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

//Version 1.0.0
//Last edit Andreas d.1/13/18
//Lines edited: 160 - 267

public class StringSort implements Runnable {
	protected String name, message1;
	protected ADM parent;
	protected int current;

	//Saves the name of the sender, the GM, message and the number of the player (-1 for GM)
	public StringSort(String nn, ADM dm, String msg, int i) {
		name = nn;
		parent = dm;
		message1 = msg;
		current = i;
	}

	//The main part runs though the message and finds out what it should do
	public void run() {
		//If it's a message written in chat, /chat is automatically added in front and the string is either a command or a message.
		if (message1.startsWith("/chat")) {
			String message = message1.replaceAll("/chat ","");
			//It's checked if it's a command
			if (message.startsWith("/")) {
				//if it's a whisper, we want to only send it to one person
				if (message.startsWith("/whisper")) {
					//If it's to the GM, we check first if GM sent it to himself if so:
					if (message.startsWith("/whisper gm")) {
						if (current == -1) {
							parent.chat.append("You're retarded right?");
						} else {
							//Otherwise it was sent by another player and we append it to GM's chat and send a "sent" message back to the sender
							String msg1 = message.replaceAll("/whisper gm ", "");
							parent.chat.append("Whisper from " + name + ": " + msg1 + " \r\n");
							parent.outStream.get(current).print("You whispered to GM: " + msg1 + "\r\n");
							parent.outStream.get(current).flush(); 
						}
					} else {
						//If it was not sent to GM we go through all the players and see if we can find the player, we then send it to only
						//that player and send a "sent" message to the sender
						for (int i = 0; i < parent.player.size(); i++) {
							if (message.startsWith("/whisper " + parent.player.get(i).getName())){
								String msg = message.replaceAll("/whisper " + parent.player.get(i).getName() + " ", "");
								parent.outStream.get(i).print("Whisper from " + name + ": " + msg + " \r\n");
								i = parent.player.size();
								parent.outStream.get(current).print("You whispered to " + name + ": " + msg + "\r\n");
								parent.outStream.get(current).flush(); 
								//if we don't find the intended player we send an error message to the sender. We append the original message, so
								//the sender can copy paste it back and correct the name
							} else if(i == parent.player.size()) {
								parent.outStream.get(current).print("Player " + name + "not found check spelling and try again. Your message was: \r\n"
										+ "\"" + message + "\" \r\n");
								parent.outStream.get(current).flush(); 
							}
						}
					}
					//if the string starts with /roll, it's a roll command and we extract the first and second number indicating, the amount and the sides
				} else  if (message.startsWith("/roll ")) {
					//We remove any letters from the string, and then split the string in tokens consisting of 2-3 number plus a few "" and " ".
					String msg = message.replaceAll("/roll ", "");
					msg = msg.replaceAll("d", " ");
					String msg1[] = msg.split(" ");
					//We save it in an arraylist, so we can remove all "" and " "
					ArrayList <String> msg2 = new ArrayList <String>();
					for (int i = 0; i < msg1.length; i++) {
						msg2.add(msg1[i]);
					}
					while (msg2.contains("") || msg2.contains(" ")) {
						msg2.remove(" ");
						msg2.remove("");
					}

					//We check if it's a legal roll ai. it only contains numbers now.
					if (legalroll(msg2)) {
						//The roll string that will contain the roll is initialized our sum and the rolled number is also initialized
						String roll = "";
						int sum = 0, num = 0;
						//The number in position 0 in the array indicates the amount of rolls
						for (int i = 0; i < Integer.parseInt(msg2.get(0)); i++) {
							//The number in position 1 indicates the amount of sides on the dice, and we get a random number between 1 and that
							num = ThreadLocalRandom.current().nextInt(1, Integer.parseInt(msg2.get(1)));
							//we add this roll to the roll string and update the sum
							roll = roll + "Roll number: " + (i+1) + " rolled: " + num + " ";
							sum += num;
						}
						//There is an option of adding a modifier to your roll, if this has been used it is then added to the string and sum
						if (msg2.size() == 3) {
							roll = roll + " a modifier of: " + msg2.get(2) + " is added.";
							sum += Integer.parseInt(msg2.get(2));
						}
						//Sum is added to the roll string, and roll is then sent out to all players and the GM
						roll = roll + "Sumtotal: " + sum + "\r\n";
						parent.chat.append("" + name + " rolled a d" + msg2.get(1) + " " + msg2.get(0) + 
								" times results were: " + roll);
						for (int i = 0; i < parent.playernum; i++) {
							parent.outStream.get(i).print("" + name + "rolled a d" + msg2.get(1) + " " + msg2.get(0) + 
									" times results were: " + roll + "\r\n");
							parent.outStream.get(i).flush();
						}
						//If you get here without having rolled you messed up the roll command and we send an error message with the correct syntax
					} else {
						if (!(name.equals("GM"))) {
							parent.outStream.get(current).print("Woops! \"" + message + "\" is not a legal roll command. \r\n"
									+ "The correct syntax is \"/roll [amount]d[size] [modifier]\". \r\n"
									+ "Where all [  ] must be replaced with legal numbers \r\n");
							parent.outStream.get(current).flush();
						} else  {
							parent.chat.append("Woops! \"" + message + "\" is not a legal roll command. \r\n"
									+ "The correct syntax is \"/roll [amount]d[size] [modifier]\". \r\n"
									+ "Where all [  ] must be replaced with legal numbers \r\n");
						}
					}
					//if it's a /me command, we send it out as if your character did something
				} else if (message.startsWith("/me")) {
					String msg = message.replaceAll("/me ", "");
					parent.chat.append(name + " " + msg + "\r\n");
					for (int i = 0; i < parent.playernum; i++) {
						parent.outStream.get(i).print(name + " " + msg + "\r\n");
						parent.outStream.get(i).flush();
					}
					//If it's a /help command we send the current list of commands with their syntax
				} else if (message.startsWith("/help")) {
					if (!(name.equals("GM"))) {
						parent.outStream.get(current).print("Current list of commands are: \r\n"
								+ "/whisper [playername] [message] \r\n" 
								+ "/roll [amount]d[sides] \r\n" 
								+ "/me [message] \r\n" 
								+ "/help \r\n");
						parent.outStream.get(current).flush();
					} else {
						parent.chat.append("Current list of commands are: \r\n"
								+ "/whisper [playername] [message] \r\n" 
								+ "/roll [amount]d[sides] \r\n" 
								+ "/me [message] \r\n"
								+ "/help \r\n");
					}
					//If the message does not start with contain a recognized command we assume that it's just a message 
					//to be sent out as a normal message 
				}else {
					parent.chat.append(name + ": " + message + "\r\n");
					for (int i = 0; i < parent.playernum; i++) {
						parent.outStream.get(i).print("" + name + ": " + message + "\r\n");
						parent.outStream.get(i).flush();
					}
				}
				//If the message does not have a / after /chat has been removed it's a normal message and we send it out as such
			} else {
				parent.chat.append(name + ": " + message + "\r\n");
				for (int i = 0; i < parent.playernum; i++) {
					parent.outStream.get(i).print("" + name + ": " + message + "\r\n");
					parent.outStream.get(i).flush();
				}
			}
			//If the message doesn't start with only it can be a character sheet update
		} else if(message1.startsWith("/charSheet")) {
			
			//First we remove /charSheet as we don't need it
			String message = message1.replaceAll("/charSheet ","");
			
			//We defined /next to indicate where to split between different areas on the sheets, and we split the string into tokens using that
			String msg1 [] = message.split(" /next ");
			
			//We change that to an arraylist as those are easier to manipulate and take things out of
			ArrayList <String> msg = new ArrayList<String>();
			for (int i = 0; i < msg1.length; i++) {
				msg.add(msg1[i]);
			}

			//We then remove any blanks or spaces in front of the first real information - the name of the player
			while(!(msg.get(0).equals(name))) {
				msg.remove(0);
			}
			//We remove the name of the player - it is not needed
			msg.remove(0);
			
			//We update all the things on card1 with the received data
			parent.player.get(current).card1.sheetCharacterNameF.setText(msg.get(0));
			parent.player.get(current).card1.sheetClassAndLevelF.setText(msg.get(1));
			parent.player.get(current).card1.sheetRaceF.setText(msg.get(2));
			parent.player.get(current).card1.sheetAlignmentF.setText(msg.get(3));
			parent.player.get(current).card1.sheetDeityF.setText(msg.get(4));
			parent.player.get(current).card1.sheetSizeF.setText(msg.get(5));
			parent.player.get(current).card1.sheetAgeF.setText(msg.get(6));
			parent.player.get(current).card1.sheetGenderF.setText(msg.get(7));
			parent.player.get(current).card1.sheetHeightF.setText(msg.get(8));
			parent.player.get(current).card1.sheetWeightF.setText(msg.get(9));
			parent.player.get(current).card1.sheetEyesF.setText(msg.get(10));
			parent.player.get(current).card1.sheetHairF.setText(msg.get(11));
			parent.player.get(current).card1.sheetSkinF.setText(msg.get(12));

			//We remove the data that we've used
			for (int j = 0; j < 13; j++) {
				msg.remove(0);
			}

			//We update part of card2, and remove that data
			parent.player.get(current).card2.ACtotalF.setText(msg.get(0));
			parent.player.get(current).card2.ACarmorF.setText(msg.get(1));
			parent.player.get(current).card2.ACshieldF.setText(msg.get(2));
			parent.player.get(current).card2.ACdexF.setText(msg.get(3));
			parent.player.get(current).card2.ACsizeF.setText(msg.get(4));
			parent.player.get(current).card2.ACnaturalF.setText(msg.get(5));
			parent.player.get(current).card2.ACdeflectionF.setText(msg.get(6));
			parent.player.get(current).card2.ACmiscF.setText(msg.get(7));
			parent.player.get(current).card2.sheetBaseAttackBonusF.setText(msg.get(8));
			parent.player.get(current).card2.sheetSpellResistanceF.setText(msg.get(9));
			parent.player.get(current).card2.sheetSpeedF.setText(msg.get(10));
			parent.player.get(current).card2.sheetInitiativeF.setText(msg.get(11));
			parent.player.get(current).card2.sheetTouchF.setText(msg.get(12));
			parent.player.get(current).card2.sheetFlatFootF.setText(msg.get(13));
			parent.player.get(current).card2.sheetGrappleTotF.setText(msg.get(14));
			parent.player.get(current).card2.sheetGrappleBaseAttBonusF.setText(msg.get(15));
			parent.player.get(current).card2.sheetGrappleStrengthModF.setText(msg.get(16));
			parent.player.get(current).card2.sheetGrappleSizeModF.setText(msg.get(17));
			parent.player.get(current).card2.sheetGrappleMiscModF.setText(msg.get(18));
			parent.player.get(current).card2.HitPointsTotF.setText(msg.get(19));
			parent.player.get(current).card2.WoundsF.setText(msg.get(20));
			parent.player.get(current).card2.nonlethalF.setText(msg.get(21));		
			for (int j = 0; j < 22; j++) {
				msg.remove(0);
			}
			
			//Part of card2 is sorted in an array and we update that, and while doing that we remove the used data
			for (int j = 0 ; j < 24; j++) {
				parent.player.get(current).card2.at[j].setText(msg.get(0));
				msg.remove(0);
			}
			
			//same here
			for (int j = 0 ; j < 18; j++) {
				parent.player.get(current).card2.stF[j].setText(msg.get(0));
				msg.remove(0);
			}
			
			//On card3 there can be a varying amount of places to put data that should be visible, so we show the amount that is required as indicated
			//by the message currently at spot 0 here we show the correct amount
			for (int i = 0; i < Integer.parseInt(msg.get(0)); i++) {
				parent.player.get(current).card3.add(parent.player.get(current).card3.attP[i]);
			}
			msg.remove(0);
			
			//Here we add the data to the slots, even the ones without data, but that is just blanks
			for (int k = 0 ; k < 3; k++) {
				for (int j = 0 ; j < 8; j++) {
					parent.player.get(current).card3.attF[k][j].setText(msg.get(0));
					msg.remove(0);
				}
			}

			//The same happens here as for card3
			for (int i = 0; i < Integer.parseInt(msg.get(0)); i++) {
				parent.player.get(current).card4.add(parent.player.get(current).card4.skillP[i]);
			}
			
			msg.remove(0);

			for (int k = 0 ; k < 9; k++) {
				for (int j = 0 ; j < 6; j++) {
					parent.player.get(current).card4.skillF[k][j].setText(msg.get(0));
					msg.remove(0);
				}
			}
			
			//After all this, we tell the program to display the new values
			parent.p4.revalidate();
		}
	}

	//Tests if the received roll is legal. Does this by simply trying to parse it to an int.
	private boolean legalroll(ArrayList <String> list) {
		try {
			String test = "";
			for (int i = 0; i < list.size(); i++) {
				test = test + list.get(i);
			}
			Integer.parseInt(test);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}