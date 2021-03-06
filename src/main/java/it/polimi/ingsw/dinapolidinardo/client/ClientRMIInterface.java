package it.polimi.ingsw.dinapolidinardo.client;

import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteCallableServer;
import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteClientRegisterer;
import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteRMIHandler;
import it.polimi.ingsw.dinapolidinardo.view.View;
import it.polimi.ingsw.dinapolidinardo.view.ViewFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *  RMI version of the connection interface 
 *  <p>
 *  Gets a remotely callable instance of the server, and provides to the game server
 *	a remotely callable instance of the client
 */
public class ClientRMIInterface implements NetworkInterface {

	private static final int MAXSERVERGAMES = 1000;
	private RemoteCallableServer serverhandler;
	private PrintStream out = System.out;
	private View view;
	private String name = "";
	private Registry registry;
	private int clientport;
	private int gamesStarted = 1;
	
	/**
	 *  Explores all the server ports looking for an available remote registry.
	 *  Discharge from that server registry the remote server handler that the client will use
	 *  to register himself and his remote object in the starting game
	 *  
	 *  @return true if connection has been successful on any port, false otherwise
	 */
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

	
	/**
	 * Get a view instance via ViewFactory and then starts an own RMI registry.
	 * With the previously discharged server handler lead the server 
	 * to discharge the instance of the remote client handler that server will use during the game to call the 
	 * needed methods.
	 * @throws IOException if has problems with remote loading or can't read Pixels GUI text file
	 * 
	 * @see ViewFactory
	 */
	@Override
	public void startInterface() throws IOException {
		
		ViewFactory.getViewFactory();
		view = ViewFactory.getView();
		
		//check if the game as already started, to avoid to connect while 
		//some rmi players keep the registry open without inputting their names
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
			
			//check if the time to input the name has elapsed and thus the game has started 
			//without this client instance. If not, register on the starting game his name
			if(!serverhandler.isNameCompletionElapsed()){
				
				serverhandler.addPlayer(name);
							
				//Starting RMI client registry
				Registry myregistry = null;
				String notName = "Notifier";			
				
				try {
				//Binding the notifier
				    myregistry = LocateRegistry.createRegistry(clientport);  
				    RemoteRMIHandler clienthandler = new RMIHandler(name, view, myregistry, notName);
				    RemoteRMIHandler stub = (RemoteRMIHandler) UnicastRemoteObject.exportObject(clienthandler, 0);    
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
	

	/**
	 * Implements the NetworkInterface method close witouth actually doing nothing.
	 * (method only required for socket connections)
	 */ 	
	@Override
	public boolean close() {
		return true;
	}

}
