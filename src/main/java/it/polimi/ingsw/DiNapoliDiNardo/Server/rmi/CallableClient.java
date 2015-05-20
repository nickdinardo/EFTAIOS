package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Riccardo Cipolleschi
 * Remote interface exposed by the Server 
 * to allow a client to register on the server.
 */
public interface CallableClient extends Remote{
	
	/**
	 * Method used by the client to register itself on the server
	 * @param port the port that can be used by RMI to call the client
	 * @throws RemoteException
	 */
	void setClientInServer(String name, int port) throws RemoteException;
}
