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
import javax.swing.JTextField;

public class ClientInterface extends JFrame {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientInterface inter = new ClientInterface();
		inter.show();
	}
	private Client client = new Client();
	private JTextField txtF_Message;
	
	
	public ClientInterface() {
		
		setBounds(100, 100, 459, 258);
		
		setTitle("Client");
		
		getContentPane().setLayout(null);
		
		JButton btnConnect = new JButton("Connect and Send");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				client.go("localhost", 4444, txtF_Message.getText());
				
			}
		});
		btnConnect.setBounds(65, 106, 197, 23);
		getContentPane().add(btnConnect);
		
		txtF_Message = new JTextField();
		txtF_Message.setBounds(65, 143, 197, 20);
		getContentPane().add(txtF_Message);
		txtF_Message.setColumns(10);
		
		
	}
}
