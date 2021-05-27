import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {

    //metodo remoto para conectar al juego
    public String conectar(CallBackInterface client, String user, String pass) throws RemoteException;
    
    //metodo remoto para conectar al juego
    public void desconectar(byte playerID) throws RemoteException;

    //el cliente informa el movimiento al servidor
    public void miMovimiento(byte picked, String playerID) throws RemoteException;

   
}
