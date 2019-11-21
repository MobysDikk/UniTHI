package Aufgabe6;
import java.rmi.*;

public interface ServerAgent extends Remote {
	Agent execute(Agent agent) throws RemoteException;
	
}