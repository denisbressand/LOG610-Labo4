package Partie2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class ServerThread extends Thread{
	
	private ServerInterface serverInterface;
	private Socket connectSocket = null; 
	private ServerSocket server = null;

	public ServerThread(ServerInterface serverInterface,ServerSocket server, Socket connectSocket)
	{
		this.serverInterface = serverInterface;
		this.server = server;
		this.connectSocket = connectSocket;
	}
	
	public void run()
	{
		{
			String read = "";
			try {
				
				System.out.println("Connect Socket");
				serverInterface.addMessageToList("Client connected");
				
				java.io.InputStream in =  connectSocket.getInputStream();
			
				InputStreamReader is = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(is);
				read += br.readLine() + "\n";
				
				if(read.contains("GET")){
					System.out.println(read);
					serverInterface.addMessageToList("Recieved message");
					String lien=  read.substring(read.indexOf("/")+1, read.indexOf("H")-1);
					String fileString =lien;
					if(!fileString.equals("favicon.ico")){
						String message = "";
						
						try {
							
							java.io.FileInputStream fileInput = null;
							File file = new File(fileString);
							
								try {
									fileInput = new FileInputStream(file);
						 
									System.out.println("Total file size to read (in bytes) : "+ fileInput.available());
									
									message = "HTTP/1.1 200 OK\n" +
											  "Server: Test Server\n" +
											  "Content-Length: " + fileInput.available() + "\n" +
											  "Content-Type: text/html\n\r\n";
						 
									int content;
									while ((content = fileInput.read()) != -1) {
									
										message += (char) content;
									}
									System.out.println(message);
						 
								} catch (IOException e) {
									System.err.println("File not found"+ e.getMessage());
									errorMessage(connectSocket);
									
								} finally {
									try {
										if (fileInput != null){
											fileInput.close();
										}
									} catch (IOException ex) {
										ex.printStackTrace();
										
									}
									if(!message.equals("")){
										java.io.OutputStream out = connectSocket.getOutputStream();
										out.write( message.getBytes());
										System.out.println("Send Message");
										serverInterface.addMessageToList("Sent Message");
									}
								}
						}catch(IOException e){
							System.out.println("SendMessage exception"+e.getMessage());
							close();
					}
				}			
			}
				
			}catch(IOException e){
				errorMessage(connectSocket);
				close();
				
			}
			
			try{
				connectSocket.close();
				System.out.println("Server Close");
				serverInterface.addMessageToList("Client disconnected");
				close();
			}catch(IOException e){
				System.out.println("CloseSocket exception"+ e.getMessage());
			}
		}
	}
	
	public void close(){
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void errorMessage(Socket connectSocket){
		//**METTRE MESSAGE D'ERREUR !!!!!!!! *
		String errorMessage = "HTTP/1.1 200 OK\n" +
				  				"Content-Length: " + "" +
				  				"Content-Type: text/html\n\r\n"+
				  				" <!DOCTYPE html> <html> <body><h1 color='#9000A1'>HTTP 404<h1></body> </html>" ;
		java.io.OutputStream out;
		try {
			out = connectSocket.getOutputStream();
			out.write( errorMessage.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
