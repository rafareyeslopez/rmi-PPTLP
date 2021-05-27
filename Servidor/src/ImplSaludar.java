// Archivo ImplSaludar.java

import java.rmi.RemoteException;

public class ImplSaludar implements InterfazSaludar {

	public ImplSaludar() throws RemoteException { }  

	public String saludar(String nombre) throws RemoteException {
		String szMensaje = "¡Hola " +  nombre + "!";
		try {
			Thread.sleep(5000); // Produce un retraso de 5"
		} catch (InterruptedException e) {
			System.out.println("Error en ImplSaludar: " + e);
		} 
		return szMensaje;
	}
        
        public double add (double param1, double param2){
            double resultado_suma;
            resultado_suma = param1 + param2;
            
            return resultado_suma; //Hay que devolver la suma de x más y.
        }

    public double sub(double param1, double param2) throws RemoteException {
        double resultado_resta;
        resultado_resta = param1 - param2;
        
        return resultado_resta;
    }

    public double mul(double param1, double param2) throws RemoteException {
        double resultado_mult;
        resultado_mult = param1 * param2;
        
        return resultado_mult;
    }

    public double div(double param1, double param2) throws RemoteException {
        double resultado_div;
        resultado_div = param1/param2;
        
        return resultado_div;
        
    }

}
