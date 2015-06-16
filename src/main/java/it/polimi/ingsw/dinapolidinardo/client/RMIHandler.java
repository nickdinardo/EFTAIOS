package it.polimi.ingsw.dinapolidinardo.client;

import java.io.IOException;
import java.io.PrintStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;
import it.polimi.ingsw.dinapolidinardo.remotestubs.RemoteRMIHandler;
import it.polimi.ingsw.dinapolidinardo.server.Handler;
import it.polimi.ingsw.dinapolidinardo.view.View;


/**
 * Handler reachable by server via remote stub that provides to server methods to call the view 
 * in the various phases of the game
 */
public class RMIHandler implements RemoteRMIHandler, Handler {
	private View view;
	private String name;
	private String remoteName;
	private PrintStream out = System.out;
	private Registry myregistry;
	
	
		/**
		 * 
		 * @param n the name of the user associated to this handler
		 * @param v the instance of the associated view
		 * @param r the registry where the remote stub of this object resides 
		 * @param rmn the remote string associated with this object
		 */
		public RMIHandler(String n, View v, Registry r, String rmn){
		this.view = v;
		this.name = n;
		this.myregistry = r;
		this.remoteName = rmn;
	}
	
		
	/**
	 * activated by the disconnection manager, close this handler, unexport all the remote objects,
	 * and unbound the remote registry on the client side. Usually called when user doesn't respond 
	 * over the maximum waiting time, to allow the other players to keep playing.
	 */
	@Override
	public void closeConnections() throws RemoteException{
		
		try {
			myregistry.unbind(remoteName);
		} catch (NotBoundException e) {
			out.println("Object is already unbound");
		}
		UnicastRemoteObject.unexportObject(this, true);
		out.println("Disconnected from game due to inactivity over the maximum turn time.");
		out.println("Please restart client if you want to play another game");
		System.exit(0);
	
	}
	
	
	//all the following are methods the call the view to ask user different inputs 
	//for different phases of the game (movement, lights, attack, etc...)
	
	
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
	public Coordinates askForMovement(boolean reask) throws IOException{
		return view.askMovement(reask);
	}
	
	@Override
	public Coordinates askForLights(boolean reask) throws IOException, ClassNotFoundException{
		return view.askForLights(reask);
	}
	
	@Override	
	public String askForNoise() throws IOException{
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
	public void updateView(String position, String reachables, String objects) throws RemoteException {
		view.update(position, reachables, objects);
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
	public void signalEndOfTurn() throws RemoteException {
		view.signalEndOfTurn();
	}
		
	@Override
	public String getName() {
		return name;
	}
	
	
	
	
	
}
