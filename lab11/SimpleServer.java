import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class SimpleServer {
	
	public static void main(String[] args) {
		
		ServerSocket server = null;
		Socket clientSocket = null;
		BufferedReader is = null;
		PrintWriter os = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			server = new ServerSocket(1254); //Port to which the client will connect to
			clientSocket = server.accept(); //Listener which will accept data input
			System.out.println("Port initialized");
			//convert InputStream to BufferedReader since InputStream is deprecated
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintWriter(clientSocket.getOutputStream(), true);

			System.out.println("Enter what you'd like to send to the client:");
			os.println(scan.nextLine());
			
			String read;
			System.out.println("Waiting for client response...");
			while((read = is.readLine()) != null) {
				System.out.println("Client: " + read);
				break;
			}
			
			server.close();
			scan.close();
			os.close();
			is.close();
			clientSocket.close();
		}
		
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
