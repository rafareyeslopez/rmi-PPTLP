// Archivo Servidor.java
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Servidor {
	
	public Servidor () {
		//Incluye el gestor de seguridad
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());  }	  
		try {
                    Registry registry= LocateRegistry.getRegistry(1099); 
                    registry.list( );  //lanza una excepcion si no existe el servidor 
                } 
                catch (RemoteException ex) { 
                    // No hay un registro valido en dicho puerto 
                    System.out.println("No hay un registro RMI valido en puerto " + 1099);
                    try{
                        Registry registry= LocateRegistry.createRegistry(1099);
                    }
                    catch (RemoteException ex1){
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    System.out.println("Registro RMI creado en el puerto " + 1099);
                }
                try{
			// se registra el objeto como stub
			InterfazSaludar objExportado = (InterfazSaludar) new ImplSaludar();
			//se crea el stub dinamicamente y se asocia al puerto 0
			InterfazSaludar stub = (InterfazSaludar) UnicastRemoteObject.exportObject(objExportado,0);
			//se registra stub en el servidor RMI
			Registry registry = LocateRegistry.getRegistry(); // Puerto por defecto 1099
			//para registrarlo o sustituirlo
			registry.rebind("servidor", stub);
		} catch (RemoteException e) {
                    e.printStackTrace(); 
                    System.exit(0);
                }
		System.out.println("Servidor llamado *servidor* preparado.");
	}
	public static void main(String args[]) {
		new Servidor();
	}
} 

