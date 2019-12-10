import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.util.Date;

public class DaytimeImpl extends UnicastRemoteObject implements Daytime{

    
    public DaytimeImpl () throws RemoteException {
        
    }

    
    public String getTime(String s) throws RemoteException {
        Date date2 = new Date();
        System.out.println(s + " recieved at "+ date2.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }
    
    
     

}
