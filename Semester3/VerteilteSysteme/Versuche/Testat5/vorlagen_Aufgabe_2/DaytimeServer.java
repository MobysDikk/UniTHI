import java.rmi.Naming;
import java.rmi.Remote;

public class DaytimeServer {

    public static void main (String atrgs[]) throws Exception{
        Remote remote = new DaytimeImpl();
        Naming.rebind("Hallo", remote); // wird unter dem Namen Hallo in die Registry geschrieben
        System.out.println("Daytime Server startet");
    }
}
