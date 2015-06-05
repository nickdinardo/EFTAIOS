package it.polimi.ingsw.DiNapoliDiNardo.Client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Coordinates;
import it.polimi.ingsw.DiNapoliDiNardo.Server.Handler;
import it.polimi.ingsw.DiNapoliDiNardo.Server.rmi.RemoteNotifier;
import it.polimi.ingsw.DiNapoliDiNardo.view.View;



public class Notifier implements RemoteNotifier, Handler {
	private View view;
	private String name;
	private String remoteName;
	Registry myregistry;
	Timer turntimer;
	
	
		public Notifier(String n, View v, Registry r, String rmn){
		this.view = v;
		this.name = n;
		this.myregistry = r;
		this.remoteName = rmn;
	}
	
		
	
	@Override
	public void closeConnections() throws RemoteException{
		
		try {
			myregistry.unbind(remoteName);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		UnicastRemoteObject.unexportObject(this, true);
		System.exit(0);
	
	}
	
	@Override
	public boolean askForAttack() throws ClassNotFoundException, IOException{
		String answer = view.askForAttack();
		boolean attacked;
		if ("Y".equalsIgnoreCase(answer))
			attacked = true;
		else 
			attacked = false;
		return attacked;
	}
	
	@Override
	public Coordinates askForMovement(boolean reask){
		return view.askMovement(reask);
	}
	
	@Override
	public Coordinates askForLights(boolean reask) throws IOException, ClassNotFoundException{
		Coordinates coord = view.askForLights(reask);
		return coord;
	}
	
	@Override	
	public String askForNoise(){
		return view.askForNoise();
	}
	
	@Override
	public int askForItem(String objects, boolean fromDiscardCall) throws IOException{
		return view.askItemUse(objects, fromDiscardCall);
	}
	
	@Override
	public int askHumanForItemChange(String objects) throws IOException{
		int index = view.askHumanItemDiscard(objects);
		if (index == 9)
			index = askForItem(objects, true);
		return index;
	}
	
	@Override
	public int askAlienForItemChange(String objects) throws IOException{
		return view.askAlienItemDiscard(objects);
	}
	
	@Override
	public void notifyMessage(String message)throws RemoteException {
		view.print(message);
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
	public void showFinalResults(boolean iWon, String name, String humanlosers,	String humanwinners, String alienwinners, String alienlosers)
			throws RemoteException {
		view.showFinalResults(iWon, name, humanlosers, humanwinners, alienwinners, alienlosers);
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
