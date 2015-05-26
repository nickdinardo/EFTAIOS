package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;

import java.io.IOException;
import java.rmi.RemoteException;

public interface Handler {
	
	public void setName(String name) throws RemoteException;
	public String getName() throws RemoteException;
	
	public void notifyMessage(String message) throws RemoteException;
	public void showBeingHuman (String name)throws RemoteException;
	public void showBeingAlien (String name)throws RemoteException;
	public Coordinates askForMovement(boolean reask)  throws RemoteException, IOException, ClassNotFoundException;
	public boolean askForAttack() throws ClassNotFoundException, IOException, RemoteException;
	public String askForNoise() throws RemoteException, ClassNotFoundException, IOException;
	public int askForItem(String objects) throws IOException,RemoteException;
	public Coordinates askForLights() throws IOException, ClassNotFoundException, RemoteException;
	public int askAlienForItemChange(String objects) throws IOException,RemoteException;
	public int askHumanForItemChange(String objects) throws IOException,RemoteException;
	public void showActualSituation (String name, String position, String objects) throws RemoteException;

}
