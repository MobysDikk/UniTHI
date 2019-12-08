import java.rmi.Naming;
import java.text.DateFormat;

public class DaytimeClient {

    private static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG);
    public static void main (String args []) throws Exception{
       String host = args[0];
       String text = args[1];
       System.out.println("Start at" + df);
       Daytime remote = (Daytime) Naming.lookup ("//" + host + "/echo");
       String received = remote.getTime(text);
       System.out.println("End" +df + received); 
    }
    

}
