package it.polimi.ingsw.dinapolidinardo.model;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.dinapolidinardo.model.boxes.Box;
import it.polimi.ingsw.dinapolidinardo.model.cards.ItemCard;


/**
 * Abstract class that represent a player in the game, providing all the information 
 * needed by Game State and Game Controller
 */
public abstract class Player {
	
	
	protected String name;
	protected GameState gamestate;
	protected Box position;
	protected boolean isAlive = true;
	protected boolean savedWithDefenseCard = false;
	protected boolean losesIfKilledType = false;
	private boolean escaped = false;
	protected String myKiller ="";
	protected List< ItemCard > personalDeck = new ArrayList< ItemCard >();
		
		
	
	/**
	 * Method to implement in the subclasses that can use the teleport in game	
	 * 
	 * @see HumanPlayer
	 */
	public void teleport(){
		
	}
	
	/**
	 * When a player attack, gets all the other players that are in the same box
	 * and passes them to the game state for the resolution of the attack
	 * 
	 * @param position the box where the attacking player is
	 * @return the list of players affected by the attack
	 */
	public List<Player> attack(Box position){
		List<Player> playerInBox = new ArrayList<Player>();
		if(!position.isEmpty())
			for (Player p : position.getPlayersHere())
				playerInBox.add(p); 
	
		playerInBox.remove(this);
		return playerInBox;
		
	}
	
		
	//getters and setters
	
	public boolean isAdrenalized() {
		return false;
	}
	
	public boolean isSedated() {
		return false;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void kill(){
		this.isAlive = false;
	}
	
	public boolean isEscaped() {
		return this.escaped;
	}
	
	public void setEscaped(boolean sedated) {
		
	}
	
	public boolean isLosesIfKilledType() {
		return losesIfKilledType;
	}
	
	public List<ItemCard> getPersonalDeck() {
		return personalDeck;
	}

	public Box getPosition() {
		return position;
	}
	
	public void setPosition(Box position) {
		this.position = position;
		position.setPlayer(this);
	}
	
	public void setKiller(String killer){
		this.myKiller = killer;
	}
	
	public String getKiller(){
		return this.myKiller;
	}
	
	public String getName() {
		return this.name;
	}


	public int getMoveRange() {
		return 1;
	}

	public boolean isHumanFed() {
		return false;
	}	
	
	public boolean isHasAttacked() {
		return false;
	}
	
	
}
		
	


