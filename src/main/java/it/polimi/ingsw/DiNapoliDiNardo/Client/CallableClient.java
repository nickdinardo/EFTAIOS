package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CallableClient extends Remote{

	void setClientInServer(String name, int port) throws RemoteException;
}
