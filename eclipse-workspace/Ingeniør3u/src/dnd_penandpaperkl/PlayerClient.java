package dnd_penandpaperkl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.Pattern;
import java.io.*;
import java.net.*;
import java.util.*;

//Version 0.1.5
//Last edit Rasmus d.1/13/18
//Lines edited: 351-562 save and load works fully

public class PlayerClient extends JPanel implements ActionListener {
	CardLayout card = new CardLayout(50, 50); // Create layout
	protected JTextArea DMchat, chat;
	private JTextField writeChat, IPF, nameF, diceAmountF,diceSidesF, fileNameF;
	protected JButton sendChat,send, roll, addSkill, sendCharSheet, saveCharSheet, loadCharSheet;
	protected JLabel IPL, nameL, diceAmountL, diceSidesL,chatL, DMchatL,writeChatL;
	protected String sIP, charSheetS, sname;
	protected static Socket link = null;
	protected static PlayerConnection connection;
	protected static PrintWriter pwout, pwFile;
	protected int n,m = 0;
	protected sheetCard1 card1=new sheetCard1(this); 
	protected sheetCard2 card2=new sheetCard2(this);
	protected sheetCard3 card3=new sheetCard3(this);
	protected sheetCard4 card4=new sheetCard4(this);
	protected File saveFile;

