import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Daytime extends Remote{
    
    String getTime(String s) throws RemoteException;

}
 //Schnittstelle in der mittels dem interface erlaubt wird
// auf entfernte Methoden zuzugreifen