package it.polimi.ingsw.dinapolidinardo.server;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates;

import java.io.IOException;
import java.rmi.RemoteException;


/**
 * Interface that provides methods for both RMI and Socket handlers.
 * <p>
 * Game Controller can call these methods transparently without worrying
 * if is communicating with a RMI or Socket handler
 * 
 * @see it.polimi.ingsw.dinapolidinardo.client.RMIhandler
 * @see it.polimi.ingsw.dinapolidinardo.server.SocketHandler
 */
public interface Handler {
	
	/**
	 * @return handler (and user) name
	 * @throws RemoteException
	 */
	public String getName() throws RemoteException;
	/**
	 * Closes all the connections with specified user
	 * 
	 * @throws RemoteException
	 */
	public void closeConnections() throws RemoteException;
	/**
	 * Send message to be displayed by user View
	 * 
	 * @param message the message to be displayed
	 * @throws RemoteException
	 */
	public void notifyMessage(String message) throws RemoteException;
	/**
	 * Show to user information about his being an Human Player in the game
	 * 
	 * @param name the name of the player
	 * @throws RemoteException
	 */
	public void showBeingHuman (String name)throws RemoteException;
	/**
	 * Show to user information about his being an Alien Player in the game
	 * 
	 * @param name the name of the player
	 * @throws RemoteException
	 */
	public void showBeingAlien (String name)throws RemoteException;
	/**
	 * Ask input from user about the movement of his player
	 * 
	 * @param reask signals if game controller is reasking the input because not valid
	 * @return A Coordinate object with the selected coordinates
	 * @throws IOException if can't reach client 
	 * @throws ClassNotFoundException
	 * @see it.polimi.ingsw.dinapolidinardo.model.boxes.Coordinates
	 */
	public Coordinates askForMovement(boolean reask)  throws IOException, ClassNotFoundException;
	/**
	 * Ask input from user about the attack of his player
	 * 
	 * @return true if user wants to attack, false otherwise
	 * @throws IOException if can't reach client 
	 * @throws ClassNotFoundException
	 */
	public boolean askForAttack() throws ClassNotFoundException, IOException;
	/**
	 * Ask input from user about where declare noise
	 *  
	 * @return coordinates in form of String
	 * @throws ClassNotFoundException
	 * @throws IOException if can't reach client 
	 */
	public String askForNoise() throws ClassNotFoundException, IOException;
	/**
	 * Ask the selection of an item from user input
	 * 
	 * @param objects
	 * @param fromDiscardCall
	 * @return the index of the item selected
	 * @throws IOException if can't reach client 
	 */
	public int askForItem(String objects, boolean fromDiscardCall) throws IOException;
	/**
	 * Ask user to input the coordinates of the sector he wants to enlight
	 * 
	 * @param reask signals if game controller is reasking the input because not valid 
	 * @return A Coordinate object with the selected coordinates
	 * @throws IOException if can't reach client 
	 * @throws ClassNotFoundException
	 */
	public Coordinates askForLights(boolean reask) throws IOException, ClassNotFoundException;
	/**
	 * Ask the user if wants to discard an owned card 
	 * to get a free slot for the recently drawn one
	 * 
	 * @param objects the owned items in form of string
	 * @return the index of the item selected (or the index of "don't discard")
	 * @throws IOException if can't reach client 
	 */
	public int askAlienForItemChange(String objects) throws IOException;
	/**
	 * Ask the user if wants to discard or use an owned card 
	 * to get a free slot for the recently drawn one
	 * 
	 * @param objects the owned items in form of string
	 * @return the index of the item selected (or the index of "don't discard")
	 * @throws IOException if can't reach client 
	 */
	public int askHumanForItemChange(String objects) throws IOException;
	/**
	 * Show user the actual situation at the beginning of his turn
	 * 
	 * @param name name of the user
	 * @param position actual position 
	 * @param objects the owned items in form of string
	 * @param turn current turn of the game
	 * @throws RemoteException if can't reach client 
	 */
	public void showActualSituation (String name, String position, String objects, String turn) throws RemoteException;
	/**
	 * Notify to user the (failed or succeeded) escape of a player
	 * 
	 * @param escaped signals if escape has succeeded or failed
	 * @param name the name of the player who's escaping
	 * @param shipnumber the number of the lifeboat ship used
	 * @throws RemoteException if can't reach client 
	 */
	public void notifyEscape (boolean escaped, String name, String shipnumber) throws RemoteException;
	/**
	 * Ask to display the final results of the game
	 * 
	 * @param iWon signals is user won or not
	 * @param name name of the user
	 * @param humanlosers all the human players that have lost
	 * @param humanwinners all the human players that have won
	 * @param alienwinners all the alien players that have won
	 * @param alienlosers all the alien players that have lost
	 * @throws RemoteException if can't reach client 
	 */
	public void showFinalResults (boolean iWon, String name, String humanlosers, String humanwinners, String alienwinners, String alienlosers) throws RemoteException;
	/**
	 * Send to view all the updates happened in model after last user actions
	 * 
	 * @param position actual position of the player
	 * @param reachables list of all the boxes reachable with a single movement, colored in GUI 
	 * @param objects list of the owned item in form of string
	 * @throws IOException if can't reach client 
	 */
	public void updateView(String position, String reachables, String objects) throws IOException;
	/**
	 * Signals the view the end of the user turn
	 * @throws RemoteException if can't reach client 
	 */
	public void signalEndOfTurn()throws RemoteException;
}
