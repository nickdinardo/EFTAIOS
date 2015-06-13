package it.polimi.ingsw.dinapolidinardo.server.rmi;

import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteCallableServer;
import it.polimi.ingsw.dinapolidinardo.server.Server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class CallableServer implements RemoteCallableServer {
	Server headserver;
	
	boolean finish = false;
	String name = "";
	
	
	public CallableServer(Server server){
		this.headserver = server;
	}
		
		
	@Override
	public void increaseRMINumPlayers() throws RemoteException{
		headserver.increaseRMIPlayers();
	}
	
	@Override
	public int getRMINumPlayers(){
		return headserver.getRMIPlayers();
	}
	
	@Override
	public int getTotalPlayers(){
		return headserver.getTotalPlayers();
	}
	
	@Override
	public List<String> getNamesInGame(){
		Set<String> namesSet = headserver.getPlayersconnected().keySet();
		List<String> ingamenames = new ArrayList<String>();
		for (String str : namesSet)
			ingamenames.add(str);
		return ingamenames;
	}
	
	@Override
	public void addPlayer(String name) {
		headserver.putPlayerconnected(name,"RMI");
		
	}
	
	@Override
	public boolean isStarted(){
		return headserver.isStarted();
	}
	
	@Override
	public boolean isNameCompletionElapsed(){
		return headserver.isNameCompletionElapsed();
	}
	
	@Override
	public boolean isFinish() {
		return finish;
	}
}
