import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CallBackInterface extends Remote {

	//El servidor notifica al cliente que tiene que tomar turno
    public void pasarTurno() throws RemoteException;
    
    //El servidor envia mensajes para ser mostrado por el cliente
    public void notifica(String mensaje) throws RemoteException;
    
    //El servidor marca el movimiento del otro jugador
    public void muestraJugada (byte player1Pick, byte player2Pick, byte player3Pick) throws RemoteException;
    
}
