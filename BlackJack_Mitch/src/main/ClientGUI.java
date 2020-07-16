package main;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientGUI extends JFrame {
	
	private JPanel contentPane;
	private static ClientGUI frame;
	private Player myPlayer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//setup socket connection here.

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ClientGUI();
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
		setBounds(100, 100, 1000, 600);
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
				
				System.out.println(loginField.getText());
				System.out.println(passField.getText());
				
				myPlayer = new Player(loginField.getText(), passField.getText());
			
				if(true) {
					//set to gameboard
					setUpLobby();
				}
				else {
					JOptionPane pane = new JOptionPane();
					pane.showMessageDialog(frame, "Invalid password");
				}
			}
		});
		
		
		JButton newUserButton = new JButton("New User");
		newUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				myPlayer = new Player(userTF.getText(), passTF.getText());
				//send player over to server 

				//flip to game board GUI
				setUpLobby();
			}
		});
		
		newUserPanel.add(userLabel);
		newUserPanel.add(userTF);
		newUserPanel.add(passLabel);
		newUserPanel.add(passTF);
		newUserPanel.add(registerButton);
		
		frame.contentPane.removeAll();
		contentPane.invalidate();
		contentPane.add(newUserPanel);
		
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void setUpLobby() {
		JPanel lobbyPanel = new JPanel();
		lobbyPanel.setLayout(new GridLayout(2,0));
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(0,2));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,2));
		
		JLabel playerLabel = new JLabel("Other Players: ");
		JTextField playerTF = new JTextField();
		playerTF.setEditable(false);
		
		JButton startAIGame = new JButton("New Game Against AI");
		JButton startPlayerGame = new JButton("New Game Against Players");
		startAIGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//let server handle work of creating blackjack
				setUpGame();
			}
		});
		startPlayerGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//let server handle work of creating blackjack
				setUpGame();
			}
		});
		
		playerPanel.add(playerLabel);
		playerPanel.add(playerTF);
		
		buttonPanel.add(startAIGame);
		buttonPanel.add(startPlayerGame);
		
		lobbyPanel.add(playerPanel);
		lobbyPanel.add(buttonPanel);
		
		frame.contentPane.removeAll();
		contentPane.invalidate();
		contentPane.add(lobbyPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void setUpGame() {
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout(0,2));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0,4));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0,3));
		
		JButton dealButton = new JButton("Deal");
		JButton stayButton = new JButton("Stay");
		JButton foldButton = new JButton("Fold");
		bottomPanel.add(dealButton);
		bottomPanel.add(stayButton);
		bottomPanel.add(foldButton);
		dealButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myPlayer.setCommand("Deal");
				
				//send to server 
				//wait for update??
				
			}
			
		});
		
		stayButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myPlayer.setCommand("Stay");
			}
			
		});
		
		foldButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myPlayer.setCommand("Fold");
			}
			
		});
	
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout());
		
		gamePanel.add(BorderLayout.NORTH, topPanel);
		gamePanel.add(BorderLayout.SOUTH, bottomPanel);
		
		frame.contentPane.removeAll();
		contentPane.invalidate();
		contentPane.add(gamePanel);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
}
