package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteNotifier extends Remote {
	public void setName(String name) throws RemoteException;
	public String getName() throws RemoteException;
	void notifyMessage(String message) throws RemoteException;
}
