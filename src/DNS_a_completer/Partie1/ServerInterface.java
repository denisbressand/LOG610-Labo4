package Partie1;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerInterface extends JFrame {

	private Serveur serveur = new Serveur();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServerInterface inter = new ServerInterface();
		inter.show();
	}
	
	
	
	public ServerInterface()
	{
		setBounds(100, 100, 459, 258);
		
		setTitle("Server");
		
		getContentPane().setLayout(null);
		
		JButton btnActivateServer = new JButton("Activate Server");
		btnActivateServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Activate Server code goes here
				serveur.connectServeur(4444);
			}
		});
		btnActivateServer.setBounds(71, 93, 121, 23);
		getContentPane().add(btnActivateServer);
		
		JButton btnSendMessage = new JButton("Send Message");
		btnSendMessage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Send Message code goes here
				serveur.sendMessage("Hello from server!");
			}
		});
		btnSendMessage.setBounds(71, 148, 121, 23);
		getContentPane().add(btnSendMessage);
		
		
		
	}

}
