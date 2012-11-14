package Partie1;

import java.io.IOException;
import java.net.Socket;

public class Client {
	
	private Socket clientSocket; 
	private Boolean connected = false;	
	
	public void go(String host, int serverPort, String message)
	{
		try {
			clientSocket = new Socket(host, serverPort);
			System.out.println("ClientSocket open");
			connected = true;
		}catch(IOException e ){
			System.out.println("OpenSocket exeption "+e.getMessage());
		}
		
		try {
			java.io.OutputStream out = clientSocket.getOutputStream();
			out.write( message.getBytes());
			System.out.println("Send Message");
		}catch(IOException e){
			System.out.println("SendMessage exception"+e.getMessage());
		}

		try{
			clientSocket.close();
			connected = false;
			System.out.println("Socket close");
		}catch(IOException e){
			System.out.println("CloseSocket exception "+e.getMessage());
		}
		
		
	}
	
	public Boolean isConnected()
	{
		return connected;
	}

}
