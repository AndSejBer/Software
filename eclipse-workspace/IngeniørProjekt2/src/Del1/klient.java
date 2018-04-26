package Del1;

import java.io.*;
import java.net.*;
import java.util.*;

public class klient {
	public static void main(String[] args) {
		//We initialize the Socket and make the game display.
		Socket link = null;
		fillBox();
		try {
			//We connect to the server and initialize our scanners, writer and strings
			link = new Socket("itkomsrv.fotonik.dtu.dk",1102);
			Scanner in = new Scanner(link.getInputStream());
			PrintWriter out = new PrintWriter(link.getOutputStream(),true);
			Scanner entry = new Scanner(System.in);
			String message, response = null;
			// We print the first 2-3 messages from the server
			for (int i = 0; i<2; i++) {
				response = in.nextLine();
				System.out.println(response);
				if (response.endsWith("IS O")) {
					response = in.nextLine();
					System.out.println(response);
					printNice(response);
					printBoard();
				}
			}
			do {
				//We get our first user input, and test if it's a close command
				System.out.println("Enter position: ");
				message = entry.nextLine();
				if (message.equals("***CLOSE***")) {
					link.close();
					return;
				}
				//We send the msg
				out.print(message + "\r\n");
				out.flush();
				//Then we receive the server output and print it if it's "YOUR TURN" and input in our box if it's new positions. 
				//If someone won it closes
				for (int i = 0; i < 2; i++) {
					response = in.nextLine();
					if (response.equals("YOUR TURN")) {
						System.out.println(response);
					} else if (response.startsWith("BOARD")) {
						//See printNice and printBoard for further explanations
						printNice(response);
						printBoard();
					}
					if (response.endsWith("WINS")){
						System.out.println(response);
						return;
					}
				}
			} while (!message.equals("***CLOSE***"));
			in.close(); out.close(); entry.close(); link.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	//Our super nice board
	private static String niceBox[][] = new String[28][19];

	//We want to fill our board with spaces and formatting
	private static void fillBox() {
		//We fill it entirely with spaces
		for (int i = 0; i < 19; i++) {
			for (int j =0; j < 28; j++) {
				niceBox[j][i]=" ";
			}
		}
		//fieldNumber is the number of the boxes, 1,2,..,8,9
		int fieldNumber = 1;
		//For as long as there are more rows the go through
		for (int j = 0; j<19; j++ ) {
			//If j is divisible with 6, we can assume that the current line is a 
			//splitter between boxes, and is filled accordingly
			if (j % 6 == 0) {
				//For as long and there are colums
				for (int i = 0; i<28; i++) {
					//If i is divisible by 9, it is again a split between boxes and should be +, 
					//otherwise it should be -
					if (i % 9 == 0) {
						niceBox[i][j] = "+";
					} else {
						niceBox[i][j] = "-";
					}
				}
				//We use if else here to make sure it only fills each row with one of the options, in this case
				//if it's the row below a box split or the start, it should contain the box numbers ai. fieldNumber
			}else  if ((j-1) % 6 == 0) {
				for (int i = 0; i<28; i++) {
					//If it's divisible by 9, it's a box splitter
					if (i % 9 == 0) {
						niceBox[i][j] = "|";
						//if it's immediately after a wall it should have the number
					} else if ((i-1) % 9 == 0){
						niceBox[i][j] = Integer.toString(fieldNumber);
						fieldNumber++;
					}
				}
				//And the rest of the rows should only contain the walls for now
			} else {
				for (int i = 0; i<28; i++) {
					if (i % 9 == 0) {
						niceBox[i][j] = "|";
					}
				}
			}
		}
	}

	//Takes the servers board setup and converts it to an advanced game board aka. a super nice board
	private static void printNice(String response) {
		//We want to look at the characters after "BOARD IS " and see what fields should be filled with
		//either X or O or nothing, so we check each char at the 9'th position (zero index) and forward
		for (int j = 9; j<18; j++) {
			//We extract the char and convert it to string, to do simple .equals checks
			String ch = Character.toString(response.charAt(j));
			if (ch.equals("X") || ch.equals("O")) {
				//We start rows and colums at 1 and 3 because that is where we want to start for the first field
				int rows = 1;
				int colums = 3;
				//We then adjust the rows, so that we look at the right row
				if (j>11) {
					if (j>14) {
						rows = 13;
					} else {
						rows = 7;
					}
				}
				//And then we adjust the colum so we start at the right place in the colum.
				if (j == 10 || j == 13 || j==16) {
					colums = 12;
				} else if (j == 11 || j == 14 || j==17) {
					colums = 21; 
				}
				//For an "X" we want to change some spaces to # 
				if (ch.equals("X")) {
					//We only want to go through this 5 times as there is only five rows to be changed
					for (int count = 0; count <5; count ++) {
						//if count is 0 or 4, we'll make the top or bottom, notice that count is added
						//to rows, as to put the # at in the right row.
						if (count == 0 || count == 4) {
							niceBox[colums][rows+count] = "#";
							niceBox[colums + 4][rows+count] = "#";
							//if count is 1 or 3, we'll make the second or forth row
						} else if (count == 1 || count == 3) {
							niceBox[colums+1][rows+count] = "#";
							niceBox[colums+3][rows+count] = "#";
							//if count is 2, which it has to be at this point as we've used if else statements 
							//the whole way, we'll make the 2'nd or 4'th row
						} else {
							niceBox[colums+2][rows+count] = "#";
						}
					}
					//If it's not X then it has to be O
				} else {
					for (int count = 0; count <5; count ++) {
						//For row 0 and 4 we'll make the top and bottom
						if (count == 0 || count == 4) {
							niceBox[colums+1][rows+count] = "#";
							niceBox[colums+2][rows+count] = "#";
							niceBox[colums+3][rows+count] = "#";
							//For the rest of the rows we just want to make the sides
						} else {
							niceBox[colums][rows+count] = "#";
							niceBox[colums+4][rows+count] = "#";
						}
					}
				}
			}
		}
		return;
	}

	//We print out the board
	private static void printBoard() {
		for (int i = 0; i < 19; i++) {
			for (int j =0; j < 28; j++) {
				System.out.print(niceBox[j][i]);
			}
			System.out.println();
		}
	}
}//Some obsolete things were removed