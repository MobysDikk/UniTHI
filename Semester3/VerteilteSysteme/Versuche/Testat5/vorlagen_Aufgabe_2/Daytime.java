import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Daytime extends Remote{
    
    String getTime(String s) throws RemoteException;

}
 //Schnittstelle in der mittels dem interface erlaubt wird
// auf entfernte Methoden zuzugreifen. Der Client sieht bei der RMI-Registry unter 
//diesem Namen nach und bekommt eine Objektreferenz, die seinem Remote Interface entsprechen muss.