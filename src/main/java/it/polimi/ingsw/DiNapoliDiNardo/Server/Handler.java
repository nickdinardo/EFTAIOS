package it.polimi.ingsw.DiNapoliDiNardo.Server;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;

import java.io.IOException;
import java.rmi.RemoteException;

public interface Handler {
	
	public void setName(String name) throws RemoteException;
	public String getName() throws RemoteException;
	
	public void notifyMessage(String message) throws RemoteException;
	public void showBeingHuman (String name)throws RemoteException;
	public void showBeingAlien (String name)throws RemoteException;
	public Coordinates askForMovement(boolean reask)  throws IOException, ClassNotFoundException;
	public boolean askForAttack() throws ClassNotFoundException, IOException;
	public String askForNoise() throws ClassNotFoundException, IOException;
	public int askForItem(String objects) throws IOException;
	public Coordinates askForLights() throws IOException, ClassNotFoundException;
	public int askAlienForItemChange(String objects) throws IOException;
	public int askHumanForItemChange(String objects) throws IOException;
	public void showActualSituation (String name, String position, String objects) throws RemoteException;

}
