import java.rmi.RemoteException;

public class CallBack implements CallBackInterface {

    private GameClient thisClient;
    
    //Constructor
    public CallBack(Object cliente) throws RemoteException {
        thisClient = (GameClient) cliente;
    }
    
    // Muestra un mensaje desde el servidor
    public void notifica(String mensaje) throws RemoteException {
        thisClient.setMessage(mensaje);
    }

   // El servidor notifica al cliente que tiene que tomar turno
    public void pasarTurno() throws RemoteException {  	thisClient.miTurno();  }

    // El servidor muestra la jugada de todos los jugadores
    public void muestraJugada(byte player1Pick, byte player2Pick, byte player3Pick) throws RemoteException {
	thisClient.muestraJugada(player1Pick, player2Pick, player3Pick);
    }
    
}