	public PlayerClient() {
		//Layout for main JPanel
		setLayout(card);
		Dimension btnsize = new Dimension(100, 30);
		connection = new PlayerConnection(this);

		//DMchat textpanel
		DMchat = new JTextArea();
		DMchat.setLineWrap(true);
		JScrollPane DMscrollpane = new JScrollPane(DMchat);
		DMscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		DMscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		DMchat.setEditable(false);

		//Player chat textpanel
		chat = new JTextArea();
		chat.setLineWrap(true);
		JScrollPane chatscrollpane = new JScrollPane(chat);
		chatscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chat.setEditable(false);

		//send chat button
		sendChat = new JButton("Send");
		sendChat.addActionListener(this);
		sendChat.setMaximumSize(btnsize);
		sendChat.setAlignmentX(Component.CENTER_ALIGNMENT);

		//textfield for writing chat messages
		writeChat = new JTextField(40);

		//CharacterSheet as tabbedpane
		JTabbedPane characterSheet = new JTabbedPane();

		//button for add attack
		card3.addAtt.addActionListener(this);
		card4.addSkill.addActionListener(this);

		//charactersheet tabbedpane adding custom jpanels from class sheetCard1-4
		characterSheet.add("Main Page",card1);
		characterSheet.add("Attributes",card2);
		characterSheet.add("Attacks",card3);
		characterSheet.add("Skills",card4);

		//button for sending char sheet to DM
		sendCharSheet= new JButton("Send Character Sheet");
		sendCharSheet.addActionListener(this);
		sendCharSheet.setMaximumSize(btnsize);
		sendCharSheet.setAlignmentX(Component.CENTER_ALIGNMENT);

		//save charsheet locally(in same folder as the program is running)
		saveCharSheet= new JButton("Save Character Sheet");
		saveCharSheet.addActionListener(this);
		saveCharSheet.setMaximumSize(btnsize);
		saveCharSheet.setAlignmentX(Component.CENTER_ALIGNMENT);

		//load charsheet from save file
		loadCharSheet= new JButton("Load Character Sheet");
		loadCharSheet.addActionListener(this);
		loadCharSheet.setMaximumSize(btnsize);
		loadCharSheet.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Filename for save and load
		fileNameF = new JTextField(40);
		JLabel fileNameL = new JLabel("File Name");

		//Panel for filename
		JPanel fileNameP = new JPanel();
		fileNameP.setLayout(new BoxLayout(fileNameP,BoxLayout.Y_AXIS));
		fileNameP.add(fileNameL);
		fileNameP.add(fileNameF);

		//panel save,load and filename
		JPanel fileP = new JPanel();
		fileP.setLayout(new GridLayout(1,3));
		fileP.add(saveCharSheet);
		fileP.add(loadCharSheet);
		fileP.add(fileNameP);


		//Chat labels
		writeChatL=new JLabel("Write message");
		DMchatL =new JLabel("DM Chat");
		chatL = new JLabel("Chat");
		writeChatL.setAlignmentX(Component.CENTER_ALIGNMENT);
		DMchatL.setAlignmentX(Component.CENTER_ALIGNMENT);
		chatL.setAlignmentX(Component.CENTER_ALIGNMENT);


		//input part of chat
		JPanel chatLabelPanel =new JPanel(); 
		chatLabelPanel.setLayout(new BoxLayout(chatLabelPanel,BoxLayout.Y_AXIS));
		chatLabelPanel.add(writeChatL);
		chatLabelPanel.add(writeChat);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2,1));
		p1.add(chatLabelPanel);
		p1.add(sendChat);

		//all chat areas combined (right part of login screen)
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
		p2.add(DMchatL);
		p2.add(DMscrollpane);
		p2.add(chatL);
		p2.add(chatscrollpane);
		p2.add(p1);

		//roll part of screen
		roll = new JButton("Roll");
		roll.addActionListener(this);
		roll.setMaximumSize(btnsize);
		roll.setAlignmentX(Component.CENTER_ALIGNMENT);

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

		JPanel rollp = new JPanel();
		rollp.setLayout(new GridLayout(5,1));
		rollp.add(sendCharSheet);
		rollp.add(fileP);
		rollp.add(rollL);
		rollp.add(rollF);
		rollp.add(roll);

		//gathering of roll and charsheet(left side of logged in screen)
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
		panel1.add(characterSheet);
		panel1.add(rollp);

		//logged in screen
		JPanel Playerp = new JPanel();
		Playerp.setLayout(new GridLayout(1,2));
		Playerp.add(panel1);
		Playerp.add(p2);

		//login JPanel(send is for connecting)
		send = new JButton("Ready? Click Here");
		send.addActionListener(this);
		send.setMaximumSize(btnsize);
		send.setAlignmentX(Component.CENTER_ALIGNMENT);

		IPF = new JTextField(40);
		nameF = new JTextField(40);

		IPL = new JLabel("IP-Adress");
		nameL = new JLabel("Name        ");

		JPanel p3 = new JPanel();// IP
		p3.setLayout(new BorderLayout());
		p3.add(IPL,BorderLayout.WEST);
		p3.add(IPF,BorderLayout.CENTER);

		JPanel p4 = new JPanel();// Name
		p4.setLayout(new BorderLayout());
		p4.add(nameL,BorderLayout.WEST);
		p4.add(nameF,BorderLayout.CENTER);

		// login screen layout
		JPanel Loginp = new JPanel();
		Loginp.setLayout(new GridLayout(3,1));
		Loginp.add(p3);
		Loginp.add(p4);
		Loginp.add(send);

		//add's frames to the CardLayout, starts and login screen
		add(Loginp);
		add(Playerp);	
	}
	//regular expression for ip adress
	private static final Pattern PATTERN = Pattern.compile(
			"^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	//validate ip to see if what was written is a valid ip
	public static boolean validate(final String ip) {
		return PATTERN.matcher(ip).matches();
	}

	// Handle button events
	public void actionPerformed(ActionEvent e) {
		try {
			//login send info
			if (e.getSource() == send) {
				String sIP = IPF.getText();//gets text from IP field
				String sname = nameF.getText();//gets text from name
				System.out.println("Requesting connection");
				if (validate(sIP)) {//checks for valid IP
					link = new Socket(sIP,8080);//attempts to connect
					pwout = new PrintWriter(link.getOutputStream(),true);
					pwout.print(sname+"\r \n");
					pwout.flush();
					new Thread(connection).start();//starts connection thread
					card1.sheetPlayerF.setText(sname);//sets name in charsheet
					card1.revalidate();
				}else {
					nameF.setText("Invalid IP");
				}
			}
		}catch(Exception e1) {
			//error message for not connecting
			nameF.setText("Something went wrong when connecting, probably a wrong IP.");
			e1.printStackTrace();
		}

		//send from textfield in chat(input) to DM while connected
		if (e.getSource() == sendChat) {
			String s1 ="/chat " + writeChat.getText()+"\r\n";
			if (!s1.equals("\r\n")) {
				pwout.print(s1);
				pwout.flush();
				writeChat.setText("");
			}	
		}
		// roll button
		if (e.getSource() == roll) {
			String sidesS = diceSidesF.getText();
			String amountS = diceAmountF.getText();
			pwout.print("/chat /roll "+amountS +"d" +sidesS+ "\r \n");//test
			pwout.flush();

		}
		//add attack button for charsheetcard3
		if (e.getSource() == card3.addAtt) {
			if (n<3) {
				card3.add(card3.attP[n]);
				card3.revalidate();
				n++;
			}
			else 
				System.out.println("There are no more space for Attacks");
		}
		//add skill button for charsheetcard4
		if (e.getSource() == card4.addSkill) {
			if (m<9) {
				card4.add(card4.skillP[m]);
				card4.revalidate();
				m++;
			}
			else {
				System.out.println("There are no more space for skills");}

		}
		try {
			//send charsheet to DM from info in textFields on charsheet
			if (e.getSource() == sendCharSheet) {
				charSheetS = "/charSheet ";

				charSheetS = charSheetS + " /next " 
						+ card1.sheetPlayerF.getText()+" /next " 
						+ card1.sheetCharacterNameF.getText()+" /next "
						+ card1.sheetClassAndLevelF.getText()+" /next "
						+ card1.sheetRaceF.getText()+" /next "
						+ card1.sheetAlignmentF.getText()+" /next "
						+ card1.sheetDeityF.getText()+" /next "
						+ card1.sheetSizeF.getText()+" /next "
						+ card1.sheetAgeF.getText()+" /next "
						+ card1.sheetGenderF.getText()+" /next "
						+ card1.sheetHeightF.getText()+" /next "
						+ card1.sheetWeightF.getText()+" /next "
						+ card1.sheetEyesF.getText()+" /next "
						+ card1.sheetHairF.getText()+" /next "
						+ card1.sheetSkinF.getText();


				charSheetS = charSheetS + " /next " 
						+ card2.ACtotalF.getText() +" /next "
						+ card2.ACarmorF.getText() +" /next "
						+ card2.ACshieldF.getText() +" /next "
						+ card2.ACdexF.getText() +" /next "
						+ card2.ACsizeF.getText() +" /next "
						+ card2.ACnaturalF.getText() +" /next "
						+ card2.ACdeflectionF.getText() +" /next "
						+ card2.ACmiscF.getText() +" /next "
						+ card2.sheetBaseAttackBonusF.getText() +" /next "
						+ card2.sheetSpellResistanceF.getText() +" /next "
						+ card2.sheetSpeedF.getText() +" /next "
						+ card2.sheetInitiativeF.getText() +" /next "
						+ card2.sheetTouchF.getText() +" /next "
						+ card2.sheetFlatFootF.getText() +" /next "
						+ card2.sheetGrappleTotF.getText() +" /next "
						+ card2.sheetGrappleBaseAttBonusF.getText() +" /next "
						+ card2.sheetGrappleStrengthModF.getText() +" /next "
						+ card2.sheetGrappleSizeModF.getText() +" /next "
						+ card2.sheetGrappleMiscModF.getText() +" /next "
						+ card2.HitPointsTotF.getText() +" /next "
						+ card2.WoundsF.getText() +" /next "
						+ card2.nonlethalF.getText();


				for (int i = 0 ; i < card2.at.length; i++) {
					charSheetS = charSheetS+ " /next " +card2.at[i].getText();
				}

				for (int i = 0 ; i < card2.stF.length; i++) {
					charSheetS = charSheetS + " /next " + card2.stF[i].getText();
				}

				charSheetS = charSheetS + " /next " + n;

				for (int i = 0 ; i < 3; i++) {
					for (int j = 0 ; j < 8; j++) {
						charSheetS = charSheetS + " /next " + card3.attF[i][j].getText();
					}
				}

				charSheetS = charSheetS + " /next " + m;

				for (int i = 0 ; i < 9; i++) {
					for (int j = 0 ; j < 6; j++) {
						charSheetS = charSheetS + " /next " + card4.skillF[i][j].getText();
					}
				}
				charSheetS = charSheetS + "\r\n";
				System.out.println(charSheetS);

				pwout.print(charSheetS);
				pwout.flush();
			}

		}catch(Exception e2) {
			//message for error when sending to DM
			System.out.println("Unable to send charsheet to DM");
			e2.printStackTrace();
		}
		//saving charsheet locally
		if (e.getSource() == saveCharSheet) {
			BufferedWriter writer = null;
			try {
				String sFileName = fileNameF.getText()+ ".txt";//makes filename
				System.out.println("saving to :" + sFileName);
				//gets local directory from the current class
				final File dir = new File(PlayerClient.class.getProtectionDomain().getCodeSource().getLocation().getPath());
				saveFile = new File(dir,sFileName);//makes file for functions in java
				saveFile.createNewFile();//creates new file on pc
			}catch(Exception e3){
				System.out.println("Error when creating file, or finding directory");
			}
			//creates String that is written to file
			charSheetS = "/charSheet ";

			charSheetS = charSheetS + " /next " 
					+ card1.sheetPlayerF.getText()+" /next " 
					+ card1.sheetCharacterNameF.getText()+" /next "
					+ card1.sheetClassAndLevelF.getText()+" /next "
					+ card1.sheetRaceF.getText()+" /next "
					+ card1.sheetAlignmentF.getText()+" /next "
					+ card1.sheetDeityF.getText()+" /next "
					+ card1.sheetSizeF.getText()+" /next "
					+ card1.sheetAgeF.getText()+" /next "
					+ card1.sheetGenderF.getText()+" /next "
					+ card1.sheetHeightF.getText()+" /next "
					+ card1.sheetWeightF.getText()+" /next "
					+ card1.sheetEyesF.getText()+" /next "
					+ card1.sheetHairF.getText()+" /next "
					+ card1.sheetSkinF.getText();


			charSheetS = charSheetS + " /next " 
					+ card2.ACtotalF.getText() +" /next "
					+ card2.ACarmorF.getText() +" /next "
					+ card2.ACshieldF.getText() +" /next "
					+ card2.ACdexF.getText() +" /next "
					+ card2.ACsizeF.getText() +" /next "
					+ card2.ACnaturalF.getText() +" /next "
					+ card2.ACdeflectionF.getText() +" /next "
					+ card2.ACmiscF.getText() +" /next "
					+ card2.sheetBaseAttackBonusF.getText() +" /next "
					+ card2.sheetSpellResistanceF.getText() +" /next "
					+ card2.sheetSpeedF.getText() +" /next "
					+ card2.sheetInitiativeF.getText() +" /next "
					+ card2.sheetTouchF.getText() +" /next "
					+ card2.sheetFlatFootF.getText() +" /next "
					+ card2.sheetGrappleTotF.getText() +" /next "
					+ card2.sheetGrappleBaseAttBonusF.getText() +" /next "
					+ card2.sheetGrappleStrengthModF.getText() +" /next "
					+ card2.sheetGrappleSizeModF.getText() +" /next "
					+ card2.sheetGrappleMiscModF.getText() +" /next "
					+ card2.HitPointsTotF.getText() +" /next "
					+ card2.WoundsF.getText() +" /next "
					+ card2.nonlethalF.getText();


			for (int i = 0 ; i < card2.at.length; i++) {
				charSheetS = charSheetS+ " /next " +card2.at[i].getText();
			}

			for (int i = 0 ; i < card2.stF.length; i++) {
				charSheetS = charSheetS + " /next " + card2.stF[i].getText();
			}
			//adds correct number of attack panels to the string
			charSheetS = charSheetS + " /next " + n;

			for (int i = 0 ; i < 3; i++) {
				for (int j = 0 ; j < 8; j++) {
					charSheetS = charSheetS + " /next " + card3.attF[i][j].getText();
				}
			}
			//adds correct number of skill panels to the string
			charSheetS = charSheetS + " /next " + m;

			for (int i = 0 ; i < 9; i++) {
				for (int j = 0 ; j < 6; j++) {
					charSheetS = charSheetS + " /next " + card4.skillF[i][j].getText();
				}
			}
			charSheetS = charSheetS + "\r\n";
			try {
				//writes to file by creating buffered writer
				writer = new BufferedWriter(new FileWriter(saveFile));
				writer.write(charSheetS);
				writer.flush();
				writer.close();
			}
			catch(Exception write){
				System.out.println("Could not write to file");
			}

		}
		//load function
		if (e.getSource() == loadCharSheet) {
			String sFileName = fileNameF.getText()+".txt";//gets filename
			final File dirRead = new File(PlayerClient.class.getProtectionDomain().getCodeSource().getLocation().getPath());//gets directory
			String readLocationS = dirRead +"\\"+sFileName;//finds location
			System.out.println("Location for reading file" + readLocationS);
			try{

				//reads file(gets string "read" which contains the text from the .txt file
				BufferedReader reader = new BufferedReader(new FileReader(dirRead +"\\" +sFileName));
				String read = reader.readLine();
				read.toString();
				System.out.println(read);
				reader.close();
				
				//takes "read" and puts it into the char sheet
				String ssaveFile = read.replaceAll("/charSheet ","");//removes header
				System.out.println(ssaveFile);//prints file for testing
				String sloadFile1 [] = ssaveFile.split(" /next ");
				ArrayList <String> sloadFile = new ArrayList<String>();//splits file to array
				
				//takes array and inputs data into all fields on the charSheet
				for (int i = 0; i < sloadFile1.length; i++) {
					sloadFile.add(sloadFile1[i]);
				}
				for (int i = 0; i < sloadFile.size(); i++) {
					System.out.println(sloadFile.get(i));
				}

				sloadFile.remove(0);
				sloadFile.remove(0);
				card1.sheetCharacterNameF.setText(sloadFile.get(0));
				card1.sheetClassAndLevelF.setText(sloadFile.get(1));
				card1.sheetRaceF.setText(sloadFile.get(2));
				card1.sheetAlignmentF.setText(sloadFile.get(3));
				card1.sheetDeityF.setText(sloadFile.get(4));
				card1.sheetSizeF.setText(sloadFile.get(5));
				card1.sheetAgeF.setText(sloadFile.get(6));
				card1.sheetGenderF.setText(sloadFile.get(7));
				card1.sheetHeightF.setText(sloadFile.get(8));
				card1.sheetWeightF.setText(sloadFile.get(9));
				card1.sheetEyesF.setText(sloadFile.get(10));
				card1.sheetHairF.setText(sloadFile.get(11));
				card1.sheetSkinF.setText(sloadFile.get(12));

				for (int j = 0; j < 13; j++) {
					sloadFile.remove(0);
				}

				card2.ACtotalF.setText(sloadFile.get(0));
				card2.ACarmorF.setText(sloadFile.get(1));
				card2.ACshieldF.setText(sloadFile.get(2));
				card2.ACdexF.setText(sloadFile.get(3));
				card2.ACsizeF.setText(sloadFile.get(4));
				card2.ACnaturalF.setText(sloadFile.get(5));
				card2.ACdeflectionF.setText(sloadFile.get(6));
				card2.ACmiscF.setText(sloadFile.get(7));
				card2.sheetBaseAttackBonusF.setText(sloadFile.get(8));
				card2.sheetSpellResistanceF.setText(sloadFile.get(9));
				card2.sheetSpeedF.setText(sloadFile.get(10));
				card2.sheetInitiativeF.setText(sloadFile.get(11));
				card2.sheetTouchF.setText(sloadFile.get(12));
				card2.sheetFlatFootF.setText(sloadFile.get(13));
				card2.sheetGrappleTotF.setText(sloadFile.get(14));
				card2.sheetGrappleBaseAttBonusF.setText(sloadFile.get(15));
				card2.sheetGrappleStrengthModF.setText(sloadFile.get(16));
				card2.sheetGrappleSizeModF.setText(sloadFile.get(17));
				card2.sheetGrappleMiscModF.setText(sloadFile.get(18));
				card2.HitPointsTotF.setText(sloadFile.get(19));
				card2.WoundsF.setText(sloadFile.get(20));
				card2.nonlethalF.setText(sloadFile.get(21));		

				for (int j = 0; j < 22; j++) {
					sloadFile.remove(0);
				}

				for (int j = 0 ; j < 24; j++) {
					System.out.println(sloadFile.get(0) + " ");
					card2.at[j].setText(sloadFile.get(0));
					sloadFile.remove(0);
				}

				for (int j = 0 ; j < 18; j++) {
					card2.stF[j].setText(sloadFile.get(0));
					sloadFile.remove(0);
				}
				//adds correct number of attackPanels
				n=0;
				for (int i = 0; i < Integer.parseInt(sloadFile.get(0)); i++) {
					card3.add(card3.attP[i]);
					n++;

				}
				sloadFile.remove(0);

				for (int k = 0 ; k < 3; k++) {
					for (int j = 0 ; j < 8; j++) {
						card3.attF[k][j].setText(sloadFile.get(0));
						sloadFile.remove(0);
					}
				}
				//adds correct number of skill Panels
				m=0;
				for (int i = 0; i < Integer.parseInt(sloadFile.get(0)); i++) {
					card4.add(card4.skillP[i]);
					m++;
				}

				sloadFile.remove(0);

				for (int k = 0 ; k < 9; k++) {
					for (int j = 0 ; j < 6; j++) {
						card4.skillF[k][j].setText(sloadFile.get(0));
						sloadFile.remove(0);
					}
				}
				card4.skillF[9][6].setText(sloadFile.get(0));
				
				//revalidates all cards
				card1.revalidate();
				card2.revalidate();
				card3.revalidate();
				card4.revalidate();

			}catch(Exception reader) {
				chat.append("Could not load the file \r\n");
				chat.setCaretPosition(chat.getDocument().getLength());
				System.out.println("reader issues");//exception for the reader going wrong(due to variable use the try catch had to be long)
			}

		}


	}


	public static void main(String[] args) {		
		JFrame PlayerGui = new JFrame();//creates JFrame
		
		//gets size and width for creating JFrame
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int heightScreen = size.height;
		int widthScreen = size.width;
		
		//sets up player window's JFrame
		PlayerGui.setTitle("Player");
		PlayerGui.setBounds(30,30,widthScreen, heightScreen);     
		PlayerGui.setResizable(true);  
		PlayerGui.getContentPane().add(new PlayerClient());
		PlayerGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayerGui.setVisible(true);
		//adds trigger for disconnecting when window is closed
		PlayerGui.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//maybe add some saving of stats or whatever
				try {
					if (link.isConnected()) {
						pwout.print("/closeConnection \r\n");
						pwout.flush();
						pwout.close();
					}
					link.close();
				} catch (Exception e1) {

				}
				System.exit(0);
			}
		});

		System.out.println("GUI Started");
	}
}