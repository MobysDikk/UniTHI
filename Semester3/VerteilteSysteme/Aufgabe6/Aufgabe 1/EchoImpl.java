package Aufgabe6;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class EchoImpl extends UnicastRemoteObject implements Echo{

	protected EchoImpl() throws RemoteException {
		super();
	}

	@Override
	public String getEcho(String input) throws RemoteException {
		return input + input;
	}

}
