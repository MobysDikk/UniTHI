package Aufgabe6;

import java.rmi.Naming;

public class Client {
	public static void main(String args[]) throws Exception{
		if(args.length < 1){
			System.out.println("Zu wenige Argumente!");
		}

	    String host = args[0];

	    ServerAgent remote = (ServerAgent) Naming.lookup("//" + host + "/agent");
	    Agent agent = new DemoAgent(100);
	    DemoAgent result = (DemoAgent) remote.execute(agent);
	    
	    System.out.println(result.getResult());
	}
}
