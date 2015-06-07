package it.polimi.ingsw.DiNapoliDiNardo.model;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.Box;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.ItemCard;



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
		
		
	
		
	public void teleport(){
		
	}
	
	
	public List<Player> attack(Box position){
		List<Player> playerInBox = new ArrayList<Player>();
		if(!position.isEmpty())
			for (Player p : position.getPlayerHere())
				playerInBox.add(p); 
	
		playerInBox.remove(this);
		return playerInBox;
		
	}
	
	
	
	//getters and setters
	public boolean isAdrenalized() {
		return false;
	}
	
	public boolean isHumanFed() {
		return false;
	}	
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean isEscaped() {
		return this.escaped;
	}
	
	public void setEscaped(boolean sedated) {
		
	}
	
	public boolean isLosesIfKilledType() {
		return losesIfKilledType;
	}
	
	public boolean isHasAttacked() {
		return false;
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
	
	public void kill(){
		this.isAlive = false;
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


	public boolean isSedated() {
		return false;
	}
	

}
		
	


