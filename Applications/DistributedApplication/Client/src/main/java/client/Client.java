package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet6Address;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class Client {
	protected static String HOST = "localhost";
	final static int PORT = 9999;

	SocketFactory socketFactory = SocketFactory.getDefault();
	
	public void sendMessage(String message) {
		try(Socket connection = socketFactory.createSocket(Inet6Address.getLocalHost(), PORT)){
			
			System.out.println("Connected to " + HOST + " on port " + PORT);
			PrintStream output = new PrintStream(connection.getOutputStream());
			output.println(message);
			
			
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String data = input.readLine();    //o cliente fica bloqueado ate que o servidor responda
			
//			System.out.println("Server says: " + data);
			input.close();
			output.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		String requestURL = "http://" + HOST+":"+PORT;
		
		
		new Client().sendMessage("coisas... muitas coisas");
		
		
	}
}
