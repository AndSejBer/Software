package dnd_penandpaper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//Version 1.0.0
//Last edit Andreas d.1/15/17
//Lines edited: 17, 18 (protected)

public class Server implements Runnable {
	//We intialize a few variables 
	private static final int PORT = 8080; 
	protected int current = 0;
	protected static ADM parent;
	protected ArrayList <DM> player = new ArrayList <DM>();

	
	//The constructor is here so the server part can add a new player to the main parts of the program.
	public Server(ADM dm) {
		parent = dm;
	}

	public void run() {
		//The server starts on port PORT
		ServerSocket MyServSock = null;
		try {
			MyServSock = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//The while loop stays running for as long the main program keeps it alive
		boolean serverActive = true;
		current = 0;
		System.out.println("Server active");
		while (serverActive) {
			Socket klientSock = new Socket();
			try {
				//We wait for a connection
				klientSock = MyServSock.accept();
				System.out.println("Linked: " + current);
				//We add the new connections to various storage places in the main program.
				parent.klients.add(klientSock);
				parent.playernum++;
				parent.outStream.add(new PrintWriter(klientSock.getOutputStream(),true));
				player.add(new DM(klientSock, current, parent));
				parent.player.add(player.get(current));
				//We start the program that receives all the communication from the player
				new Thread(player.get(current)).start();
				//To keep track of how many players there are currently on the server
				current++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
