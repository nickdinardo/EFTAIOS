package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.Handler;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteNotifier;
import it.polimi.ingsw.DiNapoliDiNardo.view.TextView;



public class Notifier implements RemoteNotifier, Handler {
	private TextView view;
	private String name;
	
		public Notifier(String n, TextView v){
		this.view = v;
		this.name = n;
	}
	
	@Override
	public boolean askForAttack() throws ClassNotFoundException, IOException{
		String answer = view.askForAttack();
		if ("Y".equalsIgnoreCase(answer))
			return true;
		else 
			return false;
	}
	
	@Override
	public Coordinates askForMovement(boolean reask){
		
		return view.askMovement(reask);
	}
	
	@Override
	public Coordinates askForLights() throws IOException, ClassNotFoundException{
		
		Coordinates coord = view.askForLights();
		return (coord);
	}
	
	@Override	
	public String askForNoise(){
		
		return view.askForNoise();
	}
	
	@Override
	public int askForItem(String objects) throws IOException{
		
		int index = view.askItemUse(objects);
		return index;
	}
	
	@Override
	public int askHumanForItemChange(String objects) throws IOException{
		
		int index = view.askHumanItemDiscard(objects);
		if (index == 7)
			index = askForItem(objects);
		return index;
	}
	
	@Override
	public int askAlienForItemChange(String objects) throws IOException{
		
		int index = view.askAlienItemDiscard(objects);
		return index;
	}
	
	@Override
	public void notifyMessage(String message)throws RemoteException {
		
		System.out.println(message);

	}

	@Override
	public void showBeingAlien (String name){
		
		view.showBeingAlien(name);
	}
	
	@Override
	public void showBeingHuman (String name){
		
		view.showBeingHuman(name);
	}
			
	@Override
	public void showActualSituation (String name, String position, String objects, String turn){
		
		view.showActualSituation(name, position, objects, turn);
	}
	
	
	@Override
	public void notifyEscape(boolean escaped, String name, String shipnumber) throws RemoteException {
		
		view.notifyEscape(escaped, name, shipnumber);
	}
	
	
	
	
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	
}
