package it.polimi.ingsw.dinapolidinardo.remotestubs;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.server.Handler;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Remote interface for the RMIHandler
 * 
 * @see it.polimi.ingsw.dinapolidinardo.client.RMIHandler
 */
public interface RemoteRMIHandler extends Remote, Handler {
	
	@Override
	public void closeConnections() throws RemoteException;
	@Override
	public String getName() throws RemoteException;
	@Override
	void notifyMessage(String message) throws RemoteException;
	@Override
	public void showBeingHuman (String name)throws RemoteException;
	@Override
	public void showBeingAlien (String name)throws RemoteException;
	@Override
	public Coordinates askForMovement(boolean reask)throws IOException;
	@Override
	public boolean askForAttack() throws ClassNotFoundException, IOException;
	@Override
	public String askForNoise() throws IOException;
	@Override
	public int askForItem(String objects, boolean fromDiscardCall) throws IOException;
	@Override
	public Coordinates askForLights(boolean reask) throws IOException, ClassNotFoundException;
	@Override
	public int askAlienForItemChange(String objects) throws IOException;
	@Override
	public int askHumanForItemChange(String objects) throws IOException;
	@Override
	public void showActualSituation (String name, String position, String objects, String turn) throws RemoteException;
	@Override
	public void notifyEscape(boolean escaped, String name, String shipnumber) throws RemoteException;
	@Override
	public void showFinalResults(boolean iWon, String name, String humanlosers,	String humanwinners, String alienwinners, String alienlosers) throws RemoteException;
	
}
