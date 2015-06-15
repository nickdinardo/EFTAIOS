package it.polimi.ingsw.dinapolidinardo.server.socket;

import it.polimi.ingsw.dinapolidinardo.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Class that manages the incoming socket connections till game
 * doesn't start and assigns them to different socket handlers.
 * <p>
 * The socket handlers will be used by the Game Controller 
 * since the start of the game
 */
public class SocketServer extends Thread{

	private int port;
	private String address;
	private static final String LOCAL = "127.0.0.1";
	private ServerSocket serversocket;
	private boolean listening;
	private String status; 
	private List<SocketHandler> sockethandlers;
	private Server headserver;
	private static final int MAXPLAYERS = 8;
	
	
	/**
	 * Constructor with default local IP and port 
	 * 
	 * @param head reference to main Server Thread of the game
	 */
	public SocketServer(Server head) {
		port = 8888;
		address = LOCAL;
		listening = false;
		status = "Created";
		sockethandlers = new LinkedList<SocketHandler>();
		headserver = head;
	}
	
	/**
	 * Constructor that allows to define desired IP and port
	 *
	 * @param port the connection port
	 * @param address the connection IP
	 * @param head reference to main Server Thread of the game
	 */
	public SocketServer(int port, String address, Server head) {
		super();
		this.port = port;
		this.address = address;
		listening = false;
		status = "Created";
		sockethandlers = new LinkedList<SocketHandler>();
		headserver = head;
	}
	
	
	
	@Override
	public void run() {
		try {
			this.startListening();
		} catch (IOException e) {
			//if main server has closed this, it interrupts the thread
			this.interrupt();
		}
	}
	
	/**
	 * Accepts incoming Socket connections and creates a Socket Handler thread for each one.
	 * <p>
	 * All the Socket Handlers run concurrently and are submitted to an ExecutorService
	 * 
	 * @throws IOException in case of problems with SocketServer
	 */
	public void startListening()  throws IOException {
		if(!listening){
			
			ExecutorService executor=Executors.newCachedThreadPool();
			serversocket = new ServerSocket(port);
			status = "Listening...";
			listening = true;
			
			while(listening){
				while(headserver.getTotalPlayers()<MAXPLAYERS){
						
					Socket s = serversocket.accept();
					headserver.increaseTotalPlayers();
					SocketHandler sockethandler = new SocketHandler(s);
					sockethandlers.add(sockethandler);
					executor.submit(sockethandler);
						
						
				}
				listening = false;
					
			}
		}
	}
	
	
	/**
	 * Gets from main server the list of the existing names and ask to every 
	 * Socket Handler a name. Asks again every time name chosen has already been taken, 
	 * updating the list of existing names in case has been modified by other threads
	 */
	public void askForNames(){
		
		Runnable task = new Runnable() {
			
			@Override
			public void run(){
				
				for (SocketHandler sh : sockethandlers){
					Set<String> namesSet = headserver.getPlayersconnected().keySet();
					List<String> ingamenames = new ArrayList<String>();
					for (String str : namesSet)
						ingamenames.add(str);
					
					try{
						String name = sh.askName(ingamenames, false);
						
						//if player input a name that already exists, update the names in game and ask again
						while ("namenotvalid1765".equals(name)){
							namesSet = headserver.getPlayersconnected().keySet();
							ingamenames = new ArrayList<String>();
							for (String str : namesSet)
								ingamenames.add(str);
							name = sh.askName(ingamenames, true);
						}
						
						//check if max time to input names has elapsed and game has started 
						//without the user or not
						if(!headserver.isNameCompletionElapsed()){
							headserver.putPlayerconnected(name,"Socket");
							headserver.putSockethandlers(name, sh);
							headserver.putInHandlers(name, sh);
						}
						else{
							sh.notifyMessage("Server has threw out your connection because you entered your name with too much delay."); 
							sh.notifyMessage("Try to reconnect to another game, and please remember to insert your name in a reasonable time.");
							return;
						}
					}
					catch (IOException e){
						//if connection fall during initialization of the game, remove the "wait" for this player and removes him
						headserver.decreaseTotalPlayers();
						return;
					}
				}
		
			}
		
		};
		new Thread(task, "ServiceThread").start(); 
	
	}
		

	/**
	 * Stops accepting connections and closes all the generated 
	 * Socket Handlers. Method is called at the end of a game.
	 * 
	 * @throws IOException if can't close the ServerSocket
	 */
	public void endListening() throws IOException{
		if(listening){
			listening = false;
			for(SocketHandler sh : sockethandlers)
				sh.closeConnections();			
			
			serversocket.close();
			status = "Closed.";
		}
	}
	
	
	/**
	 * Stops accepting connections but keeps alive current handlers
	 * 
	 * @throws IOException if can't close the ServerSocket
	 */
	public void stopAcceptingOthersPlayers() throws IOException{
		if(listening){
			listening = false;
			serversocket.close();
			}
	}	
	
	
	
	//Getters and Setters
	
	public String getStatus() {
		return status;
	}

	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
