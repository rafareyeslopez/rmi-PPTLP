
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {

    static int portNum = 1099;
   

    public static void main(String args[]) {
        new Cliente();
    }

    public Cliente() {
        Scanner entrada_teclado = new Scanner(System.in);

        try {
            String registroURL = "rmi://localhost:" + portNum + "/servidor";
            InterfazSaludar hello = (InterfazSaludar) Naming.lookup(registroURL);
            // invocar ahora el método remoto
            System.out.println(hello.saludar("Sergio"));
            //hello.sumar(5,6);
            int operacion;
            do{
                
            System.out.println("SELECCIONA LA OPCIÓN QUE DESEES: ");
            System.out.println("Sumar (1), Restar (2), Multiplicar (3), Dividir (4) TERMINAR (0)---> ");
            operacion = entrada_teclado.nextInt();
               
            switch (operacion) {
                case 1: //SUMA
                    System.out.println("Introduce el primer numero--> ");
                    double num1_suma = entrada_teclado.nextDouble();
                    
                    System.out.println("Introduce el segundo numero--> ");
                    double num2_suma = entrada_teclado.nextDouble();
                    
                    double suma = hello.add(num1_suma, num2_suma);
                    
                    System.out.println("El resultado de la suma es--> " + suma);
                    
                    break;
                case 2: //RESTA
                    System.out.println("Introduce el primer numero--> ");
                    double num1_resta = entrada_teclado.nextDouble();
                    
                    System.out.println("Introduce el segundo numero--> ");
                    double num2_resta = entrada_teclado.nextDouble();
                    
                    double resta = hello.sub(num1_resta, num2_resta);
                    
                    System.out.println("El resultado de la resta es--> " + resta);
                    
                    break;
                case 3: //MULTIPLICACION
                    System.out.println("Introduce el primer numero--> ");
                    double num1_mult = entrada_teclado.nextDouble();
                    
                    System.out.println("Introduce el segundo numero--> ");
                    double num2_mult = entrada_teclado.nextDouble();
                    
                    double multiplicacion = hello.mul(num1_mult, num2_mult);
                    
                    System.out.println("El resultado de la multiplicacion es--> " + multiplicacion);
                    
                    break;
                case 4: //DIVISION
                    System.out.println("Introduce el primer numero--> ");
                    double num1_div = entrada_teclado.nextDouble();
                    
                    System.out.println("Introduce el segundo numero--> ");
                    double num2_div = entrada_teclado.nextDouble();
                    
                    double division = hello.div(num1_div, num2_div);
                    
                    System.out.println("El resultado de la division es--> " + division);
                    
                    break;
                
            }
           }while(operacion!=0);
            

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}


            

            