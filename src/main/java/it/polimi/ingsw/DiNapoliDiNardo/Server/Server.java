package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.Server.Socket.SocketServer;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteHandler;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RmiHandlerObject;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;



public class Server {
	int playersnum = 0;
	RemoteHandler handler= new RmiHandlerObject(this);; 
	
	
	public static void main(String[] args) throws IOException, NotBoundException {
		Server headserver = new Server();
		headserver.openconnections();
		
	};
	
	
	public void openconnections() throws IOException, NotBoundException{
		Registry registry = null;
		String name = "Handler";
		
		//Starting RMI server
		try {
            
            //RemoteHandler handler = new RmiHandlerObject(this);
            RemoteHandler stub =
                (RemoteHandler) UnicastRemoteObject.exportObject(handler, 0);            
            registry = LocateRegistry.createRegistry(2020);            
            registry.bind(name, stub);
            
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
            registry = null;
        }
		
		//Starting socket server
		SocketServer server = new SocketServer(this);
		System.out.println("Starting the server...");
		server.startListening();
		System.out.println("players connected: there are "+playersnum+" players now");
		System.out.println("Server started. Status: "+server.getStatus()+". Port: "+server.getPort());
		boolean finish = false;
		
		while(!finish){
			server.askForMovement();
			
			
				//corpo partita
			}
		
		
		
		server.endListening();
		if(registry != null)
			registry.unbind(name);
	}
	
	
	//getters and setters
	public int getPlayersnum() {
		return playersnum;
	}
	public void IncreasePlayersnum() {
		this.playersnum += 1;
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
