package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteNotifier extends Remote {
	
	public void setName(String name) throws RemoteException;
	public String getName() throws RemoteException;
	void notifyMessage(String message) throws RemoteException;
	public void showBeingHuman (String name)throws RemoteException;
	public void showBeingAlien (String name)throws RemoteException;
	public Coordinates askForMovement(boolean reask)  throws RemoteException;
}
