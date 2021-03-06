package it.polimi.ingsw.dinapolidinardo.server;

import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteCallableServer;
import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteClientRegisterer;
import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteRMIHandler;
import it.polimi.ingsw.dinapolidinardo.server.rmi.CallableServer;
import it.polimi.ingsw.dinapolidinardo.server.rmi.ClientRegisterer;
import it.polimi.ingsw.dinapolidinardo.server.socket.SocketHandler;
import it.polimi.ingsw.dinapolidinardo.server.socket.SocketServer;

import java.io.IOException;
import java.io.PrintStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;




/**
 * Class that is the server thread reference for a single game.
 * <p>
 * It collects all the incoming connections to this game and initialize a
 * Game Controller with the data of all the users in play.
 *
 */
public class Server implements Runnable {
	int gameId;
	int rmiPlayers = 0;
	int totalplayers = 0;
	Registry registry;
	String name = "Handler";
	RemoteCallableServer stub; 
	String clientName = "Client";  
    RemoteClientRegisterer clientStub;
    private PrintStream out = System.out;
	Map<String, String> playersconnected = new HashMap<String, String>();
	Map<String, Handler> handlers = new HashMap<String, Handler>();
	Map<String, RemoteRMIHandler> notifiers = new HashMap<String, RemoteRMIHandler>();
	Map<String, SocketHandler> sockethandlers = new HashMap<String, SocketHandler>();
	RemoteCallableServer handler = new CallableServer(this);
	RemoteClientRegisterer client = new ClientRegisterer(this);
	SocketServer socketserver = new SocketServer(this);
	boolean finish = false;
	boolean isStarted = false;
	boolean nameCompletionElapsed = false;
	private static final int MINPLAYERS = 2;
	private static final int MAXPLAYERS = 8;
	private static final int WAITINGTIME = 10*1000;
	private static final int NAMECOMPLETIONTIME = 60*1000;
	
	public Server(int id){
		this.gameId = id;
	}
	
	
	
	@Override
	public void run() {
		this.openconnections();
		
	}
	
	
	/**
	 * Starts both the RMI registry and the SocketServer, than calls
	 * the collect players method
	 */
	public void openconnections(){
			
		//Starting RMI server and binding handler and remotecallableclient
		try {           
            stub = (RemoteCallableServer) UnicastRemoteObject.exportObject(handler, 0+gameId); 
            clientStub = (RemoteClientRegisterer) UnicastRemoteObject.exportObject(client, 4040+gameId);
            registry = LocateRegistry.createRegistry(2020+gameId);            
            registry.bind(name, stub);
            registry.bind(clientName, clientStub);
            out.println("Remote Objects bound");
            
        } catch (Exception e) {
            System.err.println("Error while loading remote objects");
            registry = null;
            return;
        }
		
		//Starting socket server
		out.println("Starting the SocketServer...");
		Thread t = socketserver;
		t.start();
		this.collectplayers();
		this.startgame();
		
		
		try {
			socketserver.endListening();
		} catch (IOException e) {
			out.println("Couldn't close socket server at the end of the game");
		}
		
		
	}
	
	/**
	 * Collect an adequate number of players for this game.
	 * <p>
	 * It waits till at least two players are connected, then sets a timer
	 * that resets every time a new player connects. If timer elapses without
	 * new connections or eight players are reached, calls the start game method
	 */
	public void collectplayers(){		
		
		//Waiting here at least 2 players
		while (totalplayers<MINPLAYERS){
			
			    Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
			     
			
		}
		out.println("Reached minimum number of players to play. Waiting for further connections, or game will start in a little time...");
		
		//Setting a timeout for the game to start. Each time a new connection comes, timeout is extended. 
		while (totalplayers<MAXPLAYERS){
			
			long time= System.currentTimeMillis();
			long end = time+WAITINGTIME;
			int connectedplayers = totalplayers;
			
			//new single connection wait timer cycle
			while (time<end){

			    Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
			     
				time = System.currentTimeMillis();
				//when a new player connects "totalplayers" is immediately incremented,
				//but the "connectedplayers" map has to wait the name insertion, so is < than the former.
				if (connectedplayers < totalplayers){
					out.println("New player found. Currently connected: "+totalplayers+" players. Waiting for further connections, or starting in a little time...");
					break;
					}
				}
			//if no new player has been found after the wait, 
			//exits the cycle and proceed with the current amount of players (2<players<8)
			if (connectedplayers == totalplayers){
				break;
			}
		}
		
		try {
			//forbid socketserver to receive further connections to this game, 
			//that is starting
			this.stopAcceptingOthersPlayers();
		} catch (IOException e) {
			out.println("Socket server has remained listening");
		}
		
		isStarted = true;
		out.println("Starting game, waiting for all the players to input their names...");
		
		
	}
			
	
	
	/**
	 * Wait the name insertion of every user connected, till a certain time,
	 * then unbind the remote connection objects to avoid further RMI connections
	 * to this game and initialize a new Game Controller for the game
	 */
	public void startgame() {
			//asking names and waiting them from socket players
			socketserver.askForNames();
			
			//waiting for players who connected but still haven't register their names, for a limited amount of time
			//if they don't do that, game will start without them
			long time = System.currentTimeMillis();
			long end = time + NAMECOMPLETIONTIME;
			
			while(playersconnected.size()<totalplayers || notifiers.size()<rmiPlayers){
				Thread.currentThread();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//do nothing
				}
				time = System.currentTimeMillis();
				if (time > end)
					break;
			}
			nameCompletionElapsed = true;
			
			//when all players have registered their name we can unbind the remote objects 
			//to avoid further rmi connections to this game
			if(registry != null){
				try {
					registry.unbind(name);
					registry.unbind(clientName);
				} catch (Exception e) {
					out.println("Error unbinding the remote objects callable server and clientregisterer: maybe are already unbound");
				}
				
			}
			
			//then start the game
			GameController gamecontroller;
			try {
				gamecontroller = new GameController(gameId, playersconnected, handlers);
				gamecontroller.rungame();
			} catch (ClassNotFoundException | IOException e) {
				out.println("Problems with the game controller");
				out.println("Closing game "+ gameId +".");
			}
			
			
	}		
		
			
		
	
	
	
	
	//getters and setters
	public boolean isStarted() {
		return isStarted;
	}
	public boolean isNameCompletionElapsed() {
		return nameCompletionElapsed;
	}
	public int getRMIPlayers() {
		return rmiPlayers;
	}
	public int getTotalPlayers() {
		return totalplayers;
	}
	
	public void increaseRMIPlayers() {
		this.rmiPlayers += 1;
		this.totalplayers += 1;
	}
	
	public void increaseTotalPlayers() {
		this.totalplayers += 1;
	}
	
	public void decreaseTotalPlayers() {
		this.totalplayers -= 1;
	}

	public Map<String, String> getPlayersconnected() {
		return playersconnected;
	}

	public void putPlayerconnected(String key, String playername) {
		playersconnected.put(key, playername);
	}
	
	public void putNotifiers(String name, RemoteRMIHandler rn) {
		notifiers.put(name, rn);
	}
	
	public void putSockethandlers(String name, SocketHandler sh) {
		sockethandlers.put(name, sh);
	}
	
	public void putInHandlers(String name, Handler handler) {
		handlers.put(name, handler);
	}
	
	public void stopAcceptingOthersPlayers() throws IOException{
		socketserver.stopAcceptingOthersPlayers();
	}


	
	
}
