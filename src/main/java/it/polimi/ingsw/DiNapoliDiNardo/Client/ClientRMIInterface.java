package it.polimi.ingsw.DiNapoliDiNardo.Client;

import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteHandler;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMIInterface implements NetworkInterface {

	private RemoteHandler handler;
	
	public boolean connect() {
		String name = "Handler";
        Registry registry;
		try {
			registry = LocateRegistry.getRegistry(2020);
		} catch (RemoteException e) {			
			e.printStackTrace();
			return false;
		}
        try {
			handler = (RemoteHandler) registry.lookup(name);
		} catch (AccessException e) {
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			e.printStackTrace();
			return false;
		} catch (NotBoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	//interaction method
		public void startInterface() {
			
		}
	
		
	
	public boolean close() {
		return true;
	}

}
