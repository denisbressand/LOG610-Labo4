package Partie1;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientInterface extends JFrame {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientInterface inter = new ClientInterface();
		inter.show();
	}
	
	private JLabel lblmessage;
	private Client client = new Client();
	
	
	public ClientInterface() {
		
		setBounds(100, 100, 459, 258);
		
		setTitle("Client");
		
		getContentPane().setLayout(null);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblmessage.setText(client.go("localhost", 4444));
			}
		});
		btnConnect.setBounds(65, 106, 136, 23);
		getContentPane().add(btnConnect);
		
		lblmessage = new JLabel("(message)");
		lblmessage.setBounds(65, 174, 107, 14);
		getContentPane().add(lblmessage);
		
		
	}
}
