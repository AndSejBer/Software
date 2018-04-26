package dnd_penandpaper;

import java.io.*;
import java.io.BufferedReader;
import java.net.*;
import javax.swing.*;

//Version 1.0.0
//Last edit Andreas d.1/13/18
//Lines edited:83,112-133

public class DM extends JFrame implements Runnable{

	//We initialize a few variables
	private static Socket klient = new Socket();
	protected int current;
	private static ADM dm;
	private String name;
	private boolean nameT = false;
	protected sheetCard1 card1=new sheetCard1(dm); 
	protected sheetCard2 card2=new sheetCard2(dm);
	protected sheetCard3 card3=new sheetCard3(dm); 
	protected sheetCard4 card4=new sheetCard4(dm);
	

	//This constructor keeps track of the assigned number, the main program and it's socket
	public DM(Socket kl, int now, ADM tdm) {
		dm = tdm;
		klient = kl;
		current = now;
	}

	//When a player leaves, the assigned numbers have to be updated accordingly, this is handled in the main program
	protected void updatenum(int num) {
		current = num;
	}

	//To get the name of the current player
	public String getName() {
		return name;
	}

	//Main communication part of the program
	public void run() {
		//If the player wasn't connected properly it wont go in the main part of the program, and cause crashes
		if (klient.isConnected()) {
			try {
				//The reader that receives the communication is made
				final BufferedReader input = new BufferedReader(new InputStreamReader(klient.getInputStream()));
				//The first line is read, this will always be the name of the player
				name = input.readLine();

				if (dm.players.contains(name)) {
					nameT = true;
				} else {
					//The name is stored to the main program
					dm.players.add(name);
					dm.n.add(0);
					dm.m.add(0);
					//The server sends an acknowledgement of a successful connection
					dm.outStream.get(current).print("accept \r\n");
					dm.outStream.get(current).flush();
					System.out.println("accept sent " + dm.klients.size() + " " + dm.playernum + " " + current + " " + name);
					//The chat displays a welcome message to the new player
					dm.chat.append("Hello there " + name + " has joined the fray! \r\n");
					for (int i = 0; i < dm.playernum; i++) {
						dm.outStream.get(i).print("Hello there " + name + " has joined the fray! \r\n");
						dm.outStream.get(i).flush();
					}

					//We start the character sheet for the current player, and add them to the GM's sheets
					JTabbedPane characterSheet = new JTabbedPane();
					
					characterSheet.add("Main page",card1);
					card1.sheetPlayerF.setText(name);
					card1.sheetPlayerF.setEditable(false);
					card1.repaint();
					characterSheet.add("Attributes",card2);
					characterSheet.add("Attack page", card3);
					characterSheet.add("Skill page", card4);
					
					dm.p4.add(name, characterSheet);
					dm.p4.revalidate();

					//We make this run until we receive a disconnect message
					boolean connected = true;
					while(connected) {
						//we continually read from the input, if it's not empty or just a space, we first check if it's a disconnect
						//otherwise we send it to the string sort class, and that will handle the rest
						String msg = input.readLine();
						if (!(msg.equals("") || msg.equals(" "))) {
							if(msg.startsWith("/closeConnection")) {
								//If a disconnect has been received, we set connected to false and move on to fully disconnecting and removing
								connected = false;
							} else {
								new Thread(new StringSort(name,dm,msg,current)).start();
							}
						}
					}

					//We remove the sheet, the out stream, Socket (klients), DM (player), reduce the player number,
					//and their name. After all this we have fully removed the player from the main part.
					dm.outStream.get(current).close();
				}
			}catch (Exception e1) {
				System.out.println(name + " " + current);
				e1.printStackTrace();

			}
			//Finally we reduce the current on the server side, and make the main program update all the player numbers.

			//If the name was already taken we send a message to the client telling them this, we then disconnect them
			if (nameT) {
				dm.outStream.get(current).print("Name is already in use \r\n");
				dm.outStream.get(current).flush();
			} 
			//These are always updated on a new connection and as such should always be removed
			dm.server.current--;
			dm.klients.remove(current);
			dm.playernum --;
			dm.outStream.remove(current);
			dm.server.player.remove(current);
			dm.player.remove(current);
			dm.updatePlayernum();
			
			//These should only be removed if the player name was not taken and since the guy never joined we don't send a goodbye message
			if (!(nameT)) {				
				dm.p4.remove(current);
				dm.p4.repaint();
				dm.players.remove(current);
				dm.n.remove(current);
				dm.m.remove(current);

				//For the rest of the players we then output a goodbye message
				dm.chat.append("Awww " + name + " has left the game! \r\n");
				for (int i = 0; i < dm.playernum; i++) {
					dm.outStream.get(i).print("Awww " + name + " has left the game! \r\n");
					dm.outStream.get(i).flush();
				}
			}
		}
	}
}

