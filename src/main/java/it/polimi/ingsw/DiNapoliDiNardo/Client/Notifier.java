package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.rmi.RemoteException;

import it.polimi.ingsw.DiNapoliDiNardo.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteNotifier;
import it.polimi.ingsw.DiNapoliDiNardo.view.TextView;


public class Notifier implements RemoteNotifier {
	public TextView view;
	private String name;
	
	public Notifier(String n, TextView v){
		this.view = v;
		this.name = n;
	}
	
	public Coordinates askForMovement(boolean reask){
		return view.askMovement(reask);
	}
	
	public void notifyMessage(String message)
			throws RemoteException {
		System.out.println(message);

	}

	public void showBeingAlien (String name){
		view.showBeingAlien(name);
	}
	
	public void showBeingHuman (String name){
		view.showBeingHuman(name);
	}
			
	public void showActualSituation (String name, String position, String objects){
		view.showActualSituation(name, position, objects);
	}
	
	
	
	
	
	
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
