<<<<<<< HEAD:src/DNS_a_completer/Partie1/Serveur.java
package Partie1;

public class Serveur {

	public static void main(){
		
	}
}
=======
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
		}catch( IOException e){
			System.out.println("ServerSocket exception "+e.getMessage());
		}
	
		try {
			connectSocket = server.accept();
		}catch(IOException e){
			System.out.println("Socket exception: "+e.getMessage());
		}
	}
	
	public void sendMessage(String message){
		
		try {
			OutputStream out = (OutputStream) connectSocket.getOutputStream();
			out.write( message.getBytes());
		}catch(IOException e){
			System.out.println("SendMessage exception"+e.getMessage());
		}
	}
	
	public void closeSocket(){
		try{
			connectSocket.close();
			server.close();
		}catch(IOException e){
			System.out.println("CloseSocket exception"+ e.getMessage());
		}
	}
	
	public static void main(){
		
	}
}
>>>>>>> MOdele:DNS_a_completer/Partie1/Serveur.java
