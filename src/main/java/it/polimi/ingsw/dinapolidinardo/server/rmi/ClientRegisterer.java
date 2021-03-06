package it.polimi.ingsw.dinapolidinardo.server.rmi;


import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteClientRegisterer;
import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteRMIHandler;
import it.polimi.ingsw.dinapolidinardo.server.Server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 * Remote object that looks for a remote handler at the port indicated by the client
 * and register it in the Server Thread for future Game Controller usage.
 * 
 * @see it.polimi.ingsw.dinapolidinardo.client.ClientRMIInterface
 */
public class ClientRegisterer implements RemoteClientRegisterer {
	Server headserver;
	
	public ClientRegisterer(Server server){
		this.headserver = server;
	}
	
	/**
	 * Main method to register the clientRMIhandler in the Server
	 */
	@Override
	public void setClientInServer(String name, int port) throws RemoteException, NotBoundException {
		Registry registry;
		//Locate the registry and get the remote RMIhandler, registering it on the server
		registry = LocateRegistry.getRegistry(port);
		RemoteRMIHandler rn = (RemoteRMIHandler)registry.lookup("Notifier");
			
		headserver.putNotifiers(name, rn);
		headserver.putInHandlers(name, rn);
			
	}
}	
