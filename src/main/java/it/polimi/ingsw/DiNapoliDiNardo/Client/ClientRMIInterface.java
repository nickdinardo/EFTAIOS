package it.polimi.ingsw.DiNapoliDiNardo.Client;

import it.polimi.ingsw.DiNapoliDiNardo.remotestubs.RemoteCallableServer;
import it.polimi.ingsw.DiNapoliDiNardo.remotestubs.RemoteClientRegisterer;
import it.polimi.ingsw.DiNapoliDiNardo.remotestubs.RemoteRMIHandler;
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
	private RemoteCallableServer serverhandler;
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
			out.println("Failed to reach RMI registry");
			return false;
		}
        try {
			serverhandler = (RemoteCallableServer) registry.lookup(handlername);
			
		} catch (AccessException e) {
			out.println("AccessException");
			return false;
		} catch (RemoteException e) {
			out.println("RemoteException");
			return false;
		} catch (NotBoundException e) {
			out.println("Remote initializers not more avaible. Server is not accepting further players connections because game "+gamesStarted+" has already started.");
			out.println("Trying to connect to following game...");
			if (gamesStarted<MAXSERVERGAMES){
				gamesStarted++;
				//recursively call connect method looking on the following server ports till a certain number of games
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
		if(!serverhandler.isStarted()){
			
			serverhandler.increaseRMINumPlayers();
			//open the client port skipping 8 ports for every game started (in case they are already in use in local for others games)
			this.clientport = 3030+serverhandler.getRMINumPlayers()+(8*gamesStarted);
			
			name = view.askName(false);
			List<String> names = serverhandler.getNamesInGame();
			//if name is already taken for this game ask again to avoid confusion in the game
			while (names.contains(name) || "".equals(name)){
				names = serverhandler.getNamesInGame();
				name = view.askName(true);
			}
			
			if(!serverhandler.isNameCompletionElapsed()){
				serverhandler.addPlayer(name);
			
			
				
				//Starting RMI client registry
				Registry myregistry = null;
				String notName = "Notifier";			
				try {
				//Binding the notifier
				    myregistry = LocateRegistry.createRegistry(clientport);  
				    RemoteRMIHandler notifier = new RMIHandler(name, view, myregistry, notName);
				    RemoteRMIHandler stub = (RemoteRMIHandler) UnicastRemoteObject.exportObject(notifier, 0);    
				    myregistry.bind(notName, stub);
				    } catch (Exception exc) {
				    	out.println("RMI exception while exporting object");
				    	myregistry = null;
				    	}
					
				//Register on server and set the notifier on the server
				String clientName = "Client";
				try {
					((RemoteClientRegisterer) registry.lookup(clientName)).setClientInServer(name, clientport);
				} catch (AccessException e) {
					out.println("AccessException");
						
				} catch (RemoteException e) {
					out.println("RemoteException");
						
				} catch (NotBoundException e) {
					out.println("Server has threw out your connection because you entered your name with too much delay."); 
					out.println("Try to reconnect to another game, and please remember to insert your name in a reasonable time."); 
				}
			}
			else{
				out.println("Server has threw out your connection because you entered your name with too much delay."); 
				out.println("Try to reconnect to another game, and please remember to insert your name in a reasonable time.");
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
