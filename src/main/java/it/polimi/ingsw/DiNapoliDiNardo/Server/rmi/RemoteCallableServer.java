package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteCallableServer extends Remote {
	
	public List<String> getNamesInGame() throws RemoteException;
	public int getTotalPlayers()  throws RemoteException;
	public int getRMINumPlayers()  throws RemoteException;
	public boolean isStarted() throws RemoteException;
	public boolean isFinish() throws RemoteException;
	
	public void increaseRMINumPlayers() throws RemoteException;
	public void addPlayer(String name) throws RemoteException;
	boolean isNameCompletionElapsed() throws RemoteException;
	
	
}
