package Partie1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.omg.CORBA.portable.InputStream;

public class Client {
	
	private Socket clientSocket; 
	private Boolean connected = false;	
	
	public String go(String host, int serverPort)
	{
		try {
			clientSocket = new Socket(host, serverPort);
			System.out.println("ClientSocket open");
			connected = true;
		}catch(IOException e ){
			System.out.println("OpenSocket exeption "+e.getMessage());
		}
		
		int MAXLENGTH = 256; 
		byte[] buff = new byte[ MAXLENGTH];
		String read = null;
		try{
			java.io.InputStream in =  clientSocket.getInputStream();
			//in.read(buff);
			
			InputStreamReader is = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(is);
			read = br.readLine();
			System.out.println("Read file");
		}catch(IOException e){
			System.out.println("Exception readData "+e.getMessage());
		}

		try{
			clientSocket.close();
			connected = false;
			System.out.println("Socket close");
		}catch(IOException e){
			System.out.println("CloseSocket exception "+e.getMessage());
		}
		
		return read;
		
	}
	
	public Boolean isConnected()
	{
		return connected;
	}

}
