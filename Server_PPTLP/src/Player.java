
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcelino Cabrera
 */
public class Player {
    
    private String name, pass, nickname;
    private int point = 0, ranking = 0;
    private byte election = -1;
    private CallBackInterface client;

    public CallBackInterface getClient() {return client;}
    public void setClient(CallBackInterface client) {this.client = client;}

    public byte getElection() {return election;}
    public void setElection(byte election) {this.election = election;}
    
    public String getName() {return name; }
    public void setName(String name) {this.name = name;}

    public String getPass() {return pass;}
    public void setPass(String pass) {this.pass = pass;}

    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}

    public int getPoint() {return point;}
    public void setPoint(int point) {this.point = point;}

    public int getRanking() {return ranking;}
    public void setRanking(int ranking) {this.ranking = ranking;}
    
    Player () {
        
    }

    public void notifica(String mensaje) throws RemoteException {
	client.notifica(mensaje);
    }

    public void pasarTurno() throws RemoteException { 
	client.pasarTurno(); 
    }

    public void muestraJugada(byte p1, byte p2, byte p3) throws RemoteException {
	client.muestraJugada(p1, p2, p3); 
    }

}
