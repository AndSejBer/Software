package del2;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.text.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {
	private static int PORT = 8080; 
	
	public static void main(String[] args) {
		//We start the server on port PORT
		ServerSocket MyServSock = null;
		try {
			MyServSock = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println(e);
		}
		//The while loop stays running for as long the server doesn't crash horribly
		boolean serverActive = true;
		while (serverActive) {
			Socket klientSock = null;
			Scanner input = null;
			try {
				//We wait for a connection
				klientSock = MyServSock.accept();
				input = new Scanner(klientSock.getInputStream());
				System.out.println("Linked");
				//Once connected we receive a line of text generally "GET <ULR> HTTP/1.0" 
				//We split it at " " to retrieve the <ULR> part
				String sInput = input.nextLine();
				String[] words = sInput.split(" ");
				String word = words[1];
				//If it's the main page ai. "/" or "/index" we send them there otherwise we find out what they want
				if (word.equals("/") || word.startsWith("/index")) {
					getIndex(klientSock);
				} else {
					getPath(word,klientSock);
				}
				klientSock.close();
				//In case of a fatal error the server closes
			} catch (Exception e) {
				try {
					klientSock.close();
				} catch (Exception e2) {
					
				}
			}
		}
	}

	private static void getIndex(Socket klientSock) {
		try {
			//We initialize our output methods and our File object
			PrintWriter out = new PrintWriter(klientSock.getOutputStream(),true);
			File indexPage = new File("C://Users//Andreas//Desktop//Website//index");
			BufferedOutputStream bOut = new BufferedOutputStream(klientSock.getOutputStream());
			//As we know people want the main page we send it
			out.print("HTTP/1.0 200 OK \r\n");
			out.print("Content-Lenght: " + indexPage.length()+ " \r\n");
			out.print("Content-Type: text/html \r\n");
			out.print("Date: " + date()+" \r\n");
			out.print("\r\n");
			out.flush();
			Path path = Paths.get("C:/Users/Andreas/Desktop/Website/index.html");
			byte [] Byte= Files.readAllBytes(path);
			bOut.write(Byte, 0, Byte.length);
			bOut.flush();
			bOut.close();
			out.close();
		} catch (Exception e) {

		}

	}

	private static void getPath(String path, Socket klientSock) {
		try {
			//We initialize our output methods and our File object
			PrintWriter out = new PrintWriter(klientSock.getOutputStream(),true);
			BufferedOutputStream bOut = new BufferedOutputStream(klientSock.getOutputStream());
			//We get the path for the thing they want and then we find the name for it, so we can make sure we have it
			String pathName = "C:/Users/Andreas/Desktop/Website" + path;
			String extension[] = path.split("/");
			String currentEx = extension[extension.length-1];
			//As we want it to work whether you type "lorem.html" or "lorem" we take out .html if it's there
			//If it's a request for a picture we reroute them straight to the sending part
			if (currentEx.endsWith(".html")) {
				currentEx = currentEx.replace(".html","");
			}else if (currentEx.endsWith(".gif")||currentEx.endsWith(".jpg")||currentEx.endsWith(".bmp")||currentEx.endsWith(".png")) {
				sendFound (pathName, currentEx, bOut, out);
				return;
			} else {
				//If they asked for "lorem", we need to add".html" to the path
				pathName = pathName + ".html";
			}
			//If it's one of our pages we send the page, if not we send a 404
			if (currentEx.equals("lorem")||currentEx.equals("billeder2")||currentEx.equals("billeder")) {
				sendFound (pathName, currentEx, bOut, out);
			} else {
				currentEx = extension[extension.length-1];
				pathName = "C:/Users/Andreas/Desktop/Website/404.html";
				File page = new File(pathName);
				out.print("HTTP/1.0 404 Not Found \r\n");
				out.print("Content-Lenght: " + page.length()+ " \r\n");
				out.print("Content-Type: text/html \r\n");
				out.print("Date: " + date()+" \r\n");
				out.print("\r\n");
				out.flush();
				Path path0 = Paths.get(pathName);
				byte [] Byte= Files.readAllBytes(path0);
				bOut.write(Byte, 0, Byte.length);
				bOut.flush();
			}
		} catch (Exception e) {
		}

	}
	private static void sendPic(String pathName, File page, BufferedOutputStream bOut, PrintWriter out) {
		try {
			//This method deal with sending a random picture out of 7, for the 404 page
			int rand = ThreadLocalRandom.current().nextInt(1,8);
			String ext;
			if (rand == 2) {
				ext = "gif";
			} else {
				ext = "jpg";
			}
			pathName = "C:/Users/Andreas/Desktop/Website/404pics/" + rand + "." + ext;
			page = new File(pathName);
			out.print("HTTP/1.0 202 OK \r\n");
			out.print("Content-Lenght: " + page.length()+ " \r\n");
			out.print("Content-Type: image/" + ext + "\r\n");
			out.print("Date: " + date()+" \r\n");
			out.print("\r\n");
			out.flush();
			Path path = Paths.get(pathName);
			byte [] Byte= Files.readAllBytes(path);
			bOut.write(Byte, 0, Byte.length);
			bOut.flush();
		} catch (Exception e) {

		}
	}
	
	//Simple date format, used when ever we send stuff to the client
	private static String date() {
		SimpleDateFormat gmtFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return gmtFormat.format(Calendar.getInstance().getTime());
	}

	private static void sendFound (String pathName, String currentEx, BufferedOutputStream bOut, PrintWriter out) {
		try {
			File page = new File(pathName);
			//If the requested thing is for the 404 page, we go to the sendPic method
			if (currentEx.endsWith("404.gif")) {
				sendPic(pathName, page, bOut, out);
			} else {
				//At this point, we know we have the page or picture, so we start sending it
				out.print("HTTP/1.0 200 OK \r\n");
				out.print("Content-Lenght: " + page.length()+ " \r\n");
				out.print("Content-Type: ");
				//We have to send it's declaration, and so we just check what type it is and send that
				if (currentEx.endsWith("html")) {
					out.print("text/html \r\n");
				} else if (currentEx.endsWith("bmp")) {
					out.print("image/bmp \r\n");
				}else if (currentEx.endsWith("jpg")) {
					out.print("image/jpg \r\n");
				}else if (currentEx.endsWith("jng")) {
					out.print("image/jng \r\n");
				}else if (currentEx.endsWith("gif")) {
					out.print("image/gif \r\n");
				} else {
					//As we made it possible to go to "lorem" instead of "lorem.html", we have to cover the
					//possibility that people get here with currentEx=lorem or something like it
					out.print("text/html \r\n");
				}
				out.print("Date: " + date()+" \r\n");
				out.print("\r\n");
				out.flush();
				Path path = Paths.get(pathName);
				byte [] Byte= Files.readAllBytes(path);
				bOut.write(Byte, 0, Byte.length);
				bOut.flush();
			}
		} catch (IOException e) {

		}
	}
}
