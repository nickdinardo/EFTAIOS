package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.IOException;
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
	
	
	public Coordinates askForLights() throws IOException, ClassNotFoundException{
		
		Coordinates coord = view.askForLights();
		return (coord);
	}
	
		
	public String askForNoise(){
		
		return view.askForNoise();
	}
	
	
	public int askForItem(String objects) throws IOException{
		
		int index = view.askItemUse(objects);
		return index;
	}
	
	
	public int askHumanForItemChange(String objects) throws IOException{
		
		int index = view.askHumanItemDiscard(objects);
		if (index == 7)
			index = askForItem(objects);
		return index;
	}
	
	
	public int askAlienForItemChange(String objects) throws IOException{
		
		int index = view.askAlienItemDiscard(objects);
		return index;
	}
	
	
	public void notifyMessage(String message)throws RemoteException {
		
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
