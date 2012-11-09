package Partie2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

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
		
			String read = "";
			try {
				connectSocket = server.accept();
				System.out.println("Connect Socket");
				serverInterface.addMessageToList("Client connected");
				
				java.io.InputStream in =  connectSocket.getInputStream();
				//in.read(buff);
				
				InputStreamReader is = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(is);
				read += br.readLine() + "\n";
				//read += br.readLine()+ "\n";
				//read += br.readLine()+ "\n";
				//read += br.readLine()+ "\n";
				//read += br.readLine()+ "\n";

				//https://localhost:1500/test.html
				
				
				//????? get garbage
				System.out.println(read);
				//System.out.println(new String(read.getBytes()));
				//http://www.codemiles.com/finished-projects/http-server-in-java-t1178.html
				
				
				serverInterface.addMessageToList("Recieved message");
				
			}catch(IOException e){
				System.out.println("Socket exception: "+e.getMessage());
			}
			
			String message = "Hello from server!";
			
			//For file input stream
			//http://www.mkyong.com/java/how-to-read-file-in-java-fileinputstream/
			try {
				
				java.io.FileInputStream fileInput = null;
				File file = new File("C:/test.html");
				
				
				
				
				try {
					fileInput = new FileInputStream(file);
		 
					System.out.println("Total file size to read (in bytes) : "+ fileInput.available());
					
					message = "HTTP/1.1 200 OK\n" +
							  "Server: Test Server\n" +
							  "Content-Length: " + fileInput.available() + "\n" +
							  "Content-Type: text/html\n\r\n";
		 
					int content;
					while ((content = fileInput.read()) != -1) {
						// convert to char and display it
						//System.out.print((char) content);
						message += (char) content;
					}
					System.out.println(message);
		 
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fileInput != null)
							fileInput.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				
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
