package dnd_penandpaper;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;


//Version 1.0.0
//Last edit Andreas d.1/13/18
//Lines edited: (remove as you go) 97-100, 193-220, 196-212

public class ADM extends JFrame implements ActionListener {

	//Initializes most of the variables
	private static JButton b[] = new JButton [5];
	protected JTextArea DMchat, chat;
	protected JTextField writeChat, diceAmountF,diceSidesF;
	protected JLabel diceAmountL, diceSidesL;
	protected Server server;
	protected int playernum;
	protected JTabbedPane p4 = new JTabbedPane();
	protected JPanel p6 = new JPanel();
	protected ArrayList <JButton> sheetP = new ArrayList<JButton>();
	protected ArrayList <String> players = new ArrayList<String>();
	protected ArrayList <Socket> klients = new ArrayList<Socket>();
	protected ArrayList <PrintWriter> outStream = new ArrayList<PrintWriter>();
	protected ArrayList <DM> player = new ArrayList<DM>();
	protected ArrayList <Integer> n = new ArrayList<Integer>();
	protected ArrayList <Integer> m = new ArrayList<Integer>();

	//Sets up the layout of the GM
	public ADM() {
		//We set the player number to 0 and start the server 
		playernum = 0;
		server = new Server(this);
		new Thread(server).start();

		//The layout of the main part is a grid layout with 4 slots
		getContentPane().setLayout(new GridLayout(1,2));

		//The chat and GM text area is setup
		DMchat = new JTextArea();
		DMchat.setLineWrap(true);
		JScrollPane DMscrollpane = new JScrollPane(DMchat);
		DMscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		DMscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		chat = new JTextArea();
		chat.setLineWrap(true);
		JScrollPane chatscrollpane = new JScrollPane(chat);
		chatscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chat.setEditable(false);

		writeChat = new JTextField(40);

		//The buttons are set up
		for (int i = 0; i < b.length; i++) {
			b[i] = new JButton("");
			b[i].addActionListener(this);
			b[i].setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		b[0].setText("Send");
		b[1].setText("Update");
		b[2].setText("Set");
		b[3].setText("Add");
		b[4].setText("Roll");

		//The chat and GM area are added to a panel with their buttons and text field
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		p1.add(writeChat);
		p1.add(b[0]);

		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		p2.add(b[2]);
		p2.add(b[3]);

		//These are added together and will fill one of the 3 main areas
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
		p3.add(DMscrollpane);
		p3.add(p2);
		p3.add(chatscrollpane);
		p3.add(p1);

		//We set up roll area		
		diceAmountF = new JTextField(40);
		diceSidesF = new JTextField(40);
		diceAmountL = new JLabel("Amount");
		diceSidesL = new JLabel("Sides");
		JPanel rollL = new JPanel();
		rollL.setLayout(new GridLayout(1,2));
		rollL.add(diceSidesL);
		rollL.add(diceAmountL);
		JPanel rollF = new JPanel();
		rollF.setLayout(new GridLayout(1,2));
		rollF.add(diceSidesF);
		rollF.add(diceAmountF);
		JPanel buttons = new JPanel();
		buttons.add(b[4]);
		buttons.add(b[1]);
		JPanel rollp = new JPanel();
		rollp.setLayout(new GridLayout(3,1));
		rollp.add(rollL);
		rollp.add(rollF);
		rollp.add(buttons);

		//We add the sheet and the roll + update to a panel
		p6.setLayout(new BoxLayout(p6, BoxLayout.Y_AXIS));
		p6.add(p4);
		p6.add(rollp);

		//Finally we add all the parts
		getContentPane().add(p6);
		getContentPane().add(p3);

		//When the GM closes the GM should be the last person to exit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (playernum > 0) {
					for (int i = 0; i < playernum; i++) {
						outStream.get(i).print("GM is a fag, and has closed the connection before you did. Hopefully you did not crash \r\n");
						outStream.get(i).flush();
					}
				}
				System.exit(0);
			}
		});

	}

	//The main program
	public static void main(String args[]) {		
		ADM GMGui = new ADM();
		GMGui.setTitle("Gamemaster");
		GMGui.setSize(1080, 800);
		GMGui.setResizable(true);
		GMGui.setVisible(true);      
	}

	//If any button is pressed
	public void actionPerformed(ActionEvent e) {

		//if it's send chat, we get the text and if it's not empty we send it to string sort and that handles the rest
		if (e.getSource() == b[0]) {
			String s1 = writeChat.getText();
			if (!(s1.isEmpty())) {
				StringSort sort = new StringSort("GM", this,"/chat " + s1, -1);
				new Thread(sort).start();
			}
			writeChat.setText("");
			//If it's the update button, GM wants to get an update on the charsheets 
		} else if(e.getSource() == b[1]) {
			for (int i = 0; i < klients.size();i ++) {
				outStream.get(i).print("/update \r\n");
				outStream.get(i).flush();
			}
			//The GM can set or add to his GM text area
		} else if(e.getSource() == b[2]) {
			String dmchat = DMchat.getText();		
			for (int i = 0; i < klients.size();i ++) {
				outStream.get(i).print("/setDMChat " + dmchat);
				outStream.get(i).print("\r\n");
				outStream.get(i).flush();
				outStream.get(i).print("/endOfMsg \r\n");
				outStream.get(i).flush();
			}
		} else if(e.getSource() == b[3]) {
			for (int i = 0; i < klients.size();i ++) {
				outStream.get(i).print("/addDMChat " + DMchat.getText());
				outStream.get(i).print("\r\n");
				outStream.get(i).flush();
				outStream.get(i).print("/endOfMsg \r\n");
				outStream.get(i).flush();
			}
		//The rolls are handled here
		} else 	if (e.getSource() == b[4]) {
			String sidesS = diceSidesF.getText();
			String amountS = diceAmountF.getText();
			new Thread(new StringSort("GM", this, "/chat /roll "+amountS +"d" +sidesS, -1)).start();
		}
	}

	//When a player leaves, we want to update the numbers assigned to each player, that is handled here
	//Where they are once again assigned the number that corresponds to their position in the storage
	protected void updatePlayernum() {
		for (int i = 0; i < klients.size(); i++) {
			player.get(i).updatenum(i);
		}
	}
}
