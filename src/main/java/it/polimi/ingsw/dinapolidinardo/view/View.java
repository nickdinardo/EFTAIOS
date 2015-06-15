package it.polimi.ingsw.dinapolidinardo.view;

import java.io.IOException;
import java.rmi.RemoteException;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;


/**
 * Abstract class the represent the User Interface, according to the "MVC" pattern
 */
public abstract class View {

	
	/**
	 * Ask user to input his game name
	 * 
	 * @param reask signals if Game Controller is reasking the input because not valid
	 * @return a String representing the selected name
	 */
	public abstract String askName(boolean reask);
	
	
	/**
	 * Asks input from user about the movement of his player
	 * 
	 * @param reask signals if Game Controller is reasking the input because not valid
	 * @return A Coordinate object with the selected coordinates
	 * @see it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates
	 */
	public abstract Coordinates askMovement(boolean reask);
	
	
	/**
	 * Asks input from user about where declare noise
	 *  
	 * @return coordinates in form of String
	 */
	public abstract String askForNoise();
	
	
	/**
	 * Asks the selection of an item from user input
	 * 
	 * @param objects the currently owned item cards coded as a String
	 * @param fromDiscardCall signals if the call of this methods comes from a discard cards phase
	 * @return the index of the item selected
	 */
	public abstract int askItemUse(String objects, boolean fromDiscardCall);
	
	
	/**
	 * Asks user if wants to discard or use an owned card 
	 * to get a free slot for the recently drawn one
	 * 
	 * @param objects the owned items in form of string
	 * @return the index of the item selected (or the index of "don't discard")
	 */
	public abstract int askHumanItemDiscard(String objects);
	
	
	/**
	 * Asks the user if wants to discard an owned card 
	 * to get a free slot for the recently drawn one
	 * 
	 * @param objects the owned items in form of string
	 * @return the index of the item selected (or the index of "don't discard")
	 */
	public abstract int askAlienItemDiscard(String objects);
	
	
	/**
	 * Asks input from user about the attack of his player
	 * 
	 * @return true if user wants to attack, false otherwise
	 */
	public abstract String askForAttack();
	
	
	/**
	 * Asks user to input the coordinates of the sector he wants to enlight
	 * 
	 * @param reask signals if Game Controller is reasking the input because not valid 
	 * @return A Coordinate object with the selected coordinates
	 */
	public abstract Coordinates askForLights(boolean reask);
	
	
	/**
	 * Sends message to be displayed to user 
	 * 
	 * @param message the message to be displayed
	 */
	public abstract void print (String message);
	
	
	/**
	 * Shows to user information about his being an Alien Player in the game
	 * 
	 * @param name the name of the player
	 */
	public abstract void showBeingAlien (String name);
	
	
	/**
	 * Shows to user information about his being an Human Player in the game
	 * 
	 * @param name the name of the player
	 */
	public abstract void showBeingHuman (String name);

	
	/**
	 * Shows to user the actual situation at the beginning of his turn
	 * 
	 * @param name name of the user
	 * @param position actual position 
	 * @param objects the owned items in form of string
	 * @param turn current turn of the game
	 */
	public abstract void showActualSituation (String name, String position, String objects, String turn);
	
	
	/**
	 * Notifies to user the (failed or succeeded) escape of a player
	 * 
	 * @param escaped signals if escape has succeeded or failed
	 * @param name the name of the player who's escaping
	 * @param shipnumber the number of the lifeboat ship used
	 */
	public abstract void notifyEscape (boolean escaped, String name, String shipnumber);
	
	
	/**
	 * Displays the final results of the game
	 * 
	 * @param iWon signals is user won or not
	 * @param name name of the user
	 * @param humanlosers all the human players that have lost
	 * @param humanwinners all the human players that have won
	 * @param alienwinners all the alien players that have won
	 * @param alienlosers all the alien players that have lost
	 */
	public abstract void showFinalResults(boolean iWon, String name, String humanlosers, String humanwinners, String alienwinners, String alienlosers);

	
	/**
	 * Receives and shows the updates happened in model after last user actions
	 * 
	 * @param position actual position of the player
	 * @param reachables list of all the boxes reachable with a single movement, colored in GUI 
	 * @param objects list of the owned item in form of string
	 */
	public void update(String position, String reachables, String objects) {
		
	}

	/**
	 * Receives a signal of the end of the user turn
	 */
	public abstract void  signalEndOfTurn();
	
}
