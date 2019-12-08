import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;

public class DaytimeImpl extends UnicastRemoteObject implements Daytime{

    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG);
    
    public DaytimeImpl () throws RemoteException {
        
    }

    @Override
    public String getTime(String s) throws RemoteException {
        
        return "Received time is " + df;
    }
    
    
     

}
