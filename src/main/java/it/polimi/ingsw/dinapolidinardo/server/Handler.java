package it.polimi.ingsw.dinapolidinardo.server;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

import java.io.IOException;
import java.rmi.RemoteException;


public interface Handler {
	
	public void setName(String name) throws RemoteException;
	public String getName() throws RemoteException;
	
	public void closeConnections() throws RemoteException;
	public void notifyMessage(String message) throws RemoteException;
	public void showBeingHuman (String name)throws RemoteException;
	public void showBeingAlien (String name)throws RemoteException;
	public Coordinates askForMovement(boolean reask)  throws IOException, ClassNotFoundException;
	public boolean askForAttack() throws ClassNotFoundException, IOException;
	public String askForNoise() throws ClassNotFoundException, IOException;
	public int askForItem(String objects, boolean fromDiscardCall) throws IOException;
	public Coordinates askForLights(boolean reask) throws IOException, ClassNotFoundException;
	public int askAlienForItemChange(String objects) throws IOException;
	public int askHumanForItemChange(String objects) throws IOException;
	public void showActualSituation (String name, String position, String objects, String turn) throws RemoteException;
	public void notifyEscape (boolean escaped, String name, String shipnumber) throws RemoteException;
	public void showFinalResults (boolean iWon, String name, String humanlosers, String humanwinners, String alienwinners, String alienlosers) throws RemoteException;
	public void updateView(String position, String reachables, String objects) throws IOException;
	public void signalEndOfTurn()throws RemoteException;
}