package it.polimi.ingsw.DiNapoliDiNardo.Server;

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
	HashMap<String, String> playersconnected = new HashMap<String, String>();
	HashMap<String, RemoteNotifier> notifiers = new HashMap<String, RemoteNotifier>();
	RemoteHandler handler = new RmiHandlerObject(this);
	CallableClient client = new RemoteCallableClient(this);
	
	
	public static void main(String[] args) throws IOException, NotBoundException {
		Server headserver = new Server();
		headserver.openconnections();
		
	};
	
	
	public void openconnections() throws IOException, NotBoundException{
		Registry registry = null;
		String name = "Handler";
		String clientName = "Client";  
		
		//Starting RMI server
		try {
            
            //RemoteHandler handler = new RmiHandlerObject(this);
            RemoteHandler stub =
                (RemoteHandler) UnicastRemoteObject.exportObject(handler, 0); 
            CallableClient clientStub = (CallableClient) UnicastRemoteObject.exportObject(client, 4040);
            registry = LocateRegistry.createRegistry(2020);            
            registry.bind(name, stub);
            registry.bind(clientName, clientStub);
            
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
            registry = null;
        }
		
		//Starting socket server
		SocketServer socketserver = new SocketServer(this);
		System.out.println("Starting the server...");
		socketserver.startListening();
		boolean finish = false;
		
		//After collecting an appropriate amount of players the game starts. 
		while(!finish){
			
			socketserver.askForNames();
			System.out.println(playersconnected.toString());
			System.out.println(notifiers.toString());
			for (RemoteNotifier rn: notifiers.values()){
				rn.notifyMessage("ciaone", rn.getName());
				
			}
			
			
				//corpo partita
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		socketserver.endListening();
		if(registry != null)
			registry.unbind(name);
	}
	
	
	//getters and setters
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
	
	
	/*private static String readLine(String format, Object... args) throws IOException {
	    if (System.console() != null) {
	        return System.console().readLine(format, args);
	    }
	    System.out.print(String.format(format, args));
	    
	    BufferedReader br = null;
	    InputStreamReader isr = null;
	    String read = null;
	    
	    isr = new InputStreamReader(System.in);
	    br = new BufferedReader(isr);
	    read = br.readLine();
	    
	    return read;
	}*/
}
