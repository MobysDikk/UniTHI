package Aufgabe6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerAgentImpl extends UnicastRemoteObject implements ServerAgent{

	protected ServerAgentImpl() throws RemoteException {
		super();
	}

	@Override
	public Agent execute(Agent agent) throws RemoteException {
		agent.execute();
		return agent;
	}

}
