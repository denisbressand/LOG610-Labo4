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
				serveur.go(4444);
			}
		});
		btnActivateServer.setBounds(71, 93, 121, 23);
		getContentPane().add(btnActivateServer);	
		
	}

}
