package Aufgabe6;
import java.rmi.*;

public interface Echo extends Remote{
	public String getEcho(String input) throws RemoteException;
}
