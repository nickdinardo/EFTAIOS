package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;



import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteHandler extends Remote {
	
	public int getTotalPlayers()  throws RemoteException;
	public int getRMINumPlayers()  throws RemoteException;
	public boolean isStarted() throws RemoteException;
	public boolean isFinish() throws RemoteException;
	
	public void IncreaseRMINumPlayers() throws RemoteException;
	public void addPlayer(String name) throws RemoteException;
	
}
