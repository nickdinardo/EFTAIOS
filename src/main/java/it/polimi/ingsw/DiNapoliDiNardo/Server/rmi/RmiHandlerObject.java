package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import java.rmi.RemoteException;

public class RmiHandlerObject implements RemoteHandler {

	private boolean on;
	
	
	public boolean isOn() throws RemoteException{
		return on;
	}
	
	
	public boolean turn(boolean value) throws RemoteException {
		this.on = value;
		return this.on;
	}

}
