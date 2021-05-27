import java.rmi.RemoteException;

public class Game implements GameInterface {

	// Inicializa los jugadores
	private Player[] player;
	static final String[][] registered = { { "Pepe", "pepe123", "Pepote" }, { "Juan", "juan123", "Juanote" },
			{ "Luis", "luis123", "Luisote" }, { "Angel", "angel123", "Angelote" },
			{ "Miguel", "miguel123", "Miguelote" }, { "rafa", "rafa", "rafa" }, { "Paco", "paco123", "Pacote" } };
	private byte nPlayers = 0;
	private byte nPlayer = 0;

	public Game() throws RemoteException {
		player = new Player[3];
		player[0] = null;
		player[1] = null;
		player[2] = null;
	}

	// conecta el cliente al servidor
	@Override
	public String conectar(CallBackInterface client, String user, String pass) throws RemoteException {

		String nickname = "";
		// Ya esta completo el cupo de jugadores
		if (nPlayers >= 3) { // Jugador 1 = 0 -- Jugador 2 = 1 -- Jugador 3 = 2
			client.notifica("No se admiten mas jugadores");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		} else { // Si faltan jugadores se registra el jugador
			if (checkUser(user, pass)) {
				player[nPlayers] = new Player();
				player[nPlayers].setName(registered[nPlayer][0]);
				player[nPlayers].setPass(registered[nPlayer][1]);
				player[nPlayers].setNickname(registered[nPlayer][2]);
				player[nPlayers].setClient(client);
				player[nPlayers].notifica("Espera al resto de jugadores");
				nickname = player[nPlayers].getNickname();
				System.out.println(nickname + " Conectado");
				nPlayers++;
			} else {
				client.notifica("Usuario no registrado");
			}
		}
		if (nPlayers == 3) {
			for (Player element : player) {
				element.notifica("Elige opción");
				element.pasarTurno();
			}
		}

		return nickname;
	}

	private boolean checkUser(String user, String pass) {

		boolean correct = false;
		for (byte i = 0; i < registered.length; i++) {
			if (user.equals(registered[i][0]) && pass.equals(registered[i][1])) {
				correct = true;
				nPlayer = i;
				break;
			}
		}
		return correct;
	}

	// metodo remoto para desconectar del servidor
	@Override
	public void desconectar(byte nplayer) throws RemoteException {

	}

	@Override
	public void miMovimiento(byte picked, String playerID) throws RemoteException {

		for (Player element : player) {
			if (element.getNickname().equals(playerID)) {
				element.setElection(picked);
				break;
			}
		}
		// Comprobar que todos han elegido
		if (completeGame()) {
			for (int i = 0; i < player.length; i++) {
				player[i].notifica("El ganador ha sido " + checkWinner());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				resetGame(i);
			}
		}

	}

	private boolean completeGame() throws RemoteException {

		boolean complete = true;
		for (Player element : player) {
			if (element.getElection() == -1) {
				complete = false;
			}
		}
		if (complete) {
			for (Player element : player) {
				element.muestraJugada(player[0].getElection(), player[1].getElection(), player[2].getElection());
			}

		}
		return complete;
	}

	private String checkWinner() {

		String winner = "ganador";
		return winner;
	}

	private void resetGame(int i) {
		player[i].setElection((byte) -1);
		try {
			player[i].notifica("Elige opción");
			player[i].pasarTurno();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
}
