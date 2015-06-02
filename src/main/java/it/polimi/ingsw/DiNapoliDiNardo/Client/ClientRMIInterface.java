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

	private static final int MAXSERVERGAMES = 1000;
	private RemoteHandler handler;
	private PrintStream out = System.out;
	private View view;
	private String name = "";
	Registry registry;
	int clientport;
	int gamesStarted = 1;
	
	
	@Override
	public boolean connect() {
		//first thing get the handler client->server
		String handlername = "Handler";
		try {
			registry = LocateRegistry.getRegistry(2020+gamesStarted);
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
			out.println("Remote initializers not more avaible. Server is not accepting further players connections because game "+gamesStarted+" has already started.");
			out.println("Trying to connect to following game...");
			if (gamesStarted<MAXSERVERGAMES){
				gamesStarted++;
				//recursively call connect method looking on the following server ports
				return connect();
			}
			else
				out.println("Server is not accepting connection on any game, sorry. We hope to solve the issue in a little time.");
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
			//open the clientport skipping 8 ports for every game started (in case they are already in use in local for others games)
			this.clientport = 3030+handler.getRMINumPlayers()+(8*gamesStarted);
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
			out.println("The game you tried to connect has already started, but the following game still has to be initialized.");
			out.println("Please retry connection in a little time.");
		}
		
		
	}
	
		
	@Override
	public boolean close() {
		return true;
	}

}
