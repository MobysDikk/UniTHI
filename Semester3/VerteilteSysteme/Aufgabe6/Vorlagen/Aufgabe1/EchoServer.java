import java.rmi.*;

public class EchoServer {
  public static void main(String args[]) throws Exception {
    Remote remote = new EchoImpl();
    Naming.rebind("echo", remote);
    System.out.println("EchoServer gestartet ...");
  }
}
