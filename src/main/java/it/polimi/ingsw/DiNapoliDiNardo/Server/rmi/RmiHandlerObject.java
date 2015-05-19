package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import it.polimi.ingsw.DiNapoliDiNardo.Server.Server;
import it.polimi.ingsw.DiNapoliDiNardo.view.TextView;

import java.rmi.RemoteException;

public class RmiHandlerObject implements RemoteHandler {
	Server headserver;
	private TextView view = new TextView();
	boolean finish = false;
	
	
	
	public TextView getView() {
		return view;
	}
	
	
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}


	//constructor
	public RmiHandlerObject(Server server){
		this.headserver = server;
	}
	
	
	public void IncreaseNumPlayers() throws RemoteException{
		headserver.IncreasePlayersnum();
	}
	
	
	

}
