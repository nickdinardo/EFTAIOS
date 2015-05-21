package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.Server.Socket.SocketHandler;
import it.polimi.ingsw.DiNapoliDiNardo.Server.Socket.SocketServer;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteHandler;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteNotifier;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RmiHandlerObject;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteCallableClient;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.CallableClient;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;




public class Server {
	int RMIplayers = 0;
	int totalplayers = 0;
	Registry registry = null;
	String name = "Handler";
	String clientName = "Client";  
	HashMap<String, String> playersconnected = new HashMap<String, String>();
	HashMap<String, RemoteNotifier> notifiers = new HashMap<String, RemoteNotifier>();
	HashMap<String, SocketHandler> sockethandlers = new HashMap<String, SocketHandler>();
	RemoteHandler handler = new RmiHandlerObject(this);
	CallableClient client = new RemoteCallableClient(this);
	SocketServer socketserver = new SocketServer(this);
	boolean finish = false;
	boolean isStarted = false;
	private static final int MINPLAYERS = 2;
	private static final int MAXPLAYERS = 8;
	
	
	public static void main(String[] args) throws IOException, NotBoundException {
		Server headserver = new Server();
		headserver.openconnections();
		
	};
	
	
	public void openconnections() throws IOException, NotBoundException{
		
		
		
		//Starting RMI server and binding handler and remotecallableclient
		try {           
            RemoteHandler stub = (RemoteHandler) UnicastRemoteObject.exportObject(handler, 0); 
            CallableClient clientStub = (CallableClient) UnicastRemoteObject.exportObject(client, 4040);
            registry = LocateRegistry.createRegistry(2020);            
            registry.bind(name, stub);
            registry.bind(clientName, clientStub);
            System.out.println("Remote Objects bound");
            
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
            registry = null;
        }
		
		
		//Starting socket server
		System.out.println("Starting the SocketServer...");
		Thread t = socketserver;
		t.start();
		this.collectplayers();
		this.startgame();
		
		
		socketserver.endListening();
		
	}
	
	
	public void collectplayers() throws IOException, NotBoundException{		
		//Waiting here at least 2 players
		String toAvoidChurning ="";
		while (totalplayers<MINPLAYERS){
			toAvoidChurning += "avoided";
			if (toAvoidChurning.length()>10000)
				toAvoidChurning = "";
		}
		System.out.println("Reached minimum number of players to play. Waiting for further connections, or game will start in a little time...");
		//Setting a timeout for the game to start. Each time a new connection comes, timeout is extended. 
		while (totalplayers<MAXPLAYERS){
			long time= System.currentTimeMillis();
			long end = time+20000;
			int connectedplayers = totalplayers;
			while (time<end){
				time = System.currentTimeMillis();
				toAvoidChurning += "avoided";
				if (connectedplayers < totalplayers){
					System.out.println("New player found. Currently connected: "+totalplayers+" players. Waiting for further connections, or starting in a little time...");
					break;
					}
				}
			if (connectedplayers == totalplayers){
				break;
			}
		}
		this.stopAcceptingOthersPlayers();
		isStarted = true;
		toAvoidChurning = "Starting game, waiting for all the players to input their names...";
		System.out.println(toAvoidChurning);
		
	}
			
	
	
	
	public void startgame() throws IOException, NotBoundException{
			//asking names and waiting them from socket players
			socketserver.askForNames();
			
			//waiting for RMI players who connected but still haven't register their names
			String toAvoidChurning ="";
			while(playersconnected.size()<totalplayers){
				toAvoidChurning += "avoided";
				if (toAvoidChurning.length()>10000)
					toAvoidChurning = "";
			}
			//when all players have registered their name we can unbind the remote objects 
			//to avoid further rmi connections to this game
			if(registry != null){
				registry.unbind(name);
				registry.unbind(clientName);
			}
			//then start the game
			GameServer gameserver = new GameServer(totalplayers, playersconnected, notifiers, sockethandlers);
			gameserver.rungame();
	}		
		
			
		
	
	
	
	
	//getters and setters
	public boolean isStarted() {
		return isStarted;
	}
	public int getRMIPlayers() {
		return RMIplayers;
	}
	public int getTotalPlayers() {
		return totalplayers;
	}
	public void IncreaseRMIPlayers() {
		this.RMIplayers += 1;
		this.totalplayers += 1;
	}
	
	public void IncreaseTotalPlayers() {
		this.totalplayers += 1;
	}

	public HashMap<String, String> getPlayersconnected() {
		return playersconnected;
	}

	public void putPlayerconnected(String key, String playername) {
		playersconnected.put(key, playername);
	}
	
	public void putNotifiers(String name, RemoteNotifier rn) {
		notifiers.put(name, rn);
	}
	
	public void putSockethandlers(String name, SocketHandler sh) {
		sockethandlers.put(name, sh);
	}
	
	public void stopAcceptingOthersPlayers() throws IOException{
		socketserver.stopAcceptingOthersPlayers();
	}
	
}
