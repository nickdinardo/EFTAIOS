package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;


import it.polimi.ingsw.DiNapoliDiNardo.Client.CallableClient;
import it.polimi.ingsw.DiNapoliDiNardo.Server.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class RemoteCallableClient implements CallableClient {
	Server headserver;
	
	public RemoteCallableClient(Server server){
		this.headserver = server;
	}
	
	
	@Override
	public void setClientInServer(String name, int port) throws RemoteException {
		Registry registry;
		try {
			//Locate the registry and get the remote notifier
			registry = LocateRegistry.getRegistry(port);
			RemoteRMIHandler rn = (RemoteRMIHandler)registry.lookup("Notifier");
			
			headserver.putNotifiers(name, rn);
			headserver.putInHandlers(name, rn);
			
			
		
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
}	
