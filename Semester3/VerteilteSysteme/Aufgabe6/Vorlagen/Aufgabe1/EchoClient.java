import java.rmi.*;

public class EchoClient {
  public static void main(String args[]) throws Exception {
    if (args.length != 2) {
      System.err.println("java EchoClient <host> <text>");
      System.exit(1);
    }

    String host = args[0];
    String text = args[1];

    Echo remote = (Echo) Naming.lookup("//" + host + "/echo");
    String received = remote.getEcho(text);
    System.out.println(received);
  }
}
