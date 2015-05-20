package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import it.polimi.ingsw.DiNapoliDiNardo.Server.Server;


import java.rmi.RemoteException;

public class RmiHandlerObject implements RemoteHandler {
	Server headserver;
	boolean finish = false;
	String name = "";
	
	
	public void IncreaseRMINumPlayers() throws RemoteException{
		headserver.IncreaseRMIPlayers();
	}
	
	public int getRMINumPlayers(){
		return headserver.getRMIPlayers();
	}
	
	
	public void addPlayer(String name) {
		headserver.putPlayerconnected(name,"RMI");
		
	}
	

	
	
	
	//constructor
	public RmiHandlerObject(Server server){
		this.headserver = server;
	}
	//getters and setters
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	
}
