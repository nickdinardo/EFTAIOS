package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.Handler;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;



public interface RemoteNotifier extends Remote, Handler {
	
	@Override
	public void setName(String name) throws RemoteException;
	@Override
	public String getName() throws RemoteException;
	@Override
	void notifyMessage(String message) throws RemoteException;
	@Override
	public void showBeingHuman (String name)throws RemoteException;
	@Override
	public void showBeingAlien (String name)throws RemoteException;
	@Override
	public Coordinates askForMovement(boolean reask)  throws RemoteException;
	@Override
	public boolean askForAttack() throws ClassNotFoundException, IOException;
	@Override
	public String askForNoise() throws RemoteException;
	@Override
	public int askForItem(String objects) throws IOException;
	@Override
	public Coordinates askForLights() throws IOException, ClassNotFoundException;
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
