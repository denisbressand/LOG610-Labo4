package Partie2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread{
	
	private ServerInterface serverInterface;
	private Integer port;
	
	private Socket connectSocket = null; 
	private ServerSocket server = null;

	public ServerThread(ServerInterface serverInterface, Integer port)
	{
		this.serverInterface = serverInterface;
		this.port = port;
	}
	
	public void run()
	{
		while(this.isAlive())
		{
		//code to execute goes here
			try{
				server = new ServerSocket(port);
				System.out.println("Server connect");
			}catch( IOException e){
				System.out.println("ServerSocket exception "+e.getMessage());
			}
		
			try {
				connectSocket = server.accept();
				System.out.println("Connect Socket");
				serverInterface.addMessageToList("Client connected");
			}catch(IOException e){
				System.out.println("Socket exception: "+e.getMessage());
			}
			
			String message = "Hello from server!";
			
			try {
				java.io.OutputStream out = connectSocket.getOutputStream();
				out.write( message.getBytes());
				System.out.println("Send Message");
				serverInterface.addMessageToList("Sent Message");
			}catch(IOException e){
				System.out.println("SendMessage exception"+e.getMessage());
			}
			
			try{
				connectSocket.close();
				server.close();
				System.out.println("Server Close");
				serverInterface.addMessageToList("Client disconnected");
			}catch(IOException e){
				System.out.println("CloseSocket exception"+ e.getMessage());
			}
		}
	}
	
	/* might not need this
	public void stop()
	{
		this.interrupt();
	}*/

}
