package it.polimi.ingsw.dinapolidinardo.remotestubs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


/**
 * Remote interface for the Callable Server
 * 
 * @see it.polimi.ingsw.dinapolidinardo.server.rmi.CallableServer
 */
public interface RemoteCallableServer extends Remote {
	
	public List<String> getNamesInGame() throws RemoteException;
	public int getTotalPlayers()  throws RemoteException;
	public int getRMINumPlayers()  throws RemoteException;
	public boolean isStarted() throws RemoteException;
	public boolean isFinish() throws RemoteException;
	
	public void increaseRMINumPlayers() throws RemoteException;
	public void addPlayer(String name) throws RemoteException;
	public boolean isNameCompletionElapsed() throws RemoteException;
	
	
}
