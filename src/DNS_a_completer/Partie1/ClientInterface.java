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
import javax.swing.SwingConstants;

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
	private JTextField txt_IP;
	private JTextField txt_Port;
	
	
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
				client.go(txt_IP.getText(), Integer.parseInt(txt_Port.getText()), txtF_Message.getText());
				
			}
		});
		btnConnect.setBounds(65, 106, 197, 23);
		getContentPane().add(btnConnect);
		
		txtF_Message = new JTextField();
		txtF_Message.setBounds(65, 143, 197, 20);
		getContentPane().add(txtF_Message);
		txtF_Message.setColumns(10);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIp.setBounds(38, 38, 46, 14);
		getContentPane().add(lblIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPort.setBounds(38, 63, 46, 14);
		getContentPane().add(lblPort);
		
		txt_IP = new JTextField();
		txt_IP.setBounds(94, 35, 168, 20);
		getContentPane().add(txt_IP);
		txt_IP.setColumns(10);
		
		txt_Port = new JTextField();
		txt_Port.setBounds(94, 60, 86, 20);
		getContentPane().add(txt_Port);
		txt_Port.setColumns(10);
		
		
	}
}
