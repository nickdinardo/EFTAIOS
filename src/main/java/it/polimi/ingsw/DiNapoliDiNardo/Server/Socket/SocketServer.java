package it.polimi.ingsw.DiNapoliDiNardo.Server.Socket;

import it.polimi.ingsw.DiNapoliDiNardo.Server.Server;

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
	private ServerSocket serversocket;
	private boolean listening;
	private String status; 
	private List<SocketHandler> sockethandlers;
	private Server headserver;
	private static final int MAXPLAYERS = 8;
	
	
	public void run() {
		try {
			this.startListening();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void startListening()  throws IOException {
		if(!listening){
			
			ExecutorService executor=Executors.newCachedThreadPool();
			serversocket = new ServerSocket(port);
			status = "Listening...";
			listening = true;
			
			while(listening){
				try{
					while(headserver.getTotalPlayers()<MAXPLAYERS){
						
						Socket s = serversocket.accept();
						headserver.increaseTotalPlayers();
						SocketHandler sockethandler = new SocketHandler(s);
						sockethandlers.add(sockethandler);
						executor.submit(sockethandler);
						
						
					}
					listening=false;
					
				} catch (IOException ex){ 
				//ex.printStackTrace();
				}
			}
		}
	}
	
	
	public void askForNames() throws IOException{
		for (SocketHandler sh : sockethandlers){
			Set<String> namesSet = headserver.getPlayersconnected().keySet();
			List<String> ingamenames = new ArrayList<String>();
			for (String str : namesSet)
				ingamenames.add(str);
			
			String name = sh.askName(ingamenames, false);
			//if player input a name that already exists, update the names in game and ask again
			while ("namenotvalid1765".equals(name)){
				namesSet = headserver.getPlayersconnected().keySet();
				ingamenames = new ArrayList<String>();
				for (String str : namesSet)
					ingamenames.add(str);
				name = sh.askName(ingamenames, true);
			}
			headserver.putPlayerconnected(name,"Socket");
			headserver.putSockethandlers(name, sh);
			headserver.putInHandlers(name, sh);
			}
	}
		

	
	
	
	
	//close connections
	public void endListening() throws IOException{
		if(listening){
			listening = false;
			for(SocketHandler sh : sockethandlers)
				sh.close();			
			
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
		address = "127.0.0.1";
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
