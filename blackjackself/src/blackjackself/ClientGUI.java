package blackjackself;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame {
	
	private JPanel contentPane;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//setup socket connection here.

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		setContentPane(contentPane);
		
		setUpLogin();
	}
	
	public void setUpLogin() {
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(3,2));
		JLabel loginLabel = new JLabel("Username: ");
		JTextField loginField = new JTextField();
		JLabel passLabel = new JLabel("Password: ");
		JTextField passField = new JTextField();
		
		loginPanel.add(loginLabel);
		loginPanel.add(loginField);
		loginPanel.add(passLabel);
		loginPanel.add(passField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//send message to server to get authenticated.
				player player1 = new player(loginField.getText(), passField.getText());
					
				//flip to gameboard GUI
				if(player1.verified) {
					
				}
			}
		});
		
		
		JButton newUserButton = new JButton("New User");
		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(loginPanel);
				setUpUser();
			}
		});
		
		loginPanel.add(loginButton);
		loginPanel.add(newUserButton);
		
		contentPane.add(loginPanel);
	}
	
	public void setUpUser() {
		JPanel newUserPanel = new JPanel();
		newUserPanel.setLayout(new GridLayout(3,2));
		JLabel userLabel = new JLabel("Username");
		JTextField userTF = new JTextField();
		
		JLabel passLabel = new JLabel("Password: ");
		JTextField passTF = new JTextField();
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player player1 = new player(userTF.getText(), passTF.getText());
				
				//send player over to server 
				
				
				//flip to game board GUI
			}
		});
		
		newUserPanel.add(userLabel);
		newUserPanel.add(userTF);
		newUserPanel.add(passLabel);
		newUserPanel.add(passTF);
		newUserPanel.add(registerButton);
		
		contentPane.add(newUserPanel);
		
	}
	
	public void deal() {
		/*
		Player.setcommand("deal");
		socket.sned(Player);
		
		if(player.canContinue) {
			updateBust(); //display you bust message
		}
		else {
			updateClientGUI(); //can hit or stay.
		}
		updateClientGUI();
		*/
	}
	
	public void()
	
}
