package it.polimi.ingsw.DiNapoliDiNardo.Client;

import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.*;
import it.polimi.ingsw.DiNapoliDiNardo.view.View;
import it.polimi.ingsw.DiNapoliDiNardo.view.ViewFactory;







import java.io.PrintStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClientRMIInterface implements NetworkInterface {

	private RemoteHandler handler;
	private PrintStream out = System.out;
	private View view;
	private String name = "";
	Registry registry;
	int clientport;
	
	@Override
	public boolean connect() {
		//first thing get the handler client->server
		String handlername = "Handler";
		try {
			registry = LocateRegistry.getRegistry(2020);
		} catch (RemoteException e) {			
			e.printStackTrace();
			return false;
		}
        try {
			handler = (RemoteHandler) registry.lookup(handlername);
			
		} catch (AccessException e) {
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		} catch (NotBoundException e) {
			out.println("Remote initializers not more avaible. Server is not accepting further players connections.");
			return false;
		}
		return true;
	}

	@Override
	public void startInterface() throws RemoteException {
		
		ViewFactory.getViewFactory();
		view = ViewFactory.getView();
		
		//check if the game as already started, to avoid to connect while 
		//some rmi players keep the registry on without inputting their names
		if(!handler.isStarted()){
			handler.increaseRMINumPlayers();
			this.clientport = 3030+handler.getRMINumPlayers();
			name = view.askName(false);
			
			List<String> names = handler.getNamesInGame();
			while (names.contains(name) || "".equals(name)){
				
				names = handler.getNamesInGame();
				name = view.askName(true);
			}
				
			handler.addPlayer(name);
		
			
			
			//Starting RMI client registry
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
				
			//Register on server and set the notifier on the server
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
		}	
		else {
			out.println("The game you tried to connect has already started");
		}
		
		
	}
	
		
	@Override
	public boolean close() {
		return true;
	}

}
