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
		
		
	
	public abstract boolean movement(Box destination, Box position);
	
	
	public void teleport(){}
	
	
	public List<Player> attack(Box position){
		List<Player> playerInBox = new ArrayList<Player>();
		if(!position.isEmpty())
			for (Player p : position.getPlayerHere())
				playerInBox.add(p); 
	
		playerInBox.remove(this);
		return playerInBox;
		
	}
	
	
	public boolean isValidSingleMovement(Box destination, Box position){
		
		if (!destination.isCanBeCrossedType()) 
			return false;
		if (position.getCoordX()%2==0){//BASSA
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		else{//ALTA
			if (destination.getCoordX()-position.getCoordX() == -1 || destination.getCoordX()-position.getCoordX() == 1)
				if (destination.getCoordY()-position.getCoordY() == -1 || destination.getCoordY()-position.getCoordY() == 0)
					return true;
			if (destination.getCoordX()-position.getCoordX() == 0)
				if (destination.getCoordY()-position.getCoordY() == 1 || destination.getCoordY()-position.getCoordY() == -1)
					return true;
		}
		return false;
	}
			
	
	public boolean isValidDoubleMovement(Box destination, Box position){
		
		List<Box> aroundBoxes = gamestate.getGalilei().givemeAroundBoxes(position);
		List<Box> oneStepBoxes = checkBoxes(aroundBoxes, position);
		for (Box box: oneStepBoxes){
			aroundBoxes = gamestate.getGalilei().givemeAroundBoxes(box);
			List<Box> twoStepBoxes = checkBoxes(aroundBoxes, box);
			if (twoStepBoxes.contains(destination) || oneStepBoxes.contains(destination))
				return true;
		}
		return false;
	}
	
	
	//check a group of boxes and return only the reachable from actual position with a single step
	public List<Box> checkBoxes (List<Box> boxesToCheck, Box position){
		List<Box> validBoxes = new ArrayList<Box>();
		for (Box box: boxesToCheck){
			if (isValidSingleMovement(box, position))
					validBoxes.add(box);
			}
		return validBoxes;
	}
		
	
	
	//getters and setters
	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean isEscaped() {
		return this.escaped;
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
	

}
		
	


