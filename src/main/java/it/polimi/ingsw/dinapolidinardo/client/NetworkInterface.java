package it.polimi.ingsw.dinapolidinardo.client;

import java.io.IOException;
import java.rmi.RemoteException;

public interface NetworkInterface {
	boolean connect() throws IOException;
	boolean close() throws IOException;
	void startInterface() throws RemoteException;
	
}