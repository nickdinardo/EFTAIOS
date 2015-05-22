package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CallableClient extends Remote{

	void setClientInServer(String name, int port) throws RemoteException;
}
