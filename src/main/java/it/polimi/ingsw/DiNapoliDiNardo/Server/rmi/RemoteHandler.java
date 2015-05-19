package it.polimi.ingsw.DiNapoliDiNardo.Server.rmi;

import it.polimi.ingsw.DiNapoliDiNardo.view.TextView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteHandler extends Remote {
	
	public TextView getView() throws RemoteException;
	public boolean isFinish() throws RemoteException;

	public void IncreaseNumPlayers() throws RemoteException;
}
