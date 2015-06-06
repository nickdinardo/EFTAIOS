package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;


import it.polimi.ingsw.DiNapoliDiNardo.Server.Server;
import it.polimi.ingsw.DiNapoliDiNardo.remotestubs.RemoteClientRegisterer;
import it.polimi.ingsw.DiNapoliDiNardo.remotestubs.RemoteRMIHandler;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class ClientRegisterer implements RemoteClientRegisterer {
	Server headserver;
	
	public ClientRegisterer(Server server){
		this.headserver = server;
	}
	
	
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
