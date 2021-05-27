
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class GameClient extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Player player;
	private String nPlayer;
	private boolean miTurno;
	private GameInterface game;
	private CallBackInterface callback;

	private JLabel playerName = new JLabel("Jugador ");
	private JLabel results = new JLabel("  ");
	String[] movimientos = { "rock", "paper", "scissors", "lizard", "spock" };

	// Construir el juego
	public GameClient() {

		player = new Player("     ", this);
		miTurno = false;

		// Muestra los dos tableros
		this.getContentPane().setLayout(new BorderLayout());
		this.setResizable(false);
		this.setTitle("Piedra-Papel-Tijeras-Lagarto-Profesor");
		playerName.setFont(new java.awt.Font("Dialog", 1, 18));
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(playerName, BorderLayout.NORTH);// Nombre
		this.getContentPane().add(player, BorderLayout.CENTER);
		results.setFont(new java.awt.Font("Dialog", 1, 18));
		results.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(results, BorderLayout.SOUTH);// Resultado
		pack();
		centerWindow();
		setVisible(true);
		// Conecta con el servidor y lanza el juego
		initRMI();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				/*
				 * try { game.desconectar(nPlayer); } catch (RemoteException ex) {
				 * }//ex.printStackTrace();
				 */
				System.out.println("Partida terminada");
				System.exit(0);

			}
		});

	}

	void setMessage(String msg) {

		System.out.println(msg);
		results.setText(msg);

	}

	void miTurno() {
		player.unBlock();
	}

	void muestraJugada(byte player1Pick, byte player2Pick, byte player3Pick) {

		// Icluir en el lugar que considereis la puntuacion obtenida por cada jugador
		setMessage("Jugador 1: " + movimientos[player1Pick] + " Jugador 2: " + movimientos[player2Pick] + " Jugador 3: "
				+ movimientos[player3Pick]);
	}

	private void initRMI() {

		String host = "localhost";
		int port = 1099;
		String stringRMI = "PPTLP";
		String url = "rmi://" + host + ":" + port + "/" + stringRMI;

		player.block();

		try {
			System.setProperty("java.security.policy", "file:./security.policy");
			// Incluye el gestor de seguridad
			System.setProperty("java.rmi.server.codebase",
					"file:/C:\\Users\\rafar\\eclipse-workspace\\rmi\\Server_PPTLP\\bin\\");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			game = (GameInterface) Naming.lookup(url);
			System.out.println("Servidor " + stringRMI + " encontrado");
			// Create callback for use by the server to control the client
			callback = (CallBackInterface) UnicastRemoteObject.exportObject(new CallBack(this), 0);
			String jugador = player.getData("Username");
			String pass = player.getData("Password");
			System.out.println(jugador + " - " + pass);
			nPlayer = game.conectar(callback, jugador, pass);
			if (nPlayer.equals("")) {
				System.exit(0);
			}
			System.out.println(nPlayer + " Conectado");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		// Marca el jugador que soy
		playerName.setText("JugadorID: " + nPlayer);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		// if (!miTurno) return; // Si no es mi turno espero...
		player.block();

		JButton eleccion = (JButton) event.getSource();
		byte movimiento = 0;
		if (eleccion.getActionCommand().equals(movimientos[0])) {
			movimiento = 0;
		} else if (eleccion.getActionCommand().equals(movimientos[1])) {
			movimiento = 1;
		} else if (eleccion.getActionCommand().equals(movimientos[2])) {
			movimiento = 2;
		} else if (eleccion.getActionCommand().equals(movimientos[3])) {
			movimiento = 3;
		} else if (eleccion.getActionCommand().equals(movimientos[4])) {
			movimiento = 4;
		}
		results.setText("Mi movimiento es " + movimientos[movimiento]);
		try {
			game.miMovimiento(movimiento, nPlayer);
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}

	}

	private void centerWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
	}

	// Metodo principal
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		new GameClient();
	}

}
