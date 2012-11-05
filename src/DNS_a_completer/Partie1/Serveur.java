package Partie1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA.portable.OutputStream;

public class Serveur {

	private Socket connectSocket; 
	private ServerSocket server;
	
	public void connectServeur(int port){
		
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
	}
	
	public void sendMessage(String message){
		
		try {
			OutputStream out = (OutputStream) connectSocket.getOutputStream();
			out.write( message.getBytes());
			System.out.println("Send Message");
		}catch(IOException e){
			System.out.println("SendMessage exception"+e.getMessage());
		}
	}
	
	public void closeSocket(){
		try{
			connectSocket.close();
			server.close();
			System.out.println("Server Close");
		}catch(IOException e){
			System.out.println("CloseSocket exception"+ e.getMessage());
		}
	}
	
	public static void main(){
		
	}
}
