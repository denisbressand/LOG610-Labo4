package Partie1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	private Socket connectSocket = null; 
	private ServerSocket server = null;
	
	public String go(int port)
	{
		
		try{
			server = new ServerSocket(port);
			System.out.println("Server connect");
		}catch( IOException e){
			System.out.println("ServerSocket exception "+e.getMessage());
		}
	
		try {
			connectSocket = server.accept();
			System.out.println("Connect Socket");
		}catch(IOException e){
			System.out.println("Socket exception: "+e.getMessage());
		}
		
		//String message = "Hello from server!";
		
		
		
		String read = null;
		try{
			java.io.InputStream in =  connectSocket.getInputStream();
			//in.read(buff);
			
			InputStreamReader is = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(is);
			read = br.readLine();
			System.out.println("Read file");
		}catch(IOException e){
			System.out.println("Exception readData "+e.getMessage());
		}
		
		try{
			connectSocket.close();
			server.close();
			System.out.println("Server Close");
		}catch(IOException e){
			System.out.println("CloseSocket exception"+ e.getMessage());
		}
		
		return read;
	}
}
