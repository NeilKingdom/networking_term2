import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class SimpleClient {

	public static void main(String[] args) {
		
		Socket client = null;
		BufferedReader is = null;
		PrintWriter os = null; 
		Scanner scan = new Scanner(System.in);
		String hostname = "PC";
	
		try {
			client = new Socket(hostname, 1254); //server name and port it is running on
			System.out.println("Connected to " + client.getRemoteSocketAddress());
			//convert InputStream to BufferedReader since InputStream is deprecated
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			os = new PrintWriter(client.getOutputStream(), true);
			
			String read;
			System.out.println("Waiting for server response...");
			while((read = is.readLine()) != null) {
				System.out.println("Server: " + read);
				break;
			}

			System.out.println("Enter what you'd like to send to the server:");
			os.println(scan.nextLine());	
		
			is.close();
			scan.close();
			os.close();
			client.close();
		}
		
		catch (UnknownHostException e) {
			System.err.println("Trying to connect to unknown host: " + e);
		}
		catch(IOException e) {
			System.err.println("IOException: " + e);
		}
	}
}
