package Aufgabe6;

import java.rmi.Naming;
import java.rmi.Remote;

public class Server {
	public static void main(String args[]) throws Exception{
	    Remote remote = new ServerAgentImpl();
	    Naming.rebind("execute", remote);
	    System.out.println("Server gestartet ...");
	  }
}
