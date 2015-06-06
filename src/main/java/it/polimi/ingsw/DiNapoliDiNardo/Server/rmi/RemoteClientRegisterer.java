package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;



public interface RemoteClientRegisterer extends Remote{

	void setClientInServer(String name, int port) throws RemoteException, NotBoundException;
}
