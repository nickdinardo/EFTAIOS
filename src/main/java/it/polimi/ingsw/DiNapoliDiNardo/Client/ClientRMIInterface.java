package it.polimi.ingsw.DiNapoliDiNardo.Client;

import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.*;
import it.polimi.ingsw.DiNapoliDiNardo.view.TextView;


import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMIInterface implements NetworkInterface {

	private RemoteHandler handler;
	//set getter
	public TextView view = new TextView();
	private String name = "";
	Registry registry;
	int clientport;
	
	public boolean connect() {
		//first thing get the handler client->server
		String name = "Handler";
		try {
			registry = LocateRegistry.getRegistry(2020);
		} catch (RemoteException e) {			
			e.printStackTrace();
			return false;
		}
        try {
			handler = (RemoteHandler) registry.lookup(name);
			
		} catch (AccessException e) {
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		} catch (NotBoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	//interaction method
		public void startInterface() throws RemoteException {
			
			handler.IncreaseRMINumPlayers();
			name = view.askName();
			handler.addPlayer(name);
			this.clientport = 3030+handler.getRMINumPlayers();
			
			//Starting RMI registry
			Registry myregistry = null;
			String notName = "Notifier";
			
			try {
			//Binding the notifier
			    RemoteNotifier notifier = new Notifier(name, view);
			    RemoteNotifier stub = (RemoteNotifier) UnicastRemoteObject.exportObject(notifier, 0);            
			    myregistry = LocateRegistry.createRegistry(clientport);            
			    myregistry.bind(notName, stub);
			    } catch (Exception exc) {
			    	System.err.println("RMI exception:");
			    	exc.printStackTrace();
			    	myregistry = null;
			    	}
			
			//register on server
			String clientName = "Client";
			try {
				((CallableClient) registry.lookup(clientName)).setClientInServer(name, clientport);
			} catch (AccessException e) {
				e.printStackTrace();
				
			} catch (RemoteException e) {
				e.printStackTrace();
				
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			
			
			
			while(!handler.isFinish()){
				//corpo del turno
				
				
				
					
			}
		}
	
		
	
	public boolean close() {
		return true;
	}

}
