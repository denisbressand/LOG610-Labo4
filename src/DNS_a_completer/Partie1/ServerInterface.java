package Partie1;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class ServerInterface extends JFrame {

	private Serveur serveur = new Serveur();
	
	private JLabel lbl_Message;
	
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
		
		lbl_Message = new JLabel("");
		lbl_Message.setBounds(71, 165, 163, 14);
		getContentPane().add(lbl_Message);
		
		JButton btnActivateServer = new JButton("Activate Server");
		btnActivateServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lbl_Message.setText(serveur.go(4444));
			}
		});
		btnActivateServer.setBounds(71, 93, 212, 23);
		getContentPane().add(btnActivateServer);	
		
		
		
	}
}
