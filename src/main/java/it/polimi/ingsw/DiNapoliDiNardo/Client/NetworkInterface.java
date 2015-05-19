package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.IOException;
import java.rmi.RemoteException;

public interface NetworkInterface {
	boolean connect() throws IOException;
	boolean close() throws IOException;
	void startInterface();
	
}
