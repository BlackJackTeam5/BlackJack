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
import java.util.List;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 




public class ClientGUI extends JFrame {
	// private Serializable
	private JPanel contentPane;
	private static ClientGUI frame;
	private Player myPlayer;
	private ArrayList<Player> playerList;
	
	
	private Socket socket;
	
	private static ObjectOutputStream objectOutput;
	private static ObjectInputStream objectInput;
	
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		//setup socket connection here.
		
		// InetAddress ip = InetAddress.getLocalHost();
		// socket = new Socket(ip.getHostName(), 6667);
		// objectOutput = new ObjectOutputStream(socket.getOutputStream());
		// objectInput = new ObjectInputStream(socket.getInputStream());
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
				myPlayer.setCommand("login");
				//InetAddress ip = InetAddress.getLocalHost();
				// socket = new Socket("localhost", 6667);
				// objectOutput = new ObjectOutputStream(socket.getOutputStream());
				// objectInput = new ObjectInputStream(socket.getInputStream());
				try {
					socket = new Socket("localhost", 6667);
					objectOutput = new ObjectOutputStream(socket.getOutputStream());
					objectInput = new ObjectInputStream(socket.getInputStream());
					objectOutput.writeObject(myPlayer);
					myPlayer = (Player)objectInput.readObject();
					System.out.println(myPlayer.getVerified());
		
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					try {
						System.out.println("closing socket");
						socket.close();
						//System.exit(0);
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					finally {
					
					}
				}
				

				if(myPlayer.getVerified()) {
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

		ActionListener refreshPanel = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Updating panels");
				try {
					socket = new Socket("localhost", 6667);
					objectOutput = new ObjectOutputStream(socket.getOutputStream());
					objectInput = new ObjectInputStream(socket.getInputStream());
					myPlayer.setCommand("updatelobby");
					objectOutput.writeObject(myPlayer);
					//myPlayer = (Player)objectInput.readObject();
					String text= "";
					playerList = (ArrayList<Player>)objectInput.readObject();
					for(int i =0 ; i< playerList.size();i++) {
						text+= playerList.get(i).getID() + " ";
					}
					playerTF.setText(text);
					
					//objectOutput.writeObject(playerList);
				}
				catch (ClassNotFoundException e1){
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					try {
						System.out.println("closing socket");
						socket.close();
						//System.exit(0);
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					finally {
					
					}

					contentPane.revalidate();
					contentPane.repaint();
				}
			}
		};
		new Timer(1000, refreshPanel).start();

		
		JButton startPlayerGame = new JButton("Play");
		
		startPlayerGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//let server handle work of creating blackjack
				myPlayer.setCommand("play");
				try {
					socket = new Socket("localhost", 6667);
					objectOutput = new ObjectOutputStream(socket.getOutputStream());
					objectInput = new ObjectInputStream(socket.getInputStream());
					objectOutput.writeObject(myPlayer);
					myPlayer = (Player)objectInput.readObject();
					playerList = (ArrayList<Player>)objectInput.readObject();
					System.out.println(playerList.size());
				}
				catch (IOException | ClassNotFoundException e1){
					e1.printStackTrace();
				}
				finally{
					try {
						System.out.println("closing socket");
						socket.close();
						//System.exit(0);
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					finally {
					
					}
				}
				
			
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
		ArrayList<JLabel>jNames = new ArrayList<JLabel>();
		
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new BorderLayout(0,2));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0,4));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0,3));
		
		JLabel p1 = new JLabel("player1");
		JLabel p2 = new JLabel("player2");
		JLabel p3 = new JLabel("player3");
		JLabel p4 = new JLabel("player4");
		JLabel p5 = new JLabel("player5");
		JLabel p6 = new JLabel("player6");

		jNames.add(p1);
		jNames.add(p2);
		jNames.add(p3);
		jNames.add(p4);
		jNames.add(p5);
		jNames.add(p6);

		//JLabel l3 = new Jlabel("");
		topPanel.add(p1);
		topPanel.add(p2);
		topPanel.add(p3);
		topPanel.add(p4);
		topPanel.add(p5);
		topPanel.add(p6);

		JButton dealButton = new JButton("Deal");
		JButton stayButton = new JButton("Stay");
		JButton foldButton = new JButton("Fold");
		bottomPanel.add(dealButton);
		bottomPanel.add(stayButton);
		bottomPanel.add(foldButton);

		ActionListener refreshPanel = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					myPlayer.setCommand("UpdateGame");
					socket = new Socket("localhost", 6667);
					objectOutput = new ObjectOutputStream(socket.getOutputStream());
					objectInput = new ObjectInputStream(socket.getInputStream());
					objectOutput.writeObject(myPlayer);
					playerList = (ArrayList<Player>)objectInput.readObject();
					for (int i =0; i < playerList.size(); i++)
					{
						jNames.get(i).setText(playerList.get(i).getID()+": "+Integer.toString(playerList.get(i).getHand().calcTotal())
				+ " | " + playerList.get(i).getCommand());

					}
				}
				catch (ClassNotFoundException e1){
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					try {
						socket.close();
						//System.exit(0);
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					finally {
						contentPane.revalidate();
						contentPane.repaint();
					}
					
				}
			}
		
		};

		// TIMER IS HERE 
		new Timer(2000, refreshPanel).start();




		dealButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// myPlayer.setCommand("Deal");
				myPlayer.setCommand("deal");
				System.out.println("set DEAL");
				try {
					objectOutput.writeObject(myPlayer);
					myPlayer = (Player)objectInput.readObject();
					System.out.println(myPlayer.getHand().hand.size());
		
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					myPlayer.setCommand("deal");
					socket = new Socket("localhost", 6667);
					objectOutput = new ObjectOutputStream(socket.getOutputStream());
					objectInput = new ObjectInputStream(socket.getInputStream());
					objectOutput.writeObject(myPlayer);
					myPlayer = (Player)objectInput.readObject();
					System.out.println("NEW TOTAL = " + myPlayer.getHand().calcTotal());;
		
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					try {
						System.out.println("closing socket");
						socket.close();
						//System.exit(0);
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					finally {
					
					}
				}
				
				// try {
				// 	sendCommand("deal");
				// }
				// catch (IOException | ClassNotFoundException e1){
				// 	e1.printStackTrace();
				// }
				//System.out.println(myPlayer.getHand().hand.size());
				//  l1.setText(myPlayer.getID()+": "+Integer.toString(myPlayer.getHand().calcTotal())
				// + " | " + myPlayer.getCommand());

	
				
				
				//send to server 
				//wait for update??
				
			}
			
		});
		
		stayButton.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myPlayer.setCommand("Stay");
				try {
					socket = new Socket("localhost", 6667);
					objectOutput = new ObjectOutputStream(socket.getOutputStream());
					objectInput = new ObjectInputStream(socket.getInputStream());
					objectOutput.writeObject(myPlayer);
					myPlayer = (Player)objectInput.readObject();
					System.out.println(myPlayer.getCommand());
		
				} catch (IOException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally{
					try {
						System.out.println("closing socket");
						socket.close();
						//System.exit(0);
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					finally {
					
					}
				}
				// l1.setText(myPlayer.getID()+": "+Integer.toString(myPlayer.getHand().calcTotal())
				// + " | " + myPlayer.getCommand());

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
	
	// public void sendCommand(String command) throws ClassNotFoundException, IOException{
	// 	try {
	// 		myPlayer.setCommand(command);
	// 		System.out.println(myPlayer.getCommand());
	// 		objectOutput.writeObject(myPlayer);
	// 		// playerList = new ArrayList<Player>();
	// 		//List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
	// 		//Player temp = new Player();
	// 		//myPlayer = null;
	// 		if (command.equalsIgnoreCase("play")){
	// 			myPlayer = (Player)objectInput.readObject();
	// 			playerList = (ArrayList<Player>)objectInput.readObject();
	// 		}

	// 		if (command.equalsIgnoreCase("deal")){
	// 			Player newPlayer = (Player)objectInput.readObject();
	// 			//System.out.println("NEW HAND SIZE = " + );
	// 			myPlayer = newPlayer;
				
	// 		}
			
			
			
	// 		//System.out.println(playerList.size()); // to test

	// 		//myPlayer = (Player)objectInput.readObject();
	// 	} catch (Exception e2) {
	// 		// TODO Auto-generated catch block
	// 		e2.printStackTrace();
	// 	}
	// }
}