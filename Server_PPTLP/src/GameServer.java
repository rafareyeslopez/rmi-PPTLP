import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GameServer {

	private int port = 1099;
	private String stringRMI = "PPTLP";
	private Registry registry;

	public GameServer() {

		try {
			System.setProperty("java.security.policy", "file:./security.policy");
			System.setProperty("java.rmi.server.codebase",
					"file:/C:\\Users\\rafar\\eclipse-workspace\\rmi\\Server_PPTLP\\bin\\");
			startRegistry();
			// Incluye el gestor de seguridad
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			// crea la instancia de juego
			GameInterface game = new Game();
			// crea el stub
			GameInterface stub = (GameInterface) UnicastRemoteObject.exportObject(game, 0);
			// se registra stub en el servidor RMI
			registry.rebind(stringRMI, stub); // para registrarlo o sustituirlo
			// Muestra un mensaje positivo y muestra nombre registros actuales
			System.out.println("Servidor " + stringRMI + " preparado.");
		} catch (RemoteException ex) {
			ex.printStackTrace();
			System.exit(0);
		}

	}

	// Este metodo inicia un registro RMI en el "host" local, si
	// todavia no existe en el numero de puerto especificado.
	private void startRegistry() throws RemoteException {

		try {
			registry = LocateRegistry.getRegistry(port);
			// Esta llamada lanza una excepcion si el registro no existe todavia
			registry.list();
		} catch (RemoteException e) {
			// No hay ningun registro valido en este puerto
			System.out.println("No hay registro en el puerto " + port);
			registry = LocateRegistry.createRegistry(port);
			System.out.println("Registro RMI en el puerto " + port);
		}
	}

	public static void main(String[] args) {
		new GameServer();
	}

}
