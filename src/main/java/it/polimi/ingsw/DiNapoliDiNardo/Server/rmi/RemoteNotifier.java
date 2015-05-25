package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RemoteNotifier extends Remote {
	
	public void setName(String name) throws RemoteException;
	public String getName() throws RemoteException;
	
	void notifyMessage(String message) throws RemoteException;
	public void showBeingHuman (String name)throws RemoteException;
	public void showBeingAlien (String name)throws RemoteException;
	public Coordinates askForMovement(boolean reask)  throws RemoteException;
	public boolean askForAttack() throws ClassNotFoundException, IOException, RemoteException;
	public String askForNoise() throws RemoteException;
	public int askForItem(String objects) throws IOException,RemoteException;
	public Coordinates askForLights() throws IOException, ClassNotFoundException, RemoteException;
	public int askAlienForItemChange(String objects) throws IOException,RemoteException;
	public int askHumanForItemChange(String objects) throws IOException,RemoteException;
	public void showActualSituation (String name, String position, String objects) throws RemoteException;

}
