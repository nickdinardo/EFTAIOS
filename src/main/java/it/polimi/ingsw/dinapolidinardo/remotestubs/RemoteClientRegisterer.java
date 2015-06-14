package it.polimi.ingsw.dinapolidinardo.remotestubs;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for the ClientRegisterer
 * 
 * @see it.polimi.ingsw.dinapolidinardo.server.rmi.ClientRegisterer
 */
public interface RemoteClientRegisterer extends Remote{

	public void setClientInServer(String name, int port) throws RemoteException, NotBoundException;


}
