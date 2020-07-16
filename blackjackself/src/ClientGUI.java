

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
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
	private ArrayList<Player> playerList;
	
	private static Socket socket;
	
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		//setup socket connection here.
		
		InetAddress ip = InetAddress.getLocalHost();
		socket = new Socket(ip.getHostName(), 6667);
		objectOutput = new ObjectOutputStream(socket.getOutputStream());
		objectInput = new ObjectInputStream(socket.getInputStream());
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
	 * @throws IOException 
	 */
	public ClientGUI() throws IOException {
		//System.out.println("In GUI");
		
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
			public void actionPerformed(ActionEvent e){
				//send message to server to get authenticated.
				
				System.out.println(loginField.getText());
				System.out.println(passField.getText());
				
				myPlayer = new Player(loginField.getText(), passField.getText());
				try {
					objectOutput.writeObject(myPlayer);
					myPlayer = (Player)objectInput.readObject();
					System.out.println(myPlayer.verified);
		
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				if(myPlayer.verified) {
					//set to gameboard
					try {
						setUpLobby();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
	
	public void setUpUser(){
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
				System.out.println(myPlayer.getID());
				System.out.println(myPlayer.getPass());
				//send player over to server 
				try {
					objectOutput.writeObject(myPlayer);
					myPlayer = (Player)objectInput.readObject();
				} catch (IOException | ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//flip to game board GUI
				
				try {
					setUpLobby();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	public void setUpLobby() throws ClassNotFoundException, IOException {
		JPanel lobbyPanel = new JPanel();
		lobbyPanel.setLayout(new GridLayout(2,0));
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(0,2));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0,2));
		
		JLabel playerLabel = new JLabel("Other Players: ");
		JTextField playerTF = new JTextField();
		playerTF.setEditable(false);
		
		JButton startPlayerGame = new JButton("Play");
		
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
		
		buttonPanel.add(startPlayerGame);
		
		lobbyPanel.add(playerPanel);
		lobbyPanel.add(buttonPanel);
		
		frame.contentPane.removeAll();
		contentPane.invalidate();
		contentPane.add(lobbyPanel);
		contentPane.revalidate();
		contentPane.repaint();
		
		/*
		while(true) {
			//playerList = (ArrayList<Player>)objectInput.readObject();
			updateLobbyGUI();
		}U*/
	}
	
	public void updateLobbyGUI() {
		
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
