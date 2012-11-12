package Partie2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JMenu;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

//Java get ip code
//http://www.roseindia.net/java/java-get-example/get-ip-address.shtml

//Java date and time code
//http://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/



public class ServerInterface extends JFrame{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerInterface server = new ServerInterface();
		server.show();
	}
	
	private List list_LastAction;
	private JButton btnStartServer;
	private JButton btnStopServer;
	
	//For timestamp
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar cal;
	private JLabel lblPort;
	private JTextField txtF_Port;
	
	
	private Integer port;
	private ServerInterface me;
	private ServerSocket server;
	private Socket connectSocket = null; 
	private ServerWaitingThread serverWaitingThread;
	
	public ServerInterface() {
		
		me = this;
		
		setBounds(100, 100, 459, 258);
		
		setTitle("Serveur : Partie 2 : Serveur HTTP");
		getContentPane().setLayout(null);
		
		JLabel lblServerIp = new JLabel("Server ip :");
		lblServerIp.setBounds(34, 43, 57, 14);
		getContentPane().add(lblServerIp);
		
		JTextField txtF_ServerIp = new JTextField();
		txtF_ServerIp.setEditable(false);
		txtF_ServerIp.setBounds(91, 40, 86, 20);
		getContentPane().add(txtF_ServerIp);
		txtF_ServerIp.setColumns(10);
		
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Server start code goes here
				port = Integer.parseInt(txtF_Port.getText());
				cal = Calendar.getInstance();
				list_LastAction.add(dateFormat.format(cal.getTime()) + ": Server started on port : " + port.toString());
				btnStartServer.setEnabled(false);
				btnStopServer.setEnabled(true);
				txtF_Port.setEnabled(false);
				
				try{
					server = new ServerSocket(port);
					System.out.println("Server connect");
				}catch( IOException e){
					System.out.println("ServerSocket exception "+e.getMessage());
				}
				
				serverWaitingThread = new ServerWaitingThread(server, me);
				serverWaitingThread.start();
			

				list_LastAction.select(list_LastAction.getItemCount() - 1);
			}
		});
		btnStartServer.setBounds(34, 84, 103, 23);
		getContentPane().add(btnStartServer);
		
		btnStopServer = new JButton("Stop Server");
		btnStopServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Server stop code goes here
				cal = Calendar.getInstance();
				list_LastAction.add(dateFormat.format(cal.getTime()) + ": Server stoped.");
				btnStartServer.setEnabled(true);
				btnStopServer.setEnabled(false);
				txtF_Port.setEnabled(true);
				try {
					server.close();
				} catch (IOException e1) {
					System.out.println("Server Close"+e1.getMessage());
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				list_LastAction.select(list_LastAction.getItemCount() - 1);
			}
		});
		btnStopServer.setBounds(301, 84, 103, 23);
		btnStopServer.setEnabled(false);
		getContentPane().add(btnStopServer);
		
		list_LastAction = new List();
		list_LastAction.setBounds(10, 126, 423, 84);
		getContentPane().add(list_LastAction);
		
		txtF_Port = new JTextField();
		txtF_Port.setText("1500");
		txtF_Port.setBounds(287, 40, 57, 20);
		getContentPane().add(txtF_Port);
		txtF_Port.setColumns(10);
		
		lblPort = new JLabel("Port:");
		lblPort.setBounds(241, 43, 31, 14);
		getContentPane().add(lblPort);
		
		//Find and show IP
		try 
		{
			InetAddress thisIp =InetAddress.getLocalHost();
			System.out.println("IP:"+thisIp.getHostAddress());
			txtF_ServerIp.setText(thisIp.getHostAddress());
			
			cal = Calendar.getInstance();
			list_LastAction.add(dateFormat.format(cal.getTime()) + ": Found ip.");
			cal = Calendar.getInstance();
			list_LastAction.add(dateFormat.format(cal.getTime()) + ": Ready.");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			txtF_ServerIp.setText("No ip!");
			cal = Calendar.getInstance();
			list_LastAction.add(dateFormat.format(cal.getTime()) + ": No ip found, please fix network issues.");
		}
		
		list_LastAction.select(list_LastAction.getItemCount() - 1);
			
	}
	
	//To be called by thread
	public void addMessageToList(String message)
	{
		cal = Calendar.getInstance();
		list_LastAction.add(dateFormat.format(cal.getTime()) + ": " + message);
		
		list_LastAction.select(list_LastAction.getItemCount() - 1);
	}
	
	private static class ServerWaitingThread extends Thread{
	  
		private Socket connectSocket; 
		private ServerSocket server;
		private ServerInterface me;
		private ServerThread serverThread;
		public ServerWaitingThread(ServerSocket server, ServerInterface me){
			this.server = server;
			this.me = me;
			
		}
		public void run()
		{
			while(this.isAlive()){
				
				try {
					if(!server.isClosed()){
						connectSocket = server.accept();
						serverThread = new ServerThread(me, server, connectSocket);
						serverThread.start();
					}else{
						this.finalize();
					}
				} catch (IOException e) {
					System.out.println("ServerThread exception"+ e.getMessage());
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				
			}
		}
	}
	
}
