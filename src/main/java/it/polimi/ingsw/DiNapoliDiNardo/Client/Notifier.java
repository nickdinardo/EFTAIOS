package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.rmi.RemoteException;

import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteNotifier;
import it.polimi.ingsw.DiNapoliDiNardo.view.TextView;


public class Notifier implements RemoteNotifier {
	public TextView view;
	private String name;
	
	public Notifier(String n, TextView v){
		this.view = view;
		this.name = n;
	}
	

	public void notifyMessage(String message, String source)
			throws RemoteException {
		System.out.println(source.toUpperCase()+") "+message);

	}

	
	
	
	
	
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
