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
	
	@Override
	public void run() {
		try {
			this.startListening();
		} catch (IOException e) {
			//if main server has closed this, it interrupts the thread
			this.interrupt();
		}
	}
	
	
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
		

	
	
	
	
	//close connections
	public void endListening() throws IOException{
		if(listening){
			listening = false;
			for(SocketHandler sh : sockethandlers)
				sh.closeConnections();			
			
			serversocket.close();
			status = "Closed.";
		}
	}
	
	//stop accepting connections but keeps alive current handlers
	public void stopAcceptingOthersPlayers() throws IOException{
		if(listening){
			listening = false;
			serversocket.close();
			}
	}	
	
	//Constructors, Getters and Setters
	public SocketServer(Server head) {
		port = 8888;
		address = LOCAL;
		listening = false;
		status = "Created";
		sockethandlers = new LinkedList<SocketHandler>();
		headserver = head;
	}
	
	public SocketServer(int port, String address, Server head) {
		super();
		this.port = port;
		this.address = address;
		listening = false;
		status = "Created";
		sockethandlers = new LinkedList<SocketHandler>();
		headserver = head;
	}
	
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
