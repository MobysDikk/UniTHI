import java.rmi.Naming;
import java.util.Date;

public class DaytimeClient {

    
    public static void main (String args []) throws Exception{
       String host = args[0];
       String text = args[1];
       
       Date date = new Date();
       System.out.println(text + " Start at  "+ date.toString());
       
       Daytime remote = (Daytime) Naming.lookup("//" + host + "/Hallo");
       String recived = remote.getTime(text);
       Date date3 = new Date();
       System.out.println(recived + " back at " + date3.toString()); 
    }
    

}
