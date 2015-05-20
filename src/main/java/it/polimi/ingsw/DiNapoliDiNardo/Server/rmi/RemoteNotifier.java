package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Riccardo Cipolleschi
 * Remote interface exposed by the clients. 
 * This allows the server to send messages to the clients.
 *
 */
public interface RemoteNotifier extends Remote {
	public void setName(String name) throws RemoteException;
	public String getName() throws RemoteException;
	void notifyMessage(String message, String source) throws RemoteException;
}
