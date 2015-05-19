package it.polimi.ingsw.DiNapoliDiNardo.Server.Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class SocketServer {

	private int port;
	private String address;
	private ServerSocket serversocket;
	private boolean listening;
	private String status; 
	private List<SocketHandler> handlers;
	
	private PrintStream out=System.out;
	
	
	public void startListening() throws IOException {
		if(!listening){
			
			ExecutorService executor=Executors.newCachedThreadPool();
			
			serversocket = new ServerSocket(port);
			status = "Listening...";
			listening = true;
			while(listening){
				try{
					while(handlers.size()<2){
						
						Socket s = serversocket.accept();
						SocketHandler sockethandler = new SocketHandler(s);
						handlers.add(sockethandler);
						executor.submit(sockethandler);
						out.println("player connected: there are "+(handlers.size())+" players now");
					}
					
					listening=false;
				} catch (IOException ex){ 
					ex.printStackTrace();
				}
			}
			
		}
	}
	
	
	public void askForMovement() throws IOException{
		for (SocketHandler sh : handlers){
			sh.askForMovement();
			
		}
	}
		

	public void endListening() throws IOException{
		if(listening){
			listening = false;
			for(SocketHandler sh : handlers)
				sh.Close();			
			
			serversocket.close();
			status = "Closed.";
		}
	}

	
	
	//Getters and Setters
	public SocketServer() {
		port = 8888;
		address = "127.0.0.1";
		listening = false;
		status = "Created";
		handlers = new LinkedList<SocketHandler>();
	}
	
	public SocketServer(int port, String address) {
		super();
		this.port = port;
		this.address = address;
		listening = false;
		status = "Created";
		handlers = new LinkedList<SocketHandler>();
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
