package Partie1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.omg.CORBA.portable.InputStream;

public class Client {
	
	private Socket clientSocket; 
	
	public void openSocket(String host, int serverPort){
		try {
			clientSocket = new Socket(host, serverPort);
		}catch(IOException e ){
			System.out.println("OpenSocket exeption "+e.getMessage());
		}
	}
	
	public String readData(){
		int MAXLENGTH = 256; 
		byte[] buff = new byte[ MAXLENGTH];
		String read = null;
		try{
			InputStream in = (InputStream) clientSocket.getInputStream();
			in.read(buff);
			
			InputStreamReader is = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(is);
			read = br.readLine();
		}catch(IOException e){
			System.out.println("Exception readData "+e.getMessage());
		}
		return read;
		
	}
	
	public void closeSocket(){
		try{
			clientSocket.close();
		}catch(IOException e){
			System.out.println("CloseSocket exception "+e.getMessage());
		}
	}
	
	
	public static void main(){
		
		
		
	}

}
