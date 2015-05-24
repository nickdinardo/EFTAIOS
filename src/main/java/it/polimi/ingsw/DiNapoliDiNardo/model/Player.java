package it.polimi.ingsw.DiNapoliDiNardo.model;
import java.util.ArrayList;
import it.polimi.ingsw.DiNapoliDiNardo.model.boxes.*;
import it.polimi.ingsw.DiNapoliDiNardo.model.cards.*;


public abstract class Player {
	
	
	protected String name;
	protected GameState gamestate;
	protected Box position;
	protected boolean isAlive=true;
	protected ArrayList< Card > personalDeck = new ArrayList< Card >();
		
		
	
	
	//getters and setters
	public boolean isAlive() {
		return isAlive;
	}
	public ArrayList<Card> getPersonalDeck() {
		return personalDeck;
	}
	public Box getPosition() {
		return position;
	}
	public void setPosition(Box position) {
		this.position = position;
	}
	
	
	
	public abstract boolean movement(Box destination, Box position);
	public void teleport(){};
	public ArrayList<Player> attack(Box position){
		ArrayList<Player> playerInBox = new ArrayList<Player>();
		if(!position.isEmpty()){
			playerInBox = position.getPlayerHere();
			for(Player player : playerInBox ){
				if(this != player){
					player.kill();
					}
			}
		}
		playerInBox.remove(this);
		return playerInBox;
		
	}
	
	
	public boolean isValidSingleMovement(Box destination, Box position){
		
		if (destination instanceof Wall) return false;
		if (destination instanceof HumanBox) return false;
		if (destination instanceof AlienBox) return false;
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
	};
			
	
	public boolean isValidDoubleMovement(Box destination, Box position){
		
		ArrayList<Box> aroundBoxes = gamestate.getGalilei().givemeAroundBoxes(position);
		ArrayList<Box> oneStepBoxes = checkBoxes(aroundBoxes, position);
		for (Box box: oneStepBoxes){
			aroundBoxes = gamestate.getGalilei().givemeAroundBoxes(box);
			ArrayList<Box> twoStepBoxes = checkBoxes(aroundBoxes, box);
			if (twoStepBoxes.contains(destination))
				return true;
		}
		return false;
	};
	
	
	//check a group of boxes and return only the reachable from actual position with a single step
	public ArrayList<Box> checkBoxes (ArrayList<Box> boxesToCheck, Box position){
		ArrayList<Box> validBoxes = new ArrayList<Box>();
		for (Box box: boxesToCheck){
			if (isValidSingleMovement(box, position))
					validBoxes.add(box);
			}
		return validBoxes;
	}
		
	
	
	
	public void kill(){
		this.isAlive = false;
	}
	
	public String getName() {
		return this.name;
	}
}
		
	


